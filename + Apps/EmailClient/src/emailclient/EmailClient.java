package emailclient;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Почтовый клиент.
 * @author dwinner@inbox.ru
 */
public class EmailClient extends JFrame {

    private static final long serialVersionUID = 6L;

    // Модель таблицы данных сообщений.
    private MessagesTableModel tableModel;

    // Таблица со списком сообщений.
    private JTable table;

    // Текстовое поле для отображения сообщений.
    private JTextArea messageTextArea;

    // Панель, разделенная на таблицу сообщений и панель
    // для отображения содержимого сообщения.
    private JSplitPane splitPane;

    // Кнопки для управления выбранным сообщением.
    private JButton replyButton;
    private JButton forwardButton;
    private JButton deleteButton;

    // Выбранное из таблицы сообщение.
    private Message selectedMessage;

    // Флаг для отображения состояния сообщения.
    private boolean deleting;

    // Сеанс JavaMail.
    private Session session;

    // Конструктор для почтового клиента.
    public EmailClient() {
        // Установить заголовок приложения.
        setTitle("E-mail Client");

        // Установить размеры окна.
        setSize(640, 480);

        // Обработать события при закрытии окна.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                actionExit();
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Установить меню файлов.
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem fileExitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        fileExitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionExit();
            }
        });
        fileMenu.add(fileExitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Установить панель для кнопок.
        JPanel buttonPanel = new JPanel();
        JButton newButton = new JButton("New Message");
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionNew();
            }
        });
        buttonPanel.add(newButton);

        // Установить таблицу сообщений.
        tableModel = new MessagesTableModel();
        table = new JTable(tableModel);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                tableSelectionChanged();
            }
        });
        // Разрешить выбирать только одну строку.
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Установить панель почтового клиента.
        JPanel emailsPanel = new JPanel();
        emailsPanel.setBorder(BorderFactory.createTitledBorder("E-mails"));
        messageTextArea = new JTextArea();
        messageTextArea.setEditable(false);
        splitPane = new JSplitPane( JSplitPane.VERTICAL_SPLIT,
                                    new JScrollPane(table),
                                    new JScrollPane(messageTextArea));
        emailsPanel.setLayout(new BorderLayout());
        emailsPanel.add(splitPane, BorderLayout.CENTER);

        // Установить вторую панель для кнопок.
        JPanel buttonPanel2 = new JPanel();
        replyButton = new JButton("Reply");
        replyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionReply();
            }
        });
        replyButton.setEnabled(false);
        buttonPanel2.add(replyButton);
        forwardButton = new JButton("Forward");
        forwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionForward();
            }
        });
        forwardButton.setEnabled(false);
        buttonPanel2.add(forwardButton);
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionDelete();
            }
        });
        deleteButton.setEnabled(false);
        buttonPanel2.add(deleteButton);

        // Расположение панелей.
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(emailsPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel2, BorderLayout.SOUTH);
    }

    // Выход из программы.
    private void actionExit() {
        System.exit(0);
    }

    // Создать новое сообщение.
    private void actionNew() {
        sendMessage(MessageDialog.NEW, null);
    }

    // Вызывается при изменении выбранной строки таблицы.
    private void tableSelectionChanged() {
        // Если сообщение не удаляется, установить выбранное сообщение
        // и отобразить его.
        if (!deleting) {
            selectedMessage = tableModel.getMessage(table.getSelectedRow());
            showSelectedMessage();
            updateButtons();
        }
    }

    // Ответить на сообщение.
    private void actionReply() {
        sendMessage(MessageDialog.REPLY, selectedMessage);
    }

    // Перенаправить сообщение.
    private void actionForward() {
        sendMessage(MessageDialog.FORWARD, selectedMessage);
    }

    // Удалить выбранное сообщение.
    private void actionDelete() {
        deleting = true;
        try {
            // Удалить сообщение с сервера.
            selectedMessage.setFlag(Flags.Flag.DELETED, true);
            Folder folder = selectedMessage.getFolder();
            folder.close(true);
            folder.open(Folder.READ_WRITE);
        }
        catch (MessagingException ex) {
            Logger.getLogger(EmailClient.class.getName()).log(Level.SEVERE, null, ex);
            showError("Unable to delete message.", false);
        }

        // Удалить сообщение из таблицы.
        tableModel.deleteMessage(table.getSelectedRow());

        // Обновить GUI.
        messageTextArea.setText("");
        deleting = false;
        selectedMessage = null;
        updateButtons();
    }

    // Отправить указанное сообщение.
    private void sendMessage(int type, Message message) {
        try {
            // Отобразить диалоговое окно сообщений для получения параметров сообщения.
            MessageDialog dialog;
            try {
                dialog = new MessageDialog(this, type, message);
                if (!dialog.display()) {
                    // Отменить написание сообщения.
                    return;
                }
            }
            catch (Exception ex) {
                Logger.getLogger(EmailClient.class.getName()).log(Level.SEVERE, null, ex);
                showError("Unable to send message.", false);
                return;
            }

            // Создать новое сообщение со значениями из диалогового окна.
            Message newMessage = new MimeMessage(session);
            newMessage.setFrom(new InternetAddress(dialog.getFrom()));
            newMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(dialog.getTo()));
            newMessage.setSubject(dialog.getSubject());
            newMessage.setSentDate(new Date());
            newMessage.setText(dialog.getContent());

            // Отправить новое сообщение.
            Transport.send(newMessage);
        }
        catch (MessagingException ex) {
            Logger.getLogger(EmailClient.class.getName()).log(Level.SEVERE, null, ex);
            showError("Unable to send message.", false);
        }
    }

    // Отобразить выбранное сообщение в панели для содержимого.
    private void showSelectedMessage() {
        // Показать песочные часы, пока сообщение загружается.
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            messageTextArea.setText(getMessageContent(selectedMessage));
            messageTextArea.setCaretPosition(0);
        }
        catch (Exception e) {
            showError("Unabled to load message.", false);
        }
        finally {
            // Вернуть прежний вид курсора.
            setCursor(Cursor.getDefaultCursor());
        }
    }

    /*
     * Обновить состояние каждой кнопки на основе того,
     * выбрано ли сообщение в таблице.
     */
    private void updateButtons() {
        boolean enable = (selectedMessage != null) ? true : false;
        replyButton.setEnabled(enable);
        forwardButton.setEnabled(enable);
        deleteButton.setEnabled(enable);
    }

    // Показать окно приложения на экране.
    @Override
    @SuppressWarnings({"deprecation", "deprecation"})
    public void show() {
        super.show();
        // Обновить разделение панели с соотношением 50/50.
        splitPane.setDividerLocation(.5);
    }

    // Подключиться к почтовому серверу.
    @SuppressWarnings("deprecation")
    public void connect() {
        // Отобразить диалоговое окно для соединения.
        ConnectDialog dialog = new ConnectDialog(this);
        dialog.show();
        // Обработать данные для соединения.
        StringBuilder connectionUrl = new StringBuilder();
        connectionUrl.append(dialog.getType()).append("://");
        connectionUrl.append(dialog.getUsername()).append(":");
        connectionUrl.append(dialog.getPassword()).append("@");
        connectionUrl.append(dialog.getServer()).append("/");

        // Отобразить диалоговое окно с фразой о том, что
        // почтовые сообщения загружаются с сервера.
        final DownloadingDialog downloadingDialog = new DownloadingDialog(this);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                downloadingDialog.show();
            }
        });

        // Создать сеанс JavaMail и подключиться к серверу.
        Store store = null;
        try {
            // Инициализировать сеанс JavaMail и подключиться к SMTP-серверу.
            Properties props = new Properties();
            props.put("mail.smtp.host", dialog.getSmtpServer());
            session = Session.getDefaultInstance(props, null);
            // подключиться к почтовому серверу.
            URLName urln = new URLName(connectionUrl.toString());
            store = session.getStore(urln);
            store.connect();
        }
        catch (Exception e) {
            // Закрыть диалоговое окно.
            downloadingDialog.dispose();
            // Отобразить диалоговое окно ошибки.
            showError("Unable to connect.", true);
        }

        // Загрузить заголовки сообщений с сервера.
        Folder folder;
        try {
            // Открыть главный "INBOX"-каталог.
            folder = store.getFolder("INBOX");
            folder.open(Folder.READ_WRITE);

            // Получить список сообщений из каталога.
            Message[] messages = folder.getMessages();

            // Извлечь заголовок каждого сообщения из каталога.
            FetchProfile profile = new FetchProfile();
            profile.add(FetchProfile.Item.ENVELOPE);
            folder.fetch(messages, profile);

            // Поместить сообщение в таблицу.
            tableModel.setMessages(messages);
        }
        catch (MessagingException ex) {
            Logger.getLogger(EmailClient.class.getName()).log(Level.SEVERE, null, ex);

            // Закрыть диалоговое окно.
            downloadingDialog.dispose();

            // Отобразить диалоговое окно ошибки.
            showError("Unable to download messages.", true);
        }

        // Закрыть диалоговое окно.
        downloadingDialog.dispose();
    }

    // Отобразить диалоговое окно ошибки и выйти из программы.
    private void showError(String message, boolean exit) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        if (exit) {
            System.exit(0);
        }
    }

    // Получить содержимое сообщения.
    public static String getMessageContent(Message message) throws Exception {
        Object content = message.getContent();
        if (content instanceof Multipart) {
            StringBuilder messageContent = new StringBuilder();
            Multipart multipart = (Multipart) content;
            for (int i = 0; i < multipart.getCount(); i++) {
                Part part = (Part) multipart.getBodyPart(i);
                if (part.isMimeType("text/plain")) {
                    messageContent.append(part.getContent().toString());
                }
            }
            return messageContent.toString();
        }
        else {
            return content.toString();
        }
    }

    public static void main(String[] args) {
        EmailClient client = new EmailClient();
        client.show();
        // Отобразить диалоговое окно для соединения.
        client.connect();
    }
}
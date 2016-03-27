package emailclient;

import java.awt.*;
import java.awt.event.*;
import javax.mail.*;
import javax.swing.*;

/**
 * Этот класс отображает диалоговое окно, используемое
 * при создании сообщений.
 * @author dwinner@inbox.ru
 */
public class MessageDialog extends JDialog {

    private static final long serialVersionUID = 3L;

    // Идентификаторы сообщений.
    public static final int NEW = 0;
    public static final int REPLY = 1;
    public static final int FORWARD = 2;

    // Текстовые поля для сообщений "от", "для" и "предмет".
    private JTextField fromTextField;
    private JTextField toTextField;
    private JTextField subjectTextField;

    // Текстовое поле для содержимого сообщения.
    private JTextArea contentTextArea;

    // Флаг, указывающий о закрытии диалогового окна.
    private boolean cancelled;

    // Конструктор для диалогового окна.
    public MessageDialog(Frame parent, int type, Message message) throws Exception {
        // Вызов супер-конструктора, определение диалогового окна как модального.
        super(parent, true);

        /* Установить заголовок диалогового окна и получить значения для полей
         * "to", "subject" и "content" на основе типа сообщения.
         */
        String to = "", subject = "", content = "";
        switch (type) {
            // Ответное сообщение.
            case REPLY:
                setTitle("Reply To Message");
                // Получить значение "to" для сообщения.
                Address[] senders = message.getFrom();
                if (senders != null || senders.length > 0) {
                    to = senders[0].toString();
                }
                to = message.getFrom()[0].toString();
                // Получить тему сообщения.
                subject = message.getSubject();
                subject = (subject != null && subject.length() > 0) ? "RE: " + subject : "RE:";
                // Получить содержимое сообщения и добавить запись "REPLIED TO".
                content = "\n------------------ ";
                content += "REPLIED TO MESSAGE" + "------------------\n";
                content += EmailClient.getMessageContent(message);
                break;
            // Перенаправить сообщение.
            case FORWARD:
                setTitle("Forward Message");
                // Получить тему сообщения.
                subject = message.getSubject();
                subject = (subject != null && subject.length() > 0) ? "FWD: " + subject : "FWD:";
                // Получить содержимое сообщения и добавить запись "FORWARDED".
                content = "\n------------------ " + "FORWARDED MESSAGE" + "------------------\n";
                content += EmailClient.getMessageContent(message);
                break;
            // Новое сообщение.
            default:
                setTitle("New Message");
        }

        // Обработать события при закрытии.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                actionCancel();
            }
        });

        // Установить поля панели.
        JPanel fieldsPanel = new JPanel();
        GridBagConstraints constraints;
        GridBagLayout layout = new GridBagLayout();
        fieldsPanel.setLayout(layout);
        JLabel fromLabel = new JLabel("From:");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(fromLabel, constraints);
        fieldsPanel.add(fromLabel);
        fromTextField = new JTextField();
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(fromTextField, constraints);
        fieldsPanel.add(fromTextField);
        JLabel toLabel = new JLabel("To:");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(toLabel, constraints);
        fieldsPanel.add(toLabel);
        toTextField = new JTextField(to);
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 0, 0);
        constraints.weightx = 1.0D;
        layout.setConstraints(toTextField, constraints);
        fieldsPanel.add(toTextField);
        JLabel subjectLabel = new JLabel("Subject:");
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 0);
        layout.setConstraints(subjectLabel, constraints);
        fieldsPanel.add(subjectLabel);
        subjectTextField = new JTextField(subject);
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 5, 0);
        layout.setConstraints(subjectTextField, constraints);
        fieldsPanel.add(subjectTextField);

        // Установить панель содержимого.
        JScrollPane contentPanel = new JScrollPane();
        contentTextArea = new JTextArea(content, 10, 50);
        contentPanel.setViewportView(contentTextArea);

        // Установить панель кнопок.
        JPanel buttonsPanel = new JPanel();
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionSend();
            }
        });
        buttonsPanel.add(sendButton);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionCancel();
            }
        });
        buttonsPanel.add(cancelButton);

        // Добавить отображения панелей.
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(fieldsPanel, BorderLayout.NORTH);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

        // Оптимизировать размеры.
        pack();

        // Центрировать диалоговое окно в приложении.
        setLocationRelativeTo(parent);
    }

    // Проверка и закрытие диалогового окна.
    private void actionSend() {
        if (
                fromTextField.getText().trim().length() < 1 ||
                toTextField.getText().trim().length() < 1 ||
                subjectTextField.getText().trim().length() < 1 ||
                contentTextArea.getText().trim().length() < 1
           )
        {
            JOptionPane.showMessageDialog(this,
                                          "One or more fields is missing.",
                                          "Missing Field(s)",
                                          JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Закрытие диалогового окна.
        dispose();
    }

    // Прервать создание сообщения и закрыть диалоговое окно.
    private void actionCancel() {
        cancelled = true;
        // закрытие диалогового окна.
        dispose();
    }

    // Показать диалоговое окно.
    @SuppressWarnings("deprecation")
    public boolean display() {
        show();
        // Возврат, если отображение произошло с ошибкой.
        return !cancelled;
    }

    // Получить сообщение "From" field value.
    public String getFrom() {
        return fromTextField.getText();
    }

    // Получить значение поля "To" для сообщения.
    public String getTo() {
        return toTextField.getText();
    }

    // Получить значение поля "Subject" для сообщения.
    public String getSubject() {
        return subjectTextField.getText();
    }

    // Получить значение поля "content" для сообщения.
    public String getContent() {
        return contentTextArea.getText();
    }
}
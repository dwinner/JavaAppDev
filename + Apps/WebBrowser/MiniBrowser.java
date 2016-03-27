import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.html.*;

/**
 * Мини Web-браузер.
 * @author dwinner@inbox.ru
 */
public class MiniBrowser extends JFrame implements HyperlinkListener {

    private static final long serialVersionUID = 1L;

    // Кнопки для перемещения по странице.
    private JButton backButton;
    private JButton forwardButton;

    // Поле для адреса страницы.
    private JTextField locationTextField;

    // Панель для отображения страницы.
    private JEditorPane displayEditorPane;

    // Список страниц, которые были посещены.
    private ArrayList<String> pageList = new ArrayList<String>();

    // Конструктор для мини Web-браузера.
    @SuppressWarnings("LeakingThisInConstructor")
    public MiniBrowser() {
        // Установить заголовок.
        super("Mini Browser");

        // Установить размеры окна.
        setSize(640, 480);

        // Обработка событий при закрытии.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                actionExit();
            }
        });

        // Установить меню "File".
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem fileExitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        fileExitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionExit();
            }
        });
        fileMenu.add(fileExitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Установить панель кнопок.
        JPanel buttonPanel = new JPanel();
        backButton = new JButton("< Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionBack();
            }
        });
        backButton.setEnabled(false);
        buttonPanel.add(backButton);
        forwardButton = new JButton("Forward >");
        forwardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionForward();
            }
        });
        forwardButton.setEnabled(false);
        buttonPanel.add(forwardButton);
        locationTextField = new JTextField(35);
        locationTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    actionGo();
                }
            }
        });
        buttonPanel.add(locationTextField);
        JButton goButton = new JButton("GO");
        goButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionGo();
            }
        });
        buttonPanel.add(goButton);

        // Настроить отображение страницы.
        displayEditorPane = new JEditorPane();
        displayEditorPane.setContentType("text/html");
        displayEditorPane.setEditable(false);
        displayEditorPane.addHyperlinkListener(this);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(displayEditorPane), BorderLayout.CENTER);
    }

    // Выход из программы.
    private void actionExit() {
        System.exit(0);
    }

    // Вернуться к ранее просматриваемой странице.
    private void actionBack() {
        URL currentUrl = displayEditorPane.getPage();
        int pageIndex = pageList.indexOf(currentUrl.toString());
        try {
            showPage(new URL(pageList.get(pageIndex - 1)), false);
        }
        catch (Exception e) {

        }
    }

    // Перейти к следующей странице.
    private void actionForward() {
        URL currentUrl = displayEditorPane.getPage();
        int pageIndex = pageList.indexOf(currentUrl.toString());
        try {
            showPage(new URL(pageList.get(pageIndex + 1)), false);
        }
        catch (Exception e) {

        }
    }

    // Загрузить и отобразить страницу, адрес которой указан в текстовом поле.
    private void actionGo() {
        URL verifiedUrl = verifyUrl(locationTextField.getText());
        if (verifiedUrl != null) {
            showPage(verifiedUrl, true);
        }
        else {
            showError("Invalid URL");
        }
    }

    // Показать диалоговое окно ошибки.
    private void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Проверить формат URL.
    private URL verifyUrl(String url) {
        // разрешены только HTTP-адреса.
        if (!url.toLowerCase().startsWith("http://")) {
            return null;
        }

        // Проверить URL.
        URL verifiedUrl = null;
        try {
            verifiedUrl = new URL(url);
        }
        catch (MalformedURLException ex) {
            Logger.getLogger(MiniBrowser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return verifiedUrl;
    }

    // Показать указанную страницу и добавить ее в список страниц.
    private void showPage(URL pageUrl, boolean addToList) {
        // Показать курсор ожидания на время загрузки страницы.
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            // Получить адрес страницы для отображения.
            URL currentUrl = displayEditorPane.getPage();

            // Загрузить и отобразить указанную страницу.
            displayEditorPane.setPage(pageUrl);

            // Получить адрес новой страницы для отображения.
            URL newUrl = displayEditorPane.getPage();

            // Добавить страницу в список.
            if (addToList) {
                int listSize = pageList.size();
                if (listSize > 0) {
                    int pageIndex = pageList.indexOf(currentUrl.toString());
                    if (pageIndex < listSize - 1) {
                        for (int i = listSize - 1; i > pageIndex; i--) {
                            pageList.remove(i);
                        }
                    }
                }
                pageList.add(newUrl.toString());
            }

            // Обновить текстовое поле.
            locationTextField.setText(newUrl.toString());

            // Обновить кнопки в соответствии с отображаемой страницей.
            updateButtons();
        }
        catch (Exception e) {
            // Показать сообщение об ошибке.
            showError("Unable to load page.");
        }
        finally {
            // Установить курсор по умолчанию.
            setCursor(Cursor.getDefaultCursor());
        }
    }

    // Обновить кнопки back и forward в соответствии со страницей,
    // которая будет отображаться.
    private void updateButtons() {
        if (pageList.size() < 2) {
            backButton.setEnabled(false);
            forwardButton.setEnabled(false);
        }
        else {
            URL currentUrl = displayEditorPane.getPage();
            int pageIndex = pageList.indexOf(currentUrl.toString());
            backButton.setEnabled(pageIndex > 0);
            forwardButton.setEnabled(pageIndex < (pageList.size() - 1));
        }
    }

    // Обработать щелчок на гиперссылке.
    public void hyperlinkUpdate(HyperlinkEvent event) {
        HyperlinkEvent.EventType eventType = event.getEventType();
        if (eventType == HyperlinkEvent.EventType.ACTIVATED) {
            if (event instanceof HTMLFrameHyperlinkEvent) {
                HTMLFrameHyperlinkEvent linkEvent = (HTMLFrameHyperlinkEvent) event;
                HTMLDocument document = (HTMLDocument) displayEditorPane.getDocument();
                document.processHTMLFrameHyperlinkEvent(linkEvent);
            }
            else {
                showPage(event.getURL(), true);
            }
        }
    }

    // Запустить MiniBrowser.
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        MiniBrowser browser = new MiniBrowser();
        browser.show();
    }
}
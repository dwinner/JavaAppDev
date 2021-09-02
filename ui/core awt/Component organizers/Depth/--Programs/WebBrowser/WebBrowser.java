import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * WebBrowser - приложение для просмотра Web-страниц с использованием
 * компонентов WebToolBar и WebBrowserPane.
 * <p/>
 * @author dwinner@inbox.ru
 */
public class WebBrowser extends JFrame
{
    private WebToolBar toolBar;
    private WebBrowserPane browserPane;

    // Конструктор WebBrowser
    public WebBrowser()
    {
        super("dwinner Web Browser");

        // Создание WebBrowserPane и WebToolBar для навигации
        browserPane = new WebBrowserPane();
        toolBar = new WebToolBar(browserPane);

        // Размещение компонентов WebBrowser
        Container contentPane = getContentPane();
        contentPane.add(toolBar, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(browserPane), BorderLayout.CENTER);
    }

    // Запуск приложения
    public static void main(String[] args)
    {
        WebBrowser browser = new WebBrowser();
        browser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        browser.setSize(640, 480);
        browser.setVisible(true);
    }
}
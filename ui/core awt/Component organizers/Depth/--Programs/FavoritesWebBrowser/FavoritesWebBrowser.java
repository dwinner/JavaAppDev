import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

/**
 * Приложение для просмотра Web-сайтов с помощью компонентов WebToolBar
 * и WebBrowserPane и отображения HTML-страницы, содержащей ссылки на
 * избранные Web-сайты.
 * <p/>
 * @author dwinner@inbox.ru
 */
public class FavoritesWebBrowser extends JFrame
{
    private static final long serialVersionUID = 1L;
    private WebToolBar toolBar;
    private WebBrowserPane browserPane;
    private WebBrowserPane favoritesBrowserPane;

    // Конструктор WebBrowser
    public FavoritesWebBrowser()
    {
        super("Deitel Web Browser");

        // Создание объектов WebBrowserPane и WebToolBar для навигации
        browserPane = new WebBrowserPane();
        toolBar = new WebToolBar(browserPane);
        // Создание объекта WebBrowserPane для отображения избранных сайтов
        favoritesBrowserPane = new WebBrowserPane();

        // Добавление WebToolBar в качестве слушателя для HyperlinkEvents в
        // компонент favoritesBrowserPane
        favoritesBrowserPane.addHyperlinkListener(toolBar);

        // Отображение favorites.html в панели favoritesBrowserPane
        favoritesBrowserPane.goToURL(getClass().getResource("favorites.html"));

        // Создание горизонтальной панели JSplitPane и добавление панелей
        // WebBrowserPanes с полосами прокрутки JScrollPane
        JSplitPane splitPane = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            new JScrollPane(favoritesBrowserPane),
            new JScrollPane(browserPane));
        // Задание положения разделительной линии между панелями WebBrowserPanes
        splitPane.setDividerLocation(210);
        // Добавление кнопок для разворачивания/сворачивания разделителя
        splitPane.setOneTouchExpandable(true);

        // Размещение компонентов WebBrowser
        Container contentPane = getContentPane();
        contentPane.add(toolBar, BorderLayout.NORTH);
        contentPane.add(splitPane, BorderLayout.CENTER);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        FavoritesWebBrowser browser = new FavoritesWebBrowser();
        browser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        browser.setSize(640, 480);
        browser.setVisible(true);
    }
}
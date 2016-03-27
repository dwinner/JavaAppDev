
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * Приложение, которое использует класс JTabbedPane для отображения
 * нескольких окон Web-браузера
 * <p/>
 * @author dwinner@inbox.ru
 */
public class TabbedPaneWebBrowser extends JFrame
{
    // Класс JTabbedPane для одновременного отображения нескольких браузеров
    private JTabbedPane tabbedPane = new JTabbedPane();

    // Конструктор TabbedPaneWebBrowser
    public TabbedPaneWebBrowser()
    {
        super("JTabbedPane Web Browser");

        // Создание вкладки для первого браузера
        createNewTab();

        // Добавление JTabbedPane в панель contentPane
        getContentPane().add(tabbedPane);

        // Создание меню File (JMenu) для создания новых вкладок браузеров и
        // выхода из приложения
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new NewTabAction());
        fileMenu.addSeparator();
        fileMenu.add(new ExitAction());
        fileMenu.setMnemonic('F');

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    // Создание новой вкладки для браузера
    private void createNewTab()
    {
        // Создание панели JPanel, включающей панели WebBrowserPane и WebToolBar
        JPanel panel = new JPanel(new BorderLayout());

        // Создание панелей WebBrowserPane и WebToolBar
        WebBrowserPane browserPane = new WebBrowserPane();
        WebToolBar toolBar = new WebToolBar(browserPane);

        // Добавление WebBrowserPane и WebToolBar в панель JPanel
        panel.add(toolBar, BorderLayout.NORTH);
        panel.add(new JScrollPane(browserPane), BorderLayout.CENTER);

        // Добавление JPanel в JTabbedPane
        tabbedPane.addTab("Browser " + tabbedPane.getTabCount(), panel);
    }

    // Действия для создания новой вкладки для браузера
    private class NewTabAction extends AbstractAction
    {
        private static final long serialVersionUID = 1L;
        // Конструктор NewTabAction

        NewTabAction()
        {
            // Задание имени, описания и "горячей" клавиши
            putValue(Action.NAME, "New Browser Tab");
            putValue(Action.SHORT_DESCRIPTION, "Create New Browser Tab");
            putValue(Action.MNEMONIC_KEY, new Integer('N'));
        }
        // Действие создает новую вкладку браузера

        @Override
        public void actionPerformed(ActionEvent e)
        {
            createNewTab();
        }
    }

    // Действие для выхода из приложения
    private class ExitAction extends AbstractAction
    {
        private static final long serialVersionUID = 1L;
        // Конструктор ExitAction

        ExitAction()
        {
            // Задание имени, описания и "горячей" клавиши
            putValue(Action.NAME, "Exit");
            putValue(Action.SHORT_DESCRIPTION, "Exit Application");
            putValue(Action.MNEMONIC_KEY, new Integer('X'));
        }
        // Действие осуществляет выход из приложения

        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        TabbedPaneWebBrowser browser = new TabbedPaneWebBrowser();
        browser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        browser.setSize(640, 480);
        browser.setVisible(true);
    }
}
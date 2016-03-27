
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 * Приложение для просмотра Web-страниц с использованием технологии перетаскивания.
 * <p/>
 * @author dwinner@inbox.ru
 */
public class DnDWebBrowser extends JFrame
{
    private WebToolBar toolBar;
    private WebBrowserPane browserPane;

    /**
     * Конструктор DnDWebBrowser.
     */
    public DnDWebBrowser()
    {
        super("Drag And Drop Web Browser");

        // Создание объектов WebBrowserPane и WebToolBar для навигации

        browserPane = new WebBrowserPane();
        toolBar = new WebToolBar(browserPane);

        // Разрешение WebBrowserPane воспринимать операции перетаскивания с
        // использованием объекта DropTargetHandler в качестве 
        // слушателя DropTargetListener

        browserPane.setDropTarget(new DropTarget(browserPane,
            DnDConstants.ACTION_COPY,
            new DropTargetHandler()));

        // Размещение компонентов WebBrowser

        Container contentPane = getContentPane();
        contentPane.add(toolBar, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(browserPane), BorderLayout.CENTER);
    }

    /**
     * Внутренний класс для обработки событий DropTargetEvents.
     */
    private class DropTargetHandler implements DropTargetListener
    {
        DropTargetHandler()
        {
        }

        @Override
        public void dragEnter(DropTargetDragEvent dtde)
        {   // Обработка операции перетаскивания при заходе на объект DropTarget

            // Если данные имеют тип javaFileListFlavor, принять перетащенный
            // объект для копирования

            if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor))
            {
                dtde.acceptDrag(DnDConstants.ACTION_COPY);
            }
            else
            {
                // Отвергнуть все другие объекты DataFlavors
                dtde.rejectDrag();
            }
        }

        @Override
        public void dragOver(DropTargetDragEvent dtde)
        {
            // Вызывается при перетаскивании над DropTarget
        }

        @Override
        public void dropActionChanged(DropTargetDragEvent dtde)
        {
            // throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void dragExit(DropTargetEvent dte)
        {
            // Вызывается при выходе за пределы DropTarget при перетаскивании
        }

        @Override
        @SuppressWarnings("deprecation")
        public void drop(DropTargetDropEvent dtde)
        {   // Обработка операции отпускания объекта

            // Получение отпускаемого объекта Transferable

            Transferable transferable = dtde.getTransferable();

            // Если объект Transferable является списком файлов,
            // принять операцию отпускания

            if (transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor))
            {
                // принятие операции отпускания для копирования объекта
                dtde.acceptDrop(DnDConstants.ACTION_COPY);
                try
                {
                    // Обработка списка файлов и отображение каждого из них браузером
                    List fileList =
                        (List) transferable.getTransferData(DataFlavor.javaFileListFlavor);

                    Iterator iterator = fileList.iterator();

                    while (iterator.hasNext())
                    {
                        File file = (File) iterator.next();
                        // Отображение файла в браузере и завершение операции отпускания
                        browserPane.goToURL(file.toURL());
                    }

                    // индикация успешной операции отпукания
                    dtde.dropComplete(true);
                }
                catch (UnsupportedFlavorException | IOException ex)
                {
                    dtde.dropComplete(false);
                }
            }
            else
            {
                // Если отпускаемый объект не является списком файлов, отвергнуть отпускание
                dtde.rejectDrop();
            }
        }
    }

    /**
     * Входная точка для запуска
     * <p/>
     * @param args аргументы cmd
     */
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                DnDWebBrowser browser = new DnDWebBrowser();
                browser.setDefaultCloseOperation(EXIT_ON_CLOSE);
                browser.setSize(640, 480);
                browser.setVisible(true);
            }
        });
    }
}

/**
 * WebBrowserPane - простой компонент для просмотра Web-содержимого, который расширяет класс
 * JEditorPane и хранит перечень просмотренных URL
 * <p/>
 * @author dwinner@inbox.ru
 */
class WebBrowserPane extends JEditorPane
{
    private List<URL> history = new ArrayList<>();
    private int historyIndex;

    /**
     * Конструктор WebBrowserPan. Запрет редактирования для разрешения переходов по гиперссылкам.
     */
    WebBrowserPane()
    {
        setEditable(false);
    }

    /**
     * Отображение заданного URL и добавление его в журнал.
     * <p/>
     * @param url url
     */
    public void goToURL(URL url)
    {
        displayPage(url);
        history.add(url);
        historyIndex = history.size() - 1;
    }

    /**
     * Отображение следующего URL в журнале на панели editorPane
     * <p/>
     * @return url
     */
    public URL forward()
    {
        historyIndex++;

        // Не выходить за конец журнала.

        if (historyIndex >= history.size())
        {
            historyIndex = history.size() - 1;
        }

        URL url = history.get(historyIndex);
        displayPage(url);

        return url;
    }

    /**
     * Отображение предыдущего URL в журнале на панели editorPane
     * <p/>
     * @return url
     */
    public URL back()
    {
        historyIndex--;

        // Не выходить за начало журнала.

        if (historyIndex < 0)
        {
            historyIndex = 0;
        }

        // Отображение предыдущего URL

        URL url = history.get(historyIndex);
        displayPage(url);

        return url;
    }

    /**
     * Отображение содержимого по заданному URL в панели JEditorPane.
     * <p/>
     * @param pageURL url для отображения.
     */
    private void displayPage(URL pageURL)
    {
        try
        {
            setPage(pageURL);   // Отображение URL
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(this, ex);
        }
    }
}

/**
 * WebToolBar - подкласс класса JToolBar, содержащий компоненты для навигации в панели
 * WebBrowserPane. WebToolBar содержит кнопки "Вперед" и "Назад" и текстовое поле для ввода URL.
 * <p/>
 * @author dwinner@inbox.ru
 */
class WebToolBar extends JToolBar implements HyperlinkListener
{
    private WebBrowserPane webBrowserPane;
    private JButton backButton;
    private JButton forwardButton;
    private JTextField urlTextField;

    // Конструктор WebToolBar
    WebToolBar(final WebBrowserPane webBrowserPane)
    {
        super("Web Navigation");
        this.webBrowserPane = webBrowserPane;
        // Регистрация событий HyperlinkEvent
        webBrowserPane.addHyperlinkListener(this);

        // Создание объекта JTextField для ввода URL
        urlTextField = new JTextField(25);
        urlTextField.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {   // Переход в webBrowser по введенному пользователем URL

                // Попытка загрузить страницу в webBrowserPane

                URL url = null;
                try
                {
                    url = new URL(urlTextField.getText());
                }
                catch (MalformedURLException ex)
                {
                    JOptionPane.showMessageDialog(webBrowserPane, ex);
                }
                webBrowserPane.goToURL(url);
            }
        });

        // Создание объекта JButton для перехода к предыдущему URL в журнале.

        backButton =
            new JButton(new ImageIcon(getClass().getResource("back.gif")));
        backButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                URL url = webBrowserPane.back();    // Переход по предыдущему URL
                urlTextField.setText(url.toString());   // Отображение URL в поле urlTextField
            }
        });

        // Создание объекта JButton для перехода к следующему URL в журнале

        forwardButton =
            new JButton(new ImageIcon(getClass().getResource("forward.gif")));
        forwardButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                URL url = webBrowserPane.forward(); // Переход к следующему URL
                urlTextField.setText(url.toString()); // Отображение нового URL в поле urlTextField
            }
        });

        // Добавление компонентов JButtons и JTextField в панель WebToolBar
        add(backButton);
        add(forwardButton);
        add(urlTextField);
    }

    @Override
    public void hyperlinkUpdate(HyperlinkEvent e)
    {   // Отслеживание событий HyperlinkEvents в WebBrowserPane
        
        // Если гиперссылка была активирована, перейти по URL ссылки
        
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
        {
            URL url = e.getURL();    // Получение URL из HyperlinkEvent

            // Переход по URL и отображение URL в поле urlTextField
            
            webBrowserPane.goToURL(url);
            urlTextField.setText(url.toString());
        }
    }
}
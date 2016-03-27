import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;

/**
 * WebBrowserPane - простой компонент для просмотра Web-содержимого,
 * который расширяет класс JEditorPane и хранит перечень просмотренных
 * URL
 * <p/>
 * @author dwinner@inbox.ru
 */
public class WebBrowserPane extends JEditorPane
{
    private List<URL> history = new ArrayList<>();
    private int historyIndex;

    public WebBrowserPane()
    {   // Конструктор WebBrowserPane
        // Запрет редактирования для разрешения переходов по гиперссылкам.
        setEditable(false);
    }

    // Отображение заданного URL и добавление его в журнал.
    public void goToURL(URL url)
    {
        displayPage(url);
        history.add(url);
        historyIndex = history.size() - 1;
    }

    // Отображение следующего URL в журнале на панели editorPane
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

    // Отображение предыдущего URL в журнале на панели editorPane
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

    // Отображение содержимого по заданному URL в панели JEditorPane
    private void displayPage(URL pageURL)
    {
        try
        {
            // Отображение URL
            setPage(pageURL);
        }
        catch (IOException ex)
        {
            Logger.getLogger(WebBrowserPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
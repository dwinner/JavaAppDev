import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 * WebToolBar - подкласс класса JToolBar, содержащий компоненты для
 * навигации в панели WebBrowserPane. WebToolBar содержит кнопки "Вперед" и
 * "Назад" и текстовое поле для ввода URL.
 * <p/>
 * @author dwinner@inbox.ru
 */
public class WebToolBar extends JToolBar implements HyperlinkListener
{
    private static final long serialVersionUID = 2L;
    private WebBrowserPane webBrowserPane;
    private JButton backButton;
    private JButton forwardButton;
    private JTextField urlTextField;

    // Конструктор WebToolBar
    public WebToolBar(final WebBrowserPane webBrowserPane)
    {
        super("Web Navigation");
        this.webBrowserPane = webBrowserPane;
        // Регистрация событий HyperlinkEvent
        webBrowserPane.addHyperlinkListener(this);

        // Создание объекта JTextField для ввода URL
        urlTextField = new JTextField(25);
        urlTextField.addActionListener(new ActionListener()
        {
            // Переход в webBrowser по введенному пользователем URL
            @Override
            public void actionPerformed(ActionEvent event)
            {
                // Попытка загрузить страницу в webBrowserPane
                URL url;
                try
                {
                    url = new URL(urlTextField.getText());
                    webBrowserPane.goToURL(url);
                }
                catch (MalformedURLException ex)
                {
                    Logger.getLogger(WebToolBar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // Создание объекта JButton для перехода к предыдущему URL в журнале.
        backButton = new JButton(new ImageIcon(getClass().getResource("images/back.gif")));
        backButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                // Переход по предыдущему URL
                URL url = webBrowserPane.back();

                // Отображение URL в поле urlTextField
                urlTextField.setText(url.toString());
            }
        });

        // Создание объекта JButton для перехода к следующему URL в журнале
        forwardButton = new JButton(new ImageIcon(getClass().getResource("images/forward.gif")));
        forwardButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                // Переход к следующему URL
                URL url = webBrowserPane.forward();

                // Отображение нового URL в поле urlTextField
                urlTextField.setText(url.toString());
            }
        });

        // Добавление компонентов JButtons и JTextField в панель WebToolBar
        add(backButton);
        add(forwardButton);
        add(urlTextField);
    }

    // Отслеживание событий HyperlinkEvents в WebBrowserPane
    @Override
    public void hyperlinkUpdate(HyperlinkEvent e)
    {
        // Если гиперссылка была активирована, перейти по URL ссылки
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
        {

            // Получение URL из HyperlinkEvent
            URL url = e.getURL();

            // Переход по URL и отображение URL в поле urlTextField
            webBrowserPane.goToURL(url);
            urlTextField.setText(url.toString());
        }
    }
}
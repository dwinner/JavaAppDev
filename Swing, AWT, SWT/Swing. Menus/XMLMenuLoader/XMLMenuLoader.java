
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Инструмент для загрузки меню из XML-файла
 * <p/>
 * @author dwinner@inbox.ru
 */
public class XMLMenuLoader
{
    // Источник данных XML
    private InputSource source;
    // Анализатор XML
    private SAXParser parser;
    // Обработчик XML
    private DefaultHandler documentHandler;
    // Хранилище для всех частей системы меню
    private Map<String, Object> menuStorage = new HashMap<String, Object>();
    // Текущая строка меню
    private JMenuBar currentMenuBar;
    // Список для упорядочения выпадающих меню
    private LinkedList<Object> menus = new LinkedList<>();

    /**
     * Конструктор требует задать поток данных с меню
     * <p/>
     * @param stream Входной поток
     */
    public XMLMenuLoader(InputStream stream)
    {
        // Настраиваем источник данных XML
        Reader reader = new InputStreamReader(stream);
        source = new InputSource(reader);
        try
        {
            parser = SAXParserFactory.newInstance().newSAXParser();
        }
        catch (ParserConfigurationException | SAXException ex)
        {
            throw new RuntimeException(ex);
        }
        // Создаем обработчик XML
        documentHandler = new XMLParser();
    }

    /**
     * Подсчитывает XML и создает систему меню
     * <p/>
     * @throws Exception
     */
    public void parse() throws Exception
    {
        parser.parse(source, documentHandler);
    }

    /**
     * Получение строки меню из карты
     * <p/>
     * @param name Имя ключа карты
     * <p/>
     * @return Ссылка на объект строки меню
     */
    public JMenuBar getMenuBar(String name)
    {
        return (JMenuBar) menuStorage.get(name);
    }

    /**
     * Получение выпадающего меню из карты
     * <p/>
     * @param name Имя ключа
     * <p/>
     * @return Ссылка на объект меню
     */
    public JMenu getMenu(String name)
    {
        return (JMenu) menuStorage.get(name);
    }

    /**
     * Получение элемента меню из карты
     * <p/>
     * @param name Имя ключа
     * <p/>
     * @return Ссылка на элемент меню
     */
    public JMenuItem getMenuItem(String name)
    {
        return (JMenuItem) menuStorage.get(name);
    }

    /**
     * Convenient-метод для быстрого добавления слушателя событий
     * <p/>
     * @param name     Идентификатор объекта добавления
     * @param listener ActionListener-Слушатель
     */
    public void addActionListener(String name, ActionListener listener)
    {
        getMenuItem(name).addActionListener(listener);
    }

    /**
     * XML-Обработчик
     * <p/>
     * @author dwinner@inbox.ru
     */
    private class XMLParser extends DefaultHandler
    {
        @Override
        public void startElement(
           String uri,
           String localName,
           String qName,
           Attributes attributes) throws SAXException
        {
            // Определяем тип узла
            switch (qName)
            {
                case "menubar":
                    parseMenuBar(attributes);
                    break;
                case "menu":
                    parseMenu(attributes);
                    break;
                case "menuitem":
                    parseMenuItem(attributes);
                    break;
            }
        }

        @Override
        public void endElement(
           String uri,
           String localName,
           String qName) throws SAXException
        {
            if (qName.equals("menu"))
            {
                menus.removeFirst();
            }
        }

        /**
         * Создание новой строки меню
         * <p/>
         * @param attrs Атрибуты создания
         */
        protected void parseMenuBar(Attributes attrs)
        {
            JMenuBar menuBar = new JMenuBar();
            // Определяем имя
            String name = attrs.getValue("name");
            menuStorage.put(name, menuBar);
            currentMenuBar = menuBar;
        }

        /**
         * Создание нового выпадающего меню
         * <p/>
         * @param attrs Атрибуты создания
         */
        protected void parseMenu(Attributes attrs)
        {
            // Создаем меню
            JMenu menu = new JMenu();
            String name = attrs.getValue("name");
            // Настраиваем общие атрибуты
            adjustProperties(menu, attrs);
            menuStorage.put(name, menu);
            // Добавляем меню в предыдущее выпадающее меню или в строку меню
            if ( ! menus.isEmpty())
            {
                ((JMenu) menus.getFirst()).add(menu);
            }
            else
            {
                currentMenuBar.add(menu);
            }
            // Добавляем в список выпадающих меню
            menus.addFirst(menu);
        }

        /**
         * Создание нового пункта меню
         * <p/>
         * @param attrs Атрибуты создания
         */
        protected void parseMenuItem(Attributes attrs)
        {
            // Проверяем, не разделитель ли это
            String name = attrs.getValue("name");
            if (name.equals("separator"))
            {
                ((JMenu) menus.getFirst()).addSeparator();
                return;
            }
            // Создаем пункт меню
            JMenuItem menuItem = new JMenuItem();
            // Настраиваем свойства
            adjustProperties(menuItem, attrs);
            menuStorage.put(name, menuItem);
            // Добавляем к текущему выпадающему меню
            ((JMenu) menus.getFirst()).add(menuItem);
        }

        private void adjustProperties(JMenuItem menuItem, Attributes attrs)
        {
            // Получаем поддерживаемые атрибуты
            String text = attrs.getValue("text");
            String mnemonic = attrs.getValue("mnemonic");
            String accelerator = attrs.getValue("accelerator");
            String enabled = attrs.getValue("enabled");
            // Настраиваем свойства меню
            menuItem.setText(text);
            if (mnemonic != null)
            {
                menuItem.setMnemonic(mnemonic.charAt(0));
            }
            if (accelerator != null)
            {
                menuItem.setAccelerator(KeyStroke.getKeyStroke(accelerator));
            }
            if (enabled != null)
            {
                boolean isEnabled = true;
                if (enabled.equals("false"))
                {
                    isEnabled = false;
                }
                menuItem.setEnabled(isEnabled);
            }
        }

    }

}
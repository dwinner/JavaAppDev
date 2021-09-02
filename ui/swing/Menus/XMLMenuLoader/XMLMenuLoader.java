
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
 * ���������� ��� �������� ���� �� XML-�����
 * <p/>
 * @author dwinner@inbox.ru
 */
public class XMLMenuLoader
{
    // �������� ������ XML
    private InputSource source;
    // ���������� XML
    private SAXParser parser;
    // ���������� XML
    private DefaultHandler documentHandler;
    // ��������� ��� ���� ������ ������� ����
    private Map<String, Object> menuStorage = new HashMap<String, Object>();
    // ������� ������ ����
    private JMenuBar currentMenuBar;
    // ������ ��� ������������ ���������� ����
    private LinkedList<Object> menus = new LinkedList<>();

    /**
     * ����������� ������� ������ ����� ������ � ����
     * <p/>
     * @param stream ������� �����
     */
    public XMLMenuLoader(InputStream stream)
    {
        // ����������� �������� ������ XML
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
        // ������� ���������� XML
        documentHandler = new XMLParser();
    }

    /**
     * ������������ XML � ������� ������� ����
     * <p/>
     * @throws Exception
     */
    public void parse() throws Exception
    {
        parser.parse(source, documentHandler);
    }

    /**
     * ��������� ������ ���� �� �����
     * <p/>
     * @param name ��� ����� �����
     * <p/>
     * @return ������ �� ������ ������ ����
     */
    public JMenuBar getMenuBar(String name)
    {
        return (JMenuBar) menuStorage.get(name);
    }

    /**
     * ��������� ����������� ���� �� �����
     * <p/>
     * @param name ��� �����
     * <p/>
     * @return ������ �� ������ ����
     */
    public JMenu getMenu(String name)
    {
        return (JMenu) menuStorage.get(name);
    }

    /**
     * ��������� �������� ���� �� �����
     * <p/>
     * @param name ��� �����
     * <p/>
     * @return ������ �� ������� ����
     */
    public JMenuItem getMenuItem(String name)
    {
        return (JMenuItem) menuStorage.get(name);
    }

    /**
     * Convenient-����� ��� �������� ���������� ��������� �������
     * <p/>
     * @param name     ������������� ������� ����������
     * @param listener ActionListener-���������
     */
    public void addActionListener(String name, ActionListener listener)
    {
        getMenuItem(name).addActionListener(listener);
    }

    /**
     * XML-����������
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
            // ���������� ��� ����
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
         * �������� ����� ������ ����
         * <p/>
         * @param attrs �������� ��������
         */
        protected void parseMenuBar(Attributes attrs)
        {
            JMenuBar menuBar = new JMenuBar();
            // ���������� ���
            String name = attrs.getValue("name");
            menuStorage.put(name, menuBar);
            currentMenuBar = menuBar;
        }

        /**
         * �������� ������ ����������� ����
         * <p/>
         * @param attrs �������� ��������
         */
        protected void parseMenu(Attributes attrs)
        {
            // ������� ����
            JMenu menu = new JMenu();
            String name = attrs.getValue("name");
            // ����������� ����� ��������
            adjustProperties(menu, attrs);
            menuStorage.put(name, menu);
            // ��������� ���� � ���������� ���������� ���� ��� � ������ ����
            if ( ! menus.isEmpty())
            {
                ((JMenu) menus.getFirst()).add(menu);
            }
            else
            {
                currentMenuBar.add(menu);
            }
            // ��������� � ������ ���������� ����
            menus.addFirst(menu);
        }

        /**
         * �������� ������ ������ ����
         * <p/>
         * @param attrs �������� ��������
         */
        protected void parseMenuItem(Attributes attrs)
        {
            // ���������, �� ����������� �� ���
            String name = attrs.getValue("name");
            if (name.equals("separator"))
            {
                ((JMenu) menus.getFirst()).addSeparator();
                return;
            }
            // ������� ����� ����
            JMenuItem menuItem = new JMenuItem();
            // ����������� ��������
            adjustProperties(menuItem, attrs);
            menuStorage.put(name, menuItem);
            // ��������� � �������� ����������� ����
            ((JMenu) menus.getFirst()).add(menuItem);
        }

        private void adjustProperties(JMenuItem menuItem, Attributes attrs)
        {
            // �������� �������������� ��������
            String text = attrs.getValue("text");
            String mnemonic = attrs.getValue("mnemonic");
            String accelerator = attrs.getValue("accelerator");
            String enabled = attrs.getValue("enabled");
            // ����������� �������� ����
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
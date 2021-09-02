import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Данная программа демонстрирует использование SAX-анализатора. В результате работы программы
 * выводится информация о гипертекстовых ссылках, содержащихся в XHTML-документе. Запуск программы:
 * java SAXTest url
 * <p/>
 * @version 1.00 2001-09-29
 * @author Cay Horstmann
 */
public class SAXTest
{
    public static void main(String[] args)
        throws  ParserConfigurationException,
                MalformedURLException,
                SAXException,
                IOException
    {
        String url;
        if (args.length == 0)
        {
            url = "http://www.w3c.org";
            System.out.println("Using " + url);
        }
        else
        {
            url = args[0];
        }

        DefaultHandler handler = new DefaultHandler()
        {
            @Override
            public void startElement(
                String uri,
                String localName,
                String qName,
                Attributes attributes) throws SAXException
            {
                if (localName.equalsIgnoreCase("a") && attributes != null)
                {
                    for (int i = 0; i < attributes.getLength(); i++)
                    {
                        String aname = attributes.getLocalName(i);
                        if (aname.equalsIgnoreCase("href"))
                        {
                            System.out.println(attributes.getValue(i));
                        }
                    }
                }
            }
        };

        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        SAXParser saxParser = factory.newSAXParser();
        InputStream in = new URL(url).openStream();
        saxParser.parse(in, handler);
    }
}

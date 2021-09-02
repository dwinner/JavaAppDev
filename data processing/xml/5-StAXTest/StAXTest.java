import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Данная программа демонстрирует использование StAX-анализатора.
 * Программа выводит все гиперссылки, имеющиеся на Web-странице XTHML.
 * Запуск программы: java StAXTest url
 * @author Cay Horstmann
 * @version 1.0 2007-06-23
 */
public class StAXTest
{
    public static void main(String[] args)
        throws  MalformedURLException,
                IOException, 
                XMLStreamException
    {
        String urlString;
        if (args.length == 0)
        {
            urlString = "http://www.w3c.org";
            System.out.println("Using " + urlString);
        }
        else
        {
            urlString = args[0];
        }
        
        URL url = new URL(urlString);
        InputStream in = url.openStream();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser = factory.createXMLStreamReader(in);
        
        while (parser.hasNext())
        {            
            int event = parser.next();
            if (event == XMLStreamConstants.START_ELEMENT)
            {
                if (parser.getLocalName().equals("a"))
                {
                    String href = parser.getAttributeValue(null, "href");
                    if (href != null)
                    {
                        System.out.println(href);
                    }
                }
            }
        }
    }
}

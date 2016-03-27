
import java.io.*;
import java.util.StringTokenizer;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.xml.sax.*;
import org.xml.sax.helpers.AttributesImpl;

/**
 * Пример XSLT-преобразования записей о сотрудниках. Записи хранятся в файле employee.dat и читаются
 * как XML-данные. С помощью таблиц стилей данные преобразуются в формат HTML и в текстовый формат.
 * В командной строке необходимо указать требуемую таблицу стилей. Например, для получения данных в
 * property-формате программа должна быть вызвана следующим образом: java TransformTest makeprop.xsl
 * <p/>
 * @version 1.01 2007-06-25
 * @author Cay Horstmann
 */
public class TransformTest
{
    public static void main(String[] args) throws Exception
    {
        String filename = (args.length > 0) ? args[0] : "makehtml.xsl";
        File styleSheet = new File(filename);
        StreamSource styleSource = new StreamSource(styleSheet);

        Transformer t = TransformerFactory.newInstance().newTransformer(styleSource);
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty(OutputKeys.METHOD, "xml");
        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        t.transform(
            new SAXSource(
                new EmployeeReader(),
                new InputSource(new FileInputStream("employee.dat"))
            ),
            new StreamResult(System.out)
        );
    }
}

/**
 * Этот класс считывает данные из текстового файла employee.dat и генерирует события
 * SAX-анализатора. В результате разбор текстового файла происходит так же, как и анализ XML-файла.
 */
class EmployeeReader implements XMLReader
{
    private ContentHandler handler;

    @Override
    public void parse(InputSource source) throws IOException, SAXException
    {
        InputStream stream = source.getByteStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String rootElement = "staff";
        AttributesImpl atts = new AttributesImpl();

        if (handler == null)
        {
            throw new SAXException("No content handler");
        }

        handler.startDocument();
        handler.startElement("", rootElement, rootElement, atts);
        String line;
        while ((line = in.readLine()) != null)
        {
            handler.startElement("", "employee", "employee", atts);
            StringTokenizer t = new StringTokenizer(line, "|");

            handler.startElement("", "name", "name", atts);
            String s = t.nextToken();
            handler.characters(s.toCharArray(), 0, s.length());
            handler.endElement("", "name", "name");

            handler.startElement("", "salary", "salary", atts);
            s = t.nextToken();
            handler.characters(s.toCharArray(), 0, s.length());
            handler.endElement("", "salary", "salary");

            atts.addAttribute("", "year", "year", "CDATA", t.nextToken());
            atts.addAttribute("", "month", "month", "CDATA", t.nextToken());
            atts.addAttribute("", "day", "day", "CDATA", t.nextToken());
            handler.startElement("", "hiredate", "hiredate", atts);
            handler.endElement("", "hiredate", "hiredate");
            atts.clear();

            handler.endElement("", "employee", "employee");
        }

        handler.endElement("", rootElement, rootElement);
        handler.endDocument();
    }

    @Override
    public void setContentHandler(ContentHandler newValue)
    {
        handler = newValue;
    }

    @Override
    public ContentHandler getContentHandler()
    {
        return handler;
    }

    // При вызове следующих методов не выполняется никаких действий.
    @Override
    public void parse(String systemId) throws IOException, SAXException
    {
    }

    @Override
    public void setErrorHandler(ErrorHandler handler)
    {
    }

    @Override
    public ErrorHandler getErrorHandler()
    {
        return null;
    }

    @Override
    public void setDTDHandler(DTDHandler handler)
    {
    }

    @Override
    public DTDHandler getDTDHandler()
    {
        return null;
    }

    @Override
    public void setEntityResolver(EntityResolver resolver)
    {
    }

    @Override
    public EntityResolver getEntityResolver()
    {
        return null;
    }

    @Override
    public void setProperty(String name, Object value)
    {
    }

    @Override
    public Object getProperty(String name)
    {
        return null;
    }

    @Override
    public void setFeature(String name, boolean value)
    {
    }

    @Override
    public boolean getFeature(String name)
    {
        return false;
    }
}
import java.text.ParseException;
import java.util.StringTokenizer;
import javax.swing.text.DefaultFormatter;

/**
 * Форматировщик для 4-байтных IP-адресов в форме a.b.c.d.
 */
public class IPAddressFormatter extends DefaultFormatter
{
    @Override
    public String valueToString(Object value) throws ParseException
    {
        if (!(value instanceof byte[]))
        {
            throw new ParseException("Not a byte[]", 0);
        }
        byte[] a = (byte[]) value;
        if (a.length != 4)
        {
            throw new ParseException("Length != 4", 0);
        }
        StringBuilder builder = new StringBuilder(0xF + 1);
        for (int i = 0; i < 4; i++)
        {
            int b = a[i];
            if (b < 0)
            {
                b += 256;
            }
            builder.append(String.valueOf(b));
            if (i < 3)
            {
                builder.append('.');
            }
        }
        return builder.toString();
    }

    @Override
    public Object stringToValue(String text) throws ParseException
    {
        StringTokenizer tokenizer = new StringTokenizer(text, ".");
        byte[] a = new byte[4];
        for (int i = 0; i < 4; i++)
        {
            int b = 0;
            if (!tokenizer.hasMoreTokens())
            {
                throw new ParseException("Too little bytes", 0);
            }
            try
            {
                b = Integer.parseInt(tokenizer.nextToken());
            }
            catch (NumberFormatException nfEx)
            {
                ParseException parseEx = new ParseException("Not an integer", i);
                parseEx.initCause(nfEx);
                throw parseEx;
            }
            if (b < 0 || b >= 256)
            {
                throw new ParseException("Byte out of range", 0);
            }
            a[i] = (byte) b;
        }
        if (tokenizer.hasMoreTokens())
        {
            throw new ParseException("Too many bytes", 0);
        }
        return a;
    }
}

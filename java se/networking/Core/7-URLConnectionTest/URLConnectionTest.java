
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * ������ ���������� � �������� �� ��������� URL � ����������� ����� ��������� � ������� ������ � 10
 * �������� ������������� ������. ������� � ��������� ������ URL, � ����� (� ������ �������������)
 * ��� ������������ � ������ (��� HTTP-�������������).
 * <p/>
 * @version 1.11 2007-06-26
 * @author Cay Horstmann
 */
public class URLConnectionTest
{
    public static final String DEFAULT_URL_STRING = "http://java.sun.com";

    public static void main(String[] args)
    {
        try
        {
            String urlName = args.length > 0 ? args[0] : DEFAULT_URL_STRING;

            URL url = new URL(urlName);
            URLConnection connection = url.openConnection();

            // ��������� ����� ������������ � ������ (���� ��� ������� � ��������� ������).

            if (args.length > 2)
            {
                String username = args[1];
                String password = args[2];
                String input = username + ":" + password;
                String encoding = base64Encode(input);
                connection.setRequestProperty("Authorization", "Basic " + encoding);
            }

            connection.connect();

            // ����� ����� ���������.

            Map<String, List<String>> headers = connection.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : headers.entrySet())
            {
                String key = entry.getKey();
                for (String value : entry.getValue())
                {
                    System.out.println(key + ": " + value);
                }
            }

            // ����� �������� �����, ���������� � ������� �����������
            // ������� (������ � ������� �����)

            System.out.println("----------");
            System.out.println("getContentType: " + connection.getContentType());
            System.out.println("getContentLength: " + connection.getContentLength());
            System.out.println("getContentEncoding: " + connection.getContentEncoding());
            System.out.println("getDate: " + connection.getDate());
            System.out.println("getExpiration: " + connection.getExpiration());
            System.out.println("getLastModifed: " + connection.getLastModified());
            System.out.println("----------");

            Scanner in = new Scanner(connection.getInputStream());

            // ����� ������ ������ ����� �����������.

            for (int n = 1; in.hasNextLine() && n <= 10; n++)
            {
                System.out.println(in.nextLine());
            }
            if (in.hasNextLine())
            {
                System.out.println(". . .");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * ����������� base64 ������.
     * <p/>
     * @param s ������
     * <p/>
     * @return ������, �������������� �� base64
     */
    public static String base64Encode(String s)
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        try (Base64OutputStream out = new Base64OutputStream(bOut))
        {
            out.write(s.getBytes());
            out.flush();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return bOut.toString();
    }
}

/**
 * ���� ������ ����������� ����� ������ � ���, ���������� �� ������ ��������� base64. ��������
 * ��������� base64 ������ 3 ����� ������������� � 4 ������� �� ��������� �����:
 * |11111122|22223333|33444444| ������ ����� �� 6 ����� ���������� � ������������ � ������ toBase64.
 * ���� ���������� ������� ������ �� ������ 3, �� ��������� ������ �� 4-� �������� ����������� �����
 * ��� ����� ��������� =. ������ �������� ������ ����� ����� �� ����� 76 ��������.
 */
class Base64OutputStream extends FilterOutputStream
{
    private static char[] toBase64 =
    {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
        'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
        'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
    };
    private int col = 0;
    private int i = 0;
    private int[] inbuf = new int[3];

    /**
     * ������� ������.
     * <p/>
     * @param out ����� ��� �������.
     */
    Base64OutputStream(OutputStream out)
    {
        super(out);
    }

    @Override
    public void write(int c) throws IOException
    {
        inbuf[i] = c;
        i++;
        if (i == 3)
        {
            super.write(toBase64[(inbuf[0] & 0xFC) >> 2]);
            super.write(toBase64[((inbuf[0] & 0x03) << 4) | ((inbuf[1] & 0xF0) >> 4)]);
            super.write(toBase64[((inbuf[1] & 0x0F) << 2) | ((inbuf[2] & 0xC0) >> 6)]);
            super.write(toBase64[inbuf[2] & 0x3F]);
            col += 4;
            i = 0;
            if (col >= 76)
            {
                super.write('\n');
                col = 0;
            }
        }
    }

    @Override
    public void flush() throws IOException
    {
        if (i == 1)
        {
            super.write(toBase64[(inbuf[0] & 0xFC) >> 2]);
            super.write(toBase64[(inbuf[0] & 0x03) << 4]);
            super.write('=');
            super.write('=');
        }
        else if (i == 2)
        {
            super.write(toBase64[(inbuf[0] & 0xFC) >> 2]);
            super.write(toBase64[((inbuf[0] & 0x03) << 4) | ((inbuf[1] & 0xF0) >> 4)]);
            super.write(toBase64[(inbuf[1] & 0x0F) << 2]);
            super.write('=');
        }
        if (col > 0)
        {
            super.write('\n');
            col = 0;
        }
    }
}

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Данная программа создает сокет для соединения с атомными часами в Болдере, штат Колорадо. Она
 * выводит на экран текущее время, полученное с сервера.
 * <p/>
 * @version 1.20 2004-08-03
 * @author Cay Horstmann
 */
public class SocketTest
{
    private final static String DEFAUL_HOST = "time-A.timefreq.bldrdoc.gov";
    private final static int DEFAULT_PORT = 13;

    public static void main(String[] args)
    {
        try
        {
            try (Socket s = new Socket(DEFAUL_HOST, DEFAULT_PORT))
            {
                InputStream inStream = s.getInputStream();
                Scanner in = new Scanner(inStream);
                while (in.hasNextLine())
                {
                    String line = in.nextLine();
                    System.out.println(line);
                }
            }
        }
        catch (UnknownHostException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Данная программа демонстрирует пример использования класса InetAddress. Вам необходимо задать имя
 * хоста в качестве аргумента командной строки или запустить прорамму без аргументов командной
 * строки, чтобы посмотреть адрес локального хоста.
 * <p/>
 * @version 1.01 2001-06-26
 * @author Cay Horstmann
 */
public class InetAddressTest
{
    public static void main(String[] args)
    {
        try
        {
            if (args.length > 0)
            {
                String host = args[0];  // F.E. google.com
                InetAddress[] addresses = InetAddress.getAllByName(host);
                for (InetAddress a : addresses)
                {
                    System.out.println(a);
                }
            }
            else
            {
                InetAddress localHostAddress = InetAddress.getLocalHost();
                System.out.println(localHostAddress);
            }
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
    }
}

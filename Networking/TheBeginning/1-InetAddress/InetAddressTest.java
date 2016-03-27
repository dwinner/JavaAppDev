// Класс InetAddress.

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest
{
    public static void main(String args[]) throws UnknownHostException
    {
        InetAddress address = InetAddress.getLocalHost();
        System.out.println(address);
        address = InetAddress.getByName("starware.com");
        System.out.println(address);
        InetAddress SW[] = InetAddress.getAllByName("www.nba.com");
        for (InetAddress inetAddress : SW)
        {
            System.out.println(inetAddress);
        }
    }
}

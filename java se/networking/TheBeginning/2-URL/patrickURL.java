// Класс URL.

import java.net.MalformedURLException;
import java.net.URL;

public class patrickURL
{
    public static void main(String args[]) throws MalformedURLException
    {
        URL hp = new URL("http://www.starware.com/people/naughton/");

        System.out.println("Protocol: " + hp.getProtocol());
        System.out.println("Port: " + hp.getPort());
        System.out.println("Host: " + hp.getHost());
        System.out.println("File: " + hp.getFile());
        System.out.println("URL: " + hp.toExternalForm());
    }
}

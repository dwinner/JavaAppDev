// Класс URLConnection.

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class UCDemo
{
    public static void main(String args[]) throws Exception
    {
        int c;
        URL hp = new URL("http://www.google.com");
        URLConnection hpCon = hp.openConnection();

        System.out.println("Date: " + new Date(hpCon.getDate()));
        System.out.println("Content Type: " + hpCon.getContentType());
        System.out.println("Expires date: " + hpCon.getExpiration());
        System.out.println("Last modifying: " + new Date(hpCon.getLastModified()));
        
        int len = hpCon.getContentLength();
        System.out.println("Content-Length: " + len);
        
        if (len > 0)
        {
            System.out.println("=== Content ===");
            try (InputStream input = hpCon.getInputStream())
            {
                int i = len;
                while (((c = input.read()) != -1) && (--i > 0))
                {
                    System.out.print((char) c);
                }
            }
        }
        else
        {
            System.out.print("No content");
        }
    }
}

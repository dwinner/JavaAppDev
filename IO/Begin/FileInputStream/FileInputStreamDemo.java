// Класс FileInputStream.
import java.io.*;

public class FileInputStreamDemo
{
    public static void main(String args[]) throws Exception
    {
        int size;
        try (InputStream f = new FileInputStream("FileInputStreamDemo.java"))
        {
            System.out.println("Summary available bytes: " + (size = f.available()));

            int n = size / 40;
            System.out.println("First " + n + " bytes one read() reads immediately");
            for (int i = 0; i < n; i++)
            {
                System.out.println((char) f.read());
            }
            System.out.println("Also available: " + f.available());
            System.out.println("Reading next " + n + " with help of one read(b[])");
            byte b[] = new byte[n];
            if (f.read(b) != n)
            {
                System.err.println("couldn't read " + n + " bytes.");
            }
            System.out.println(new String(b, 0, n));
            System.out.println("\nAlso available: " + (size = f.available()));
            System.out.println("Skip a half of rest bytes skip()");
            f.skip(size / 2);
            System.out.println("Also available: " + f.available());
            System.out.println("Reading " + n / 2 + " bytes in the end of the array");
            if (f.read(b, n / 2, n / 2) != n / 2)
            {
                System.err.println("couldn't read " + n / 2 + " bytes.");
            }
            System.out.println(new String(b, 0, b.length));
            System.out.println("\nAlso available: " + f.available());
        }
    }
}

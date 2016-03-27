// Класс ByteArrayOutputStream.
import java.io.*;

public class ByteArrayOutputStreamDemo
{
    public static void main(String args[]) throws IOException
    {
        try (ByteArrayOutputStream f = new ByteArrayOutputStream())
        {
            String s = "This should end up in the array";
            byte buf[] = s.getBytes();
            f.write(buf);
            System.out.println("Buffer as a string form contains:");
            System.out.println(f.toString());
            System.out.println("Array contains:");
            byte b[] = f.toByteArray();
            for (int i = 0; i < b.length; i++)
            {
                System.out.print((char) b[i]);
            }
            
            System.out.println("\nBuffer output in OutputStream");
            try (OutputStream f2 = new FileOutputStream("test.txt"))
            {
                f.writeTo(f2);
            }
            System.out.println("Using reset()");
            f.reset();
            for (int i = 0; i < 3; i++)
            {
                f.write('X');
            }
            System.out.println(f.toString());
        }
    }
}

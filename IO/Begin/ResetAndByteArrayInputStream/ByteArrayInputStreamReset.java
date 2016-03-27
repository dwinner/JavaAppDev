// —брос входного потока в ByteArrayInputStream.
import java.io.*;

public class ByteArrayInputStreamReset
{
    public static void main(String args[]) throws IOException
    {
        String tmp = "abc";
        byte b[] = tmp.getBytes();
        try (ByteArrayInputStream in = new ByteArrayInputStream(b))
        {
            for (int i = 0; i < 2; i++)
            {
                int c;
                while ((c = in.read()) != -1)
                {
                    if (i == 0)
                    {
                        System.out.print((char) c);
                    }
                    else
                    {
                        System.out.print(Character.toUpperCase((char) c));
                    }
                }
                System.out.println();
                in.reset();
            }
        }
    }
}

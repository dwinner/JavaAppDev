// Класс FileOutputStream.
import java.io.*;

public class FileOutputStreamDemo
{
    public static void main(String args[]) throws Exception
    {
        String source = "Теперь настало время всем порядочным людям\n"
            + " стране прийти на помощь своей\n"
            + " и платить налоги.";
        byte buf[] = source.getBytes();
        try (OutputStream f0 = new FileOutputStream("file1.txt"))
        {
            for (int i = 0; i < buf.length; i += 2)
            {
                f0.write(buf[i]);
            }
        }
        try (OutputStream f1 = new FileOutputStream("file2.txt"))
        {
            f1.write(buf);
        }
        try (OutputStream f2 = new FileOutputStream("file3.txt"))
        {
            f2.write(buf, buf.length - buf.length / 4, buf.length / 4);
        }
    }
}

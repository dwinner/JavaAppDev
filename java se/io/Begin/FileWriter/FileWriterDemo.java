// Класс FileWriter.
import java.io.*;

public class FileWriterDemo
{
    public static void main(String args[]) throws Exception
    {
        String source = "Теперь настало время всем порядочным людям\n"
            + " стране прийти на помощь своей\n"
            + " и платить налоги.";
        char buffer[] = new char[source.length()];
        source.getChars(0, source.length(), buffer, 0);
        try (FileWriter f0 = new FileWriter("file1.txt"))
        {
            for (int i = 0; i < buffer.length; i += 2)
            {
                f0.write(buffer[i]);
            }
        }
        try (FileWriter f1 = new FileWriter("file2.txt"))
        {
            f1.write(buffer);
        }
        try (FileWriter f2 = new FileWriter("file3.txt"))
        {
            f2.write(buffer, buffer.length - buffer.length / 4, buffer.length / 4);
        }
    }
}

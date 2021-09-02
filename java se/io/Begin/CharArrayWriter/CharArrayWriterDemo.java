// Класс CharArrayWriter.
import java.io.CharArrayWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CharArrayWriterDemo
{
    public static void main(String args[]) throws IOException
    {
        try (CharArrayWriter f = new CharArrayWriter())
        {
            String s = "This should end up in the array";
            char buf[] = new char[s.length()];

            s.getChars(0, s.length(), buf, 0);
            f.write(buf);
            System.out.println("Buffer in a string form contains:");
            System.out.println(f.toString());
            System.out.println("Array contains:");

            char c[] = f.toCharArray();
            for (int i = 0; i < c.length; i++)
            {
                System.out.print(c[i]);
            }

            System.out.println("\n Buffer output in FileWriter");
            try (FileWriter f2 = new FileWriter("test.txt"))
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

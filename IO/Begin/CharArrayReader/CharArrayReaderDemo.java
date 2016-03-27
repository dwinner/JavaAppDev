// Класс CharArrayReader.
import java.io.CharArrayReader;
import java.io.IOException;

public class CharArrayReaderDemo
{
    public static void main(String args[]) throws IOException
    {
        String tmp = "abcdefghijkmnopqrstuvwxyz";
        int length = tmp.length();
        char c[] = new char[length];

        tmp.getChars(0, length, c, 0);

        try (CharArrayReader input1 = new CharArrayReader(c);
             CharArrayReader input2 = new CharArrayReader(c, 0, 5))
        {
            int i;
            System.out.println("input1 contains:");
            while ((i = input1.read()) != -1)
            {
                System.out.print((char) i);
            }
            System.out.println();
            System.out.println("input2 contains:");
            while ((i = input2.read()) != -1)
            {
                System.out.print((char) i);
            }
            System.out.println();
        }

    }
}

// Класс PushbackInputStream, вернуть обратно.
import java.io.*;

public class PushbackInputStreamDemo
{
    public static void main(String args[]) throws IOException
    {
        String s = "if (a == 4) a = 0;\n";
        byte buf[] = s.getBytes();
        try (PushbackInputStream f = new PushbackInputStream(new ByteArrayInputStream(buf)))
        {
            int c;

            while ((c = f.read()) != -1)
            {
                switch (c)
                {
                    case '=':
                        if ((c = f.read()) == '=')
                        {
                            System.out.print(".eq.");
                        }
                        else
                        {
                            System.out.print("<-");
                            f.unread(c);
                        }
                        break;
                    default:
                        System.out.print((char) c);
                        break;
                }
            }
        }
    }
}

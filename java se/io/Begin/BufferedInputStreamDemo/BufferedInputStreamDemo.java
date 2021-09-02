// Использование буферизованного ввода.
import java.io.*;

public class BufferedInputStreamDemo
{
    public static void main(String args[]) throws IOException
    {
        String s = "This: &copy; is a symbol of the author right, and this: &copy - no.\n";
        byte buf[] = s.getBytes();
        try (BufferedInputStream f = new BufferedInputStream(new ByteArrayInputStream(buf)))
        {
            int c;
            boolean marked = false;

            while ((c = f.read()) != -1)
            {
                switch (c)
                {
                    case '&':
                        if (!marked)
                        {
                            f.mark(32);
                            marked = true;
                        }
                        else
                        {
                            marked = false;
                        }
                        break;
                    case ';':
                        if (marked)
                        {
                            marked = false;
                            System.out.print(c);
                        }
                        else
                        {
                            System.out.print((char) c);
                        }
                        break;
                    case ' ':
                        if (marked)
                        {
                            marked = false;
                            f.reset();
                            System.out.print("&");
                        }
                        else
                        {
                            System.out.print((char) c);
                        }
                        break;
                    default:
                        if (!marked)
                        {
                            System.out.print((char) c);
                        }
                        break;
                }
            }
        }
    }
}

// ������������� ��������������� �����.
import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;

public class BufferedReaderDemo
{
    public static void main(String args[]) throws IOException
    {
        String s = "This is a &copy; copyright symbol but this is &copy not.\n";
        char buf[] = new char[s.length()];
        s.getChars(0, s.length(), buf, 0);
        try (BufferedReader f = new BufferedReader(new CharArrayReader(buf)))
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

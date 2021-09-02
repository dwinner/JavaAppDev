import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * ����������� ����� � �������������� ����� ������.
 * <p/>
 * @version 1.00 1997-09-10
 * @author Cay Horstmann
 */
public class Caesar
{
    public static void main(String[] args)
    {
        if (args.length != 3)
        {
            System.out.println("USAGE: java Caesar in out key");
            return;
        }

        try
        {
            FileOutputStream out = null;
            try (FileInputStream in = new FileInputStream(args[0]))
            {
                out = new FileOutputStream(args[1]);
                int key = Integer.parseInt(args[2]);
                int ch;
                while ((ch = in.read()) != -1)
                {
                    byte c = (byte) (ch + key);
                    out.write(c);
                }
            }
            finally
            {
                if (out != null)
                {
                    out.close();
                }
            }
        }
        catch (Exception e)
        {
        }
    }
}

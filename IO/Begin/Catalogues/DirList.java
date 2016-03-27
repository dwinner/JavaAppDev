// Использование каталогов.
import java.io.File;

public class DirList
{
    public static void main(String args[])
    {
        String dirname = "..";
        File f1 = new File(dirname);

        if (f1.isDirectory())
        {
            System.out.println("Catalogue " + dirname);
            String s[] = f1.list();

            for (int i = 0; i < s.length; i++)
            {
                File f = new File(dirname + "/" + s[i]);
                if (f.isDirectory())
                {
                    System.out.println(s[i] + " is catalogue");
                }
                else
                {
                    System.out.println(s[i] + " is file");
                }
            }
        }
        else
        {
            System.out.println(dirname + " is not a catalogue");
        }
    }
}

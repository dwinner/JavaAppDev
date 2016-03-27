// Èםעונפויס FilenameFilter.
import java.io.*;

class OnlyExt implements FilenameFilter
{
    private String ext;

    OnlyExt(String anExt)
    {
        ext = "." + anExt;
    }

    @Override
    public boolean accept(File dir, String name)
    {
        return name.endsWith(ext);
    }
}

public class DirListOnly
{
    public static void main(String args[])
    {
        String dirname = "..";
        File f1 = new File(dirname);
        FilenameFilter only = new OnlyExt("dll");
        String s[] = f1.list(only);

        for (int i = 0; i < s.length; i++)
        {
            System.out.println(s[i]);
        }
    }
}

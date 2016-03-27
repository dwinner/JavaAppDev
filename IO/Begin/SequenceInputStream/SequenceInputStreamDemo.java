// Последовательный ввод.
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

class InputStreamEnumerator implements Enumeration
{
    private Enumeration<?> files;

    InputStreamEnumerator(@SuppressWarnings("UseOfObsoleteCollectionType") Vector<?> files)
    {
        this.files = files.elements();
    }

    @Override
    public boolean hasMoreElements()
    {
        return files.hasMoreElements();
    }

    @Override
    public Object nextElement()
    {
        try
        {
            return new FileInputStream(files.nextElement().toString());
        }
        catch (Exception e)
        {
            return null;
        }
    }
}

public class SequenceInputStreamDemo
{
    public static void main(String args[]) throws Exception
    {
        int c;
        @SuppressWarnings("UseOfObsoleteCollectionType")
        Vector<String> files = new Vector<>();

        files.addElement("C:\\AUTOEXEC.BAT");
        files.addElement("C:\\CONFIG.SYS");

        InputStreamEnumerator e = new InputStreamEnumerator(files);
        try (@SuppressWarnings("unchecked") InputStream input = new SequenceInputStream(e))
        {
            while ((c = input.read()) != -1)
            {
                System.out.print((char) c);
            }
        }
    }
}

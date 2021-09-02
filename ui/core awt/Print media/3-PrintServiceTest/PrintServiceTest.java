
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.print.*;

/**
 * ������ ������������� ����� ������. ��������� ��������� ������� ����������� GIF,
 * ��������� ����� ������ ������, ������� ������������ ������ ��� ���������.
 * <p/>
 * @version 1.10 2007-08-16
 * @author Cay Horstmann
 */
public class PrintServiceTest
{
    public static void main(String[] args)
    {
        DocFlavor flavor = DocFlavor.URL.GIF;
        PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, null);
        if (args.length == 0)
        {
            if (services.length == 0)
            {
                System.out.println("No printer for flavor " + flavor);
            }
            else
            {
                System.out.println("Specify a file of flavor " + flavor
                    + "\nand optionally the number of the desired printer.");
                for (int i = 0; i < services.length; i++)
                {
                    System.out.println((i + 1) + ": " + services[i].getName());
                }
            }
            System.exit(0);
        }
        String fileName = args[0];
        int p = 1;
        if (args.length > 1)
        {
            p = Integer.parseInt(args[1]);
        }
        try
        {
            if (fileName == null)
            {
                return;
            }
            FileInputStream in = new FileInputStream(fileName);
            Doc doc = new SimpleDoc(in, flavor, null);
            DocPrintJob job = services[p - 1].createPrintJob();
            job.print(doc, null);
        }
        catch (FileNotFoundException | PrintException e)
        {
            e.printStackTrace();
        }
    }
}
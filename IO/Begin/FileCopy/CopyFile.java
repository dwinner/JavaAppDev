// Копирование файла.
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile
{
    public static void main(String args[]) throws IOException
    {
        int i;
        FileInputStream fin;
        FileOutputStream fout;

        try
        {
            try
            {	// открыть файл для ввода
                fin = new FileInputStream(args[0]);
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Source file is not found");
                return;
            }

            try
            {	// открыть файл для вывода
                if (args[0].equals(args[1]))
                {
                    IOException ioEx = new IOException("In and out files are the same unit");
                    throw new RuntimeException(ioEx);
                }
                fout = new FileOutputStream(args[1]);
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Error opening an output-file");
                return;
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("CopyFile copies input-file to output-file");
            return;
        }

        // копировать файл
        try
        {
            do
            {
                i = fin.read();
                if (i != -1)
                {
                    fout.write(i);
                }
            }
            while (i != -1);
        }
        catch (IOException e)
        {
            System.out.println("File error: " + e);
        }

        fin.close();
        fout.close();
    }
}
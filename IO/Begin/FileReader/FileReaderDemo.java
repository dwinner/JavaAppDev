// Класс FileReader.
import java.io.BufferedReader;
import java.io.FileReader;

public class FileReaderDemo
{
    public static void main(String args[]) throws Exception
    {
        try (FileReader fr = new FileReader("FileReaderDemo.java"))
        {
            BufferedReader br = new BufferedReader(fr);
            String s;

            while ((s = br.readLine()) != null)
            {
                System.out.println(s);
            }
        }
    }
}

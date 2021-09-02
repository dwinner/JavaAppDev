// Чтение строк с консоли, используя BufferedReader.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BRReadLines
{
    public static void main(String args[]) throws IOException
    {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in)))
        {
            String str;

            System.out.println("Enter the strings of the text");
            System.out.println("Enter 'stop' for exit.");

            do
            {
                str = br.readLine();
                System.out.println(str);
            }
            while (!str.equals("stop"));
        }
    }
}

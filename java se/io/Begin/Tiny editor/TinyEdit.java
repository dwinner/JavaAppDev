// Крошечный редактор.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TinyEdit
{
    public static void main(String args[]) throws IOException
    {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in)))
        {
            String str[] = new String[100];

            System.out.println("Enter the strings of the text.");
            System.out.println("Enter 'stop' for exit");

            for (int i = 0; i < 100; i++)
            {
                str[i] = br.readLine();
                if (str[i].equals("stop"))
                {
                    break;
                }
            }

            System.out.println("\nHere is your file:");

            // Вывести строки на экран.
            for (int i = 0; i < 100; i++)
            {
                if (str[i].equals("stop"))
                {
                    break;
                }
                System.out.println(str[i]);
            }
        }
    }
}

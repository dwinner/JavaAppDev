// AvailableCharSets.java : Перечисление кодировок и их символических имен
import java.nio.charset.*;
import java.util.*;
import java.io.*;

public class AvailableCharSets
{
    public static void main(String[] args) throws FileNotFoundException
    {
        SortedMap<String,Charset> charSets = Charset.availableCharsets();
        Iterator<String> it = charSets.keySet().iterator();
        
        PrintStream out =
            new PrintStream(
                new BufferedOutputStream(
                    new FileOutputStream("charsets.txt")
                ),
                true
            );
        System.setOut(out);
        
        while (it.hasNext())
        {
            String csName = it.next();
            System.out.println(csName);
            
            Iterator aliases = charSets.get(csName).aliases().iterator();
            if (aliases.hasNext())
                System.out.print(": ");
            while (aliases.hasNext())
            {
                System.out.print(aliases.next());
                
                if (aliases.hasNext())
                    System.out.print(", ");
            }
            System.out.println();
        }
    }
}
// Использование метода split().
import java.util.regex.*;

public class RegExpr9
{
    public static void main(String args[])
    {
        // Сопоставление слов в нижнем регистре.
        Pattern pat = Pattern.compile("[ ,.!]");

        String strs[] = pat.split("one two,alpha9 12!done.");

        for (int i = 0; i < strs.length; i++)
        {
            System.out.println("Next leksem: " + strs[i]);
        }
    }
}
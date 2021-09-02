// Использование метода find() для нахождения подпоследовательности.
import java.util.regex.*;

public class RegExpr2
{
    public static void main(String args[])
    {
        Pattern pat = Pattern.compile("Java");
        Matcher mat = pat.matcher("Java SE 6.");

        System.out.println("Searching Java in " + mat.toString());

        if (mat.find())
        {
            System.out.println("Subsequence is found");
        }
        else
        {
            System.out.println("Matches are not found");
        }
    }
}
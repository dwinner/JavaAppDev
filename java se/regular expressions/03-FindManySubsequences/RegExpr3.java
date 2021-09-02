// Использование метода find() для нахождения нескольких подпоследовательностей.
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpr3
{
    public static void main(String args[])
    {
        Pattern pat = Pattern.compile("test");
        Matcher mat = pat.matcher("test 1 2 3 test");

        while (mat.find())
        {
            System.out.println("test is found on indexes " + mat.start() + " " + mat.end());
            System.out.println("Current found string is: " + mat.group());
        }
    }
}
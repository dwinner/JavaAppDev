import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Группы и квантификаторы : Groups.java
public class Groups
{
    public static void main(String[] args)
    {
        /*
         * Output: First group: abdcxy Second group: z
         *
         * First group: a Second group: bdcxy
         *
         * First group: abdcxyz Second group:
         *
         * nothing
         */
        String input = "abdcxyz";
        myMatches("([a-z]*)([a-z]+)", input);
        myMatches("([a-z]?)([a-z]+)", input);
        myMatches("([a-z]+)([a-z]*)", input);
        myMatches("([a-z]?)([a-z]?)", input);
    }

    public static void myMatches(String regex, String input)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches())
        {
            System.out.println("First group: " + matcher.group(1));
            System.out.println("Second group: " + matcher.group(2));
        }
        else
        {
            System.out.println("nothing");
        }
        System.out.println();
    }
}
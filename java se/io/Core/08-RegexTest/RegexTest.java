
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Эта программа позволяет сопоставлять регулярные выражения. Для выполнения сопоставления нужно
 * ввести шаблон и подлежащие сопоставлению с ним строки, а для выхода - пустую строку. Если в
 * шаблоне присутствуют группы, в совпадении отображаются границы этих групп.
 * <p/>
 * @version 1.01 2004-05-11
 * @author Cay Horstmann
 */
public class RegexTest
{
    public static void main(String[] args)
    {
        String patternString = null;

        System.out.println("Do you want to enter your own manually patern? (yes|no)");
        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();

        switch (answer.trim().toLowerCase())
        {
            case "yes":
                System.out.println("Enter pattern: ");
                patternString = in.nextLine();
                break;
            case "no":
                patternString = "((1?[0-9]):([0-5][0-9]))[ap]m";
                System.out.print("So I will use the next pattern string for time matching: ");
                System.out.println(patternString);
                break;
            default:
                System.out.println("Try again later");
                System.exit(0);
        }

        Pattern pattern = null;
        try
        {
            pattern = Pattern.compile(patternString);
        }
        catch (PatternSyntaxException e)
        {   // Ошибка в синтаксисе шаблона
            System.out.println("Pattern syntax error");
            System.exit(1);
        }

        while (true)
        {
            System.out.println("Enter string to match: ");
            // Введите подлежащую сопоставлению строку
            String input = in.nextLine();
            if (input == null || "".equals(input))
            {
                return;
            }
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches())
            {
                System.out.println("Match");    // Совпадает
                int g = matcher.groupCount();
                if (g > 0)
                {
                    for (int i = 0; i < input.length(); i++)
                    {
                        for (int j = 1; j <= g; j++)
                        {
                            if (i == matcher.start(j))
                            {
                                System.out.print('(');
                            }
                        }
                        System.out.print(input.charAt(i));
                        for (int j = 0; j <= g; j++)
                        {
                            if (i + 1 == matcher.end(j))
                            {
                                System.out.print(')');
                            }
                        }
                        System.out.println();
                    }
                }
            }
            else
            {   // Совпадений нет
                System.out.println("No match");
            }
        }
    }
}

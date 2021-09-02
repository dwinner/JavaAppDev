// Обработка строк с помощью шаблонов

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DemoRegular
{
    public static void main(String[] args)
    {
        // проверка на соответствие строки шаблону
        Pattern p1 = Pattern.compile("a+y");
        Matcher m1 = p1.matcher("aaay");
        boolean b = m1.matches();
        System.out.println(b);	// true

        // поиск и выбор подстроки, заданной шаблоном
        String regex = "(\\w+)@(\\w+\\.)(\\w+)(\\.\\w+)*";
        String s = "email address: dwinner@inbox.ru и bboytronik@gmail.com";
        Pattern p2 = Pattern.compile(regex);
        Matcher m2 = p2.matcher(s);
        /*
         * Output: e-mail: dwinner@inbox.ru e-mail: bboytronik@gmail.com
         */
        while (m2.find())
        {
            System.out.println("e-mail: " + m2.group());
        }

        // разбивка строки на подстроки с применением шаблона в качестве разделителя
        Pattern p3 = Pattern.compile("\\d+\\s?");
        String[] words = p3.split("java5tiger 77 java6mustang");
        /*
         * Output: java tiger java mustang
         */
        for (String word : words)
        {
            System.out.println(word);
        }
    }
}
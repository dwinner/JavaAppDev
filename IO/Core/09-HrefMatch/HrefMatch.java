import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Эта программа отображает все имеющиеся на Web-странице URL-адреса путем отыскивания совпадений
 * для регулярного выражения, которое описывает HTML-дескриптор <a href=...>. Запускается она
 * следующим образом: java HrefMatch URL
 * <p/>
 * @version 1.01 2004-06-04
 * @author Cay Horstmann
 */
public class HrefMatch
{
    public static void main(String[] args)
    {
        try
        {
            // Извлечение строки URL из командной строки
            // или использование стандартного URL
            String urlString = args.length > 0 ? args[0] : "http://java.sun.com";
            
            // Открытие считывателя для URL
            InputStreamReader in = new InputStreamReader(new URL(urlString).openStream());
            
            // Считывание содержимого в строковый конструктор
            StringBuilder input = new StringBuilder();
            int ch;
            while ((ch = in.read()) != -1)
            {                
                input.append((char) ch);
            }
            
            // Поиск всех вхождений шаблона
            String patternString = "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>])\\s*>";
            Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            
            while (matcher.find())
            {                
                int start = matcher.start();
                int end = matcher.end();
                String match = input.substring(start, end);
                System.out.println(match);
            }
        }
        catch (IOException | PatternSyntaxException e)
        {
            e.printStackTrace();
        }
    }
}

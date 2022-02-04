import java.util.*;

/**
 * "Ручное" профилирование кода.
 * @version 1.10 2000-06-03
 * @author Cay Horstmann
 */
public class ExceptionalTest
{
    public static void main(String[] args)
    {
        int i = 0;
        int ntry = 10000000;
        Stack<String> s = new Stack<String>();
        long s1;
        long s2;

        // Следим за пустотой стека ntry-раз
        System.out.println("Testing for empty stack");
        s1 = new Date().getTime();
        for (i = 0; i <= ntry; i++)
            if (!s.empty())
                s.pop();
        s2 = new Date().getTime();
        System.out.println((s2 - s1) + " milliseconds");

        // Делаем то же самое, исключая проверку, но "бросая" исключение
        System.out.println("Catching EmptyStackException");
        s1 = new Date().getTime();
        for (i = 0; i <= ntry; i++)
        {
            try
            {
                s.pop();
            }
            catch (EmptyStackException e)
            {
            }
        }
        s2 = new Date().getTime();
        System.out.println((s2 - s1) + " milliseconds");
    }
}

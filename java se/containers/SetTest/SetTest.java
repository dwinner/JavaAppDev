
import java.util.*;

/**
 * Эта программа использует набор для распечатки всех уникальных слов из System.in.
 * @version 1.10 2003-08-02
 * @author Cay Horstmann
 */
public class SetTest
{
    public static void main(String[] args)
    {
        Set<String> words = new HashSet<>(); // HashSet реализует Set
        long totalTime = 0;

        Scanner in = new Scanner(System.in);
        while (in.hasNext())
        {
            String word = in.next();
            long callTime = System.currentTimeMillis();
            words.add(word);
            callTime = System.currentTimeMillis() - callTime;
            totalTime += callTime;
        }

        Iterator<String> iter = words.iterator();
        for (int i = 1; i <= 20; i++)
        {
            System.out.println(iter.next());
        }
        System.out.println(". . .");
        System.out.println(words.size() + " distinct words. " + totalTime + " milliseconds.");
    }
}

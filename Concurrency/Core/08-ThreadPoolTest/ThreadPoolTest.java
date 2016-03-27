import java.io.File;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Пулы потоков.
 * @author dwinner@inbox.ru
 */
public class ThreadPoolTest
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Specify a base directory (for example, c:\\JDK\\scr): ");
        String directory = in.nextLine();
        System.out.print("Enter a keyword (for example, volatile): ");
        String keyword = in.nextLine();
        ExecutorService pool = Executors.newCachedThreadPool();
        MatchCounter counter = new MatchCounter(new File(directory), keyword, pool);
        Future<Integer> result = pool.submit(counter);
        try
        {
            System.out.println(result.get() + " files found");
        }
        catch (ExecutionException | InterruptedException e)
        {
            if (e instanceof ExecutionException)
            {
                e.printStackTrace();
            }
            else
            {
                throw new RuntimeException(e);
            }
        }
        pool.shutdown();
        int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize();
        System.out.println("Maximum size of pool = " + largestPoolSize);
    }
}

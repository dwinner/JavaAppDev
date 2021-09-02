import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Возврат значений из асинхронных задач.
 * @version 1.0 2004-08-01
 * @author Cay Horstmann
 */
public class FutureTest
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Specify the base directory (for example, c:\\JDK\\src): ");
        String directory = in.nextLine();
        System.out.print("Enter the keyword (for example, volatile): ");
        String keyword = in.nextLine();
        MatchCounter counter = new MatchCounter(new File(directory), keyword);
        FutureTask<Integer> task = new FutureTask<>(counter);
        Thread t = new Thread(task);
        t.start();
        try
        {
            System.out.println(task.get() + " files found");
        }
        catch (InterruptedException | ExecutionException e)
        {
            throw new RuntimeException(e);
        }
    }
}

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Демонстрация блокирующих очередей.
 * @version 1.0 2004-08-01
 * @author Cay Horstmann
 */
public class BlockingQueueTest
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Specify the base directory (for example, c:\\JDK\\src): ");
        String directory = in.nextLine();
        System.out.print("Enter the keyword (for example, volatile): ");
        String keyword = in.nextLine();
        final int FILE_QUEUE_SIZE = 10;
        final int SEARCH_THREADS = 100;
        BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);
        FileEnumerationTask enumerator = new FileEnumerationTask(queue, new File(directory));
        new Thread(enumerator).start();
        for (int i = 0; i <= SEARCH_THREADS; i++)
        {
            new Thread(new SearchTask(queue, keyword)).start();
        }
    }
}

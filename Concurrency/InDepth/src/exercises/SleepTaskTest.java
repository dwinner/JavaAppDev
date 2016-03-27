package exercises;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 22.11.12
 * Time: 18:43
 * Упражнение 6: "Уснувшие" потоки выполнения.
 */
public class SleepTaskTest
{
   public static void main(String[] args)
   {
      ExecutorService service = Executors.newCachedThreadPool();
      for (int i = 0; i < 10; i++)
         service.execute(new SleepTask());
      service.shutdown();
   }
}

class SleepTask implements Runnable
{
   @Override
   public void run()
   {
      int sleepTime = random.nextInt(MAX_SLEEP_SECONDS);
      long startSleepTime = System.nanoTime();
      try
      {
         TimeUnit.SECONDS.sleep(sleepTime);
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }
      long endSleepTime = System.nanoTime();
      System.out.println("Start time: " + startSleepTime + ". End time: " + endSleepTime);
   }

   final int MAX_SLEEP_SECONDS = 10;
   private static Random random = new Random(47);
}

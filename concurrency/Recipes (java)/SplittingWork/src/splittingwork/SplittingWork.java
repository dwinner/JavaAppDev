package splittingwork;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Разделение работы по разным потокам.
 *
 * @author Denis
 */
public class SplittingWork
{
   public static void main(String[] args) throws InterruptedException
   {
      SplittingWork work = new SplittingWork();
      work.start();
   }

   private void start() throws InterruptedException
   {
      BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
      for (int i = 0; i < 10; i++)
      {
         final int localI = i;
         queue.add(new Runnable()
         {
            @Override
            public void run()
            {
               doExpensiveOperation(localI);
            }

         });
      }
      ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 1000, TimeUnit.MILLISECONDS,
                                                           queue);
      executor.prestartAllCoreThreads();
      executor.shutdown();
      executor.awaitTermination(100000, TimeUnit.SECONDS);
      System.out.println("Look ma! All operations were completed");
   }

   // Затратная операция.
   private void doExpensiveOperation(int index)
   {
      System.out.println("Starting expensive operation " + index);
      try
      {
         Thread.sleep(index * 1000);
      }
      catch (InterruptedException ex)
      {
         Logger.getLogger(SplittingWork.class.getName()).log(Level.SEVERE, null, ex);
      }
      System.out.println("Ending expensive operation " + index);
   }

}

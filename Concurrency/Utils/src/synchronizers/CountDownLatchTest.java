package synchronizers;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 03.11.12
 * Time: 23:06
 * Защелка с обратным отсчетом.
 */
public class CountDownLatchTest
{
   public static void main(String[] args)
   {
      CountDownLatch countDownLatch = new CountDownLatch(5);
      System.out.println("Starting");
      new SimpleThread(countDownLatch);

      try
      {
         countDownLatch.await();
      }
      catch (InterruptedException e)
      {
         Thread.currentThread().interrupt();
         Logger.getLogger(
           CountDownLatchTest.class.getSimpleName()).log(Level.SEVERE, e.getMessage(), e);
      }

      System.out.println("Done");
   }
}

class SimpleThread implements Runnable
{
   SimpleThread(CountDownLatch latch)
   {
      this.latch = latch;
      new Thread(this).start();
   }

   @Override
   public void run()
   {
      for (int i = 0; i < 5; i++)
      {
         System.out.println(i);
         latch.countDown();   // Уменьшаем счетчик защелки
      }
   }

   private CountDownLatch latch;
}
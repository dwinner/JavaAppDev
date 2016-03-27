package exercises;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Корретное завершение задачи после прерывания.
 *
 * @author Denis
 */
public class ForceSleepInterrupt
{
   public static void main(String[] args) throws InterruptedException
   {
      Thread aThread = new Thread(new ForceSleepTask(new ForceSleep()));
      aThread.start();
      TimeUnit.MILLISECONDS.sleep(500);
      aThread.interrupt();
      TimeUnit.MILLISECONDS.sleep(500);
   }

}

class ForceSleep
{
   ForceSleep()
   {
      id = Math.abs(random.nextInt());
   }

   void sleepForce()
   {
      System.out.println("Before long sleep...");
      try
      {
         TimeUnit.SECONDS.sleep(100);
      }
      catch (InterruptedException ex)
      {
         System.out.println(id + " interrupted");
      }
      System.out.println(id + " exit");
   }

   private int id;
   private Random random = new Random(47);
}

class ForceSleepTask implements Runnable
{
   ForceSleepTask(ForceSleep forceSleep)
   {
      this.forceSleep = forceSleep;
   }

   @Override
   public void run()
   {
      forceSleep.sleepForce();
   }

   private ForceSleep forceSleep;
}

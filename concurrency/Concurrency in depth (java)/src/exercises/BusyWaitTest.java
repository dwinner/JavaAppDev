package exercises;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Время простоя координирующихся задач.
 *
 * @author Denis
 */
public class BusyWaitTest
{
   public static void main(String[] args)
   {
      Coordinator coordinator = new Coordinator();
      ExecutorService exec = Executors.newFixedThreadPool(2);
      exec.execute(new FirstCooperator(coordinator));
      exec.execute(new SecondCooperator(coordinator));
      exec.shutdown();
   }

}

class Coordinator
{
   synchronized void sleepForAWhile() throws InterruptedException
   {
      TimeUnit.MILLISECONDS.sleep(random.nextInt(MAX_SLEEP_TIME_IN_MILLISECONDS));
      turnToWait();
   }

   synchronized void mustWait() throws InterruptedException
   {
      long startTime = System.nanoTime();
      while (!waitFlag)
      {
         wait();
      }
      turnToRelease();
      long endTime = System.nanoTime();
      System.out.printf("Time out. Wait time: %s seconds\n",
                        TimeUnit.MILLISECONDS.toSeconds(endTime - startTime));
   }

   void turnToWait()
   {
      waitFlag = true;
   }

   void turnToRelease()
   {
      waitFlag = false;
   }

   private volatile boolean waitFlag = true;
   private Random random = new Random(47);
   private final static int MAX_SLEEP_TIME_IN_MILLISECONDS = 10000;
}

class FirstCooperator implements Runnable
{
   FirstCooperator(Coordinator coordinator)
   {
      this.coordinator = coordinator;
   }

   @Override
   public void run()
   {
      try
      {
         coordinator.sleepForAWhile();
      }
      catch (InterruptedException ex)
      {
         Logger.getLogger(FirstCooperator.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   private Coordinator coordinator;
}

class SecondCooperator implements Runnable
{
   SecondCooperator(Coordinator coordinator)
   {
      this.coordinator = coordinator;
   }

   @Override
   public void run()
   {
      try
      {
         coordinator.mustWait();
      }
      catch (InterruptedException ex)
      {
         Logger.getLogger(SecondCooperator.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   private Coordinator coordinator;
}

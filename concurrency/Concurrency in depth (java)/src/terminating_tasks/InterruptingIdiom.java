package terminating_tasks;

import java.util.concurrent.TimeUnit;

/**
 * Общая идиома для прерывания задач.
 *
 * @author Denis
 */
public class InterruptingIdiom
{
   public static void main(String[] args) throws InterruptedException
   {
      Thread t = new Thread(new Blocked3());
      t.start();
      TimeUnit.MILLISECONDS.sleep(DELAY_IN_MILLISECONDS);
      t.interrupt();
   }

   private static final int DELAY_IN_MILLISECONDS = 1000;
}

class NeedsCleanup
{
   private final int id;

   NeedsCleanup(int id)
   {
      this.id = id;
      System.out.println(getClass().getName() + " " + id);
   }

   void cleanup()
   {
      System.out.println("Cleaning up " + id);
   }

}

class Blocked3 implements Runnable
{
   private volatile double d = 0.0;

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            // Точка 1
            NeedsCleanup n1 = null;
            /* Начало try-finally сразу после создания объекта n1
             гарантирует корректную очистку для прерванных задач */
            try
            {
               n1 = new NeedsCleanup(1);
               System.out.println("Sleeping");
               TimeUnit.SECONDS.sleep(1);
               // Точка 2
               NeedsCleanup n2 = null;
               // Гарантируем корректную очистку для n2:
               try
               {
                  n2 = new NeedsCleanup(2);
                  System.out.println("Calculating");
                  // Долгая операция
                  for (int i = 0; i < 25000000; i++)
                  {
                     d = d + (Math.PI + Math.E) / d;
                  }
                  System.out.println("Finishing time-consuming operation");
               }
               finally
               {
                  if (n2 != null)
                  {
                     n2.cleanup();
                  }
               }
            }
            finally
            {
               if (n1 != null)
               {
                  n1.cleanup();
               }
            }
         }
      }
      catch (InterruptedException e)
      {
         System.out.println("Exiting via Interrupted Exception");
      }
   }

}

package basic_threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 22.11.12 Time: 18:57 Использование приоритетов потоков.
 */
public class SimplePriorities implements Runnable
{
   private int countDown = 5;
   private volatile double d; // Без оптимизации
   /* private int priority;

    public SimplePriorities(int aPriority)
    {
    priority = aPriority;
    }*/

   @Override
   public String toString()
   {
      return Thread.currentThread() + ": " + countDown;
   }

   @Override
   public void run()
   {
      // Thread.currentThread().setPriority(priority);
      while (true)
      {
         // Высокозатратная прерываемая операция:
         for (int i = 1; i < 1000000; i++)
         {
            d += (Math.PI + Math.E) / (double) i;
            if (i % 1000 == 0)
            {
               Thread.yield();
            }
         }
         System.out.println(this);
         if (--countDown == 0)
         {
            return;
         }
      }
   }

   public static void main(String[] args)
   {
      ExecutorService executorService =
        Executors.newCachedThreadPool(
        new PriorityThreadFactory(PriorityThreadFactory.Priority.HIGH));
      for (int i = 0; i < 5; i++)
      {
         executorService.execute(new SimplePriorities(/*Thread.MIN_PRIORITY*/));
      }
      executorService.execute(new SimplePriorities(/*Thread.MAX_PRIORITY*/));
      executorService.shutdown();
   }

}

/**
 * Фабрика потоков для исполнителей, устанавливающая приориеты 3-х уровней
 */
class PriorityThreadFactory implements ThreadFactory
{
   static enum Priority
   {
      LOW,
      NORMAL,
      HIGH
   }

   PriorityThreadFactory(Priority threadPriority)
   {
      this.threadPriority = threadPriority;
   }

   @Override
   public Thread newThread(Runnable r)
   {
      Thread thread = new Thread(r);
      switch (threadPriority)
      {
         case LOW:
            thread.setPriority(Thread.MIN_PRIORITY);
            break;
         case NORMAL:
            thread.setPriority(Thread.NORM_PRIORITY);
            break;
         case HIGH:
            thread.setPriority(Thread.MAX_PRIORITY);
            break;
         default:
            thread.setPriority(Thread.currentThread().getPriority());
      }
      return thread;
   }

   private Priority threadPriority;
}

package sharing_resources;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Использование атомарных классов.
 *
 * @author Denis
 */
public class AtomicIntegerTest implements Runnable
{
   private AtomicInteger atomicInteger = new AtomicInteger(0);

   public int getValue()
   {
      return atomicInteger.get();
   }

   private void evenIncrement()
   {
      atomicInteger.addAndGet(2);
   }

   @Override
   public void run()
   {
      while (true)
      {
         evenIncrement();
      }
   }

   public static void main(String[] args)
   {
      new Timer().schedule(new TimerTask()
      {
         @Override
         public void run()
         {
            System.err.println("Aborting");
            System.exit(0);
         }

      }, 5000);   // Завершение через 5 секунд
      ExecutorService exec = Executors.newCachedThreadPool();
      AtomicIntegerTest ait = new AtomicIntegerTest();
      exec.execute(ait);
      while (true)
      {
         int val = ait.getValue();
         if (val % 2 != 0)
         {
            System.out.println(val);
            System.exit(0);
         }
      }
   }

}

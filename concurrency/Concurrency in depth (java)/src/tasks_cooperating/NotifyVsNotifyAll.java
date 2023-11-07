package tasks_cooperating;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Блокировка на разных объектах.
 *
 * @author Denis
 */
public class NotifyVsNotifyAll
{
   public static void main(String[] args) throws InterruptedException
   {
      ExecutorService exec = Executors.newCachedThreadPool();
      for (int i = 0; i < 5; i++)
      {
         exec.execute(new Task());
      }
      exec.execute(new Task2());
      Timer timer = new Timer();
      timer.scheduleAtFixedRate(new TimerTask()
      {
         @Override
         public void run()
         {
            if (prod)
            {
               System.out.print("\nnotify() ");
               Task.blocker.prod();
               prod = false;
            }
            else
            {
               System.out.print("\nnotifyAll() ");
               Task.blocker.prodAll();
               prod = true;
            }
         }

         boolean prod = true;
      }, 400, 400);  // Запускать каждые 0.4 секунды с задержкой 0.4 секунды
      TimeUnit.SECONDS.sleep(5);
      timer.cancel();
      System.out.println("\nTimer canceled");
      TimeUnit.MILLISECONDS.sleep(500);
      System.out.print("Task2.blocker.prodAll() ");
      Task2.blocker.prodAll();
      TimeUnit.MILLISECONDS.sleep(500);
      System.out.println("\nShutting down");
      exec.shutdownNow();
   }

}

class Blocker
{
   synchronized void waitingCall()
   {
      try
      {
         while (!Thread.interrupted())
         {
            wait();
            System.out.print(Thread.currentThread() + " ");
         }
      }
      catch (InterruptedException e)
      {
         // OK to exit this way
      }
   }

   synchronized void prod()
   {
      notify();
   }

   synchronized void prodAll()
   {
      notifyAll();
   }

}

class Task implements Runnable
{
   @Override
   public void run()
   {
      blocker.waitingCall();
   }

   protected static Blocker blocker = new Blocker();   // Один разделяемый объект
}

class Task2 implements Runnable
{
   @Override
   public void run()
   {
      blocker.waitingCall();
   }

   protected static Blocker blocker = new Blocker();   // Другой разделяемый объект
}

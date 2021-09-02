package sharing_resources;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Объекты Lock из библиотеки concurrent делают возможным попытки установить блокировку в течении некоторого
 * времени.
 *
 * @author Denis
 */
public class AttemptLocking
{
   private ReentrantLock lock = new ReentrantLock();

   public void untimed()
   {
      boolean captured = lock.tryLock();
      try
      {
         System.out.println("tryLock(): " + captured);
      }
      finally
      {
         if (captured)
         {
            lock.unlock();
         }
      }
   }

   public void timed()
   {
      boolean captured = false;
      try
      {
         captured = lock.tryLock(2, TimeUnit.SECONDS);
      }
      catch (InterruptedException ex)
      {
         throw new RuntimeException(ex);
      }
      try
      {
         System.out.println("tryLock(2, TimeUnit.SECONDS): " + captured);
      }
      finally
      {
         if (captured)
         {
            lock.unlock();
         }
      }
   }

   public static void main(String[] args)
   {
      final AttemptLocking al = new AttemptLocking();
      al.untimed();  // True -- блокировка доступна
      al.timed(); // True -- блокировка доступна
      // Теперь создаем отдельную задачу для установления блокировки
      new Thread()
      {

         {
            setDaemon(true);
         }

         @Override
         public void run()
         {
            al.lock.lock();
            System.out.println("acquired");
         }

      }.start();
      Thread.yield();   // Даем возможность второй задаче
      al.untimed();  // False -- блокировка захвачена задачей ?!
      al.timed(); // False -- блокировка захвачена задачей ?!
   }

}

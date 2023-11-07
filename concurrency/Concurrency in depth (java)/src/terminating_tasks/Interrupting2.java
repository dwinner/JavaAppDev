package terminating_tasks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Прерывание задач через реентерабельную блокировку.
 *
 * @author Denis
 */
public class Interrupting2
{
   public static void main(String[] args) throws InterruptedException
   {
      Thread t = new Thread(new Blocked2());
      t.start();
      TimeUnit.SECONDS.sleep(1);
      System.out.println("Issuing t.interrupt()");
      t.interrupt();
   }

}

class BlockedMutex
{
   private Lock lock = new ReentrantLock();

   BlockedMutex()
   {
      /*
       Запрсить блокировку сразу, чтобы продемонстрировать прерывание задачи,
       заблокированной на ReentrantLock:
       */
      lock.lock();
   }

   void f()
   {
      try
      {
         // Этот код уже никогда не станет доступен второй задаче
         lock.lockInterruptibly();  // Специальный вызов
         System.out.println("lock acquired in f()");
      }
      catch (InterruptedException ex)
      {
         System.out.println("Interrupted from lock acquisition in f()");
      }
   }

}

class Blocked2 implements Runnable
{
   BlockedMutex blocked = new BlockedMutex();

   @Override
   public void run()
   {
      System.out.println("Waiting for f() in Blocked mutex");
      blocked.f();
      System.out.println("Broken out of blocked call");
   }

}

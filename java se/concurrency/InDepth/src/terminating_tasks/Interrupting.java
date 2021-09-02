package terminating_tasks;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Прерывание заблокированных потоков.
 *
 * @author Denis
 */
public class Interrupting
{
   private static ExecutorService exec = Executors.newCachedThreadPool();

   static void test(Runnable r) throws InterruptedException
   {
      Future<?> f = exec.submit(r);
      TimeUnit.MILLISECONDS.sleep(100);
      System.out.println("Interrupting: " + r.getClass().getName());
      f.cancel(true);   // Прервать, если запущен
      System.out.println("Interrupt sent to " + r.getClass().getName());
   }

   public static void main(String[] args) throws InterruptedException
   {
      test(new SleepBlocked());
      test(new IOBlocked(System.in));
      test(new SynchronizedBlocked());
      TimeUnit.SECONDS.sleep(3);
      System.out.println("Aborting with System.exit(0)");
      System.exit(0);   // так как последние 2 прерывания не удались
   }

}

/**
 * Прерывание "заснувшего" потока
 *
 * @author Denis
 */
class SleepBlocked implements Runnable
{
   @Override
   public void run()
   {
      try
      {
         TimeUnit.SECONDS.sleep(100);
      }
      catch (InterruptedException ex)
      {
         System.out.println(getClass().getName() + ": interrupted exception");
      }
      System.out.println(getClass().getName() + ": exiting run()");
   }

}

/**
 * Прерывание потока, ждущего ввод
 *
 * @author Denis
 */
class IOBlocked implements Runnable
{
   private InputStream in;

   IOBlocked(InputStream inputStream)
   {
      in = inputStream;
   }

   @Override
   public void run()
   {
      try
      {
         System.out.println(getClass().getName() + ": waiting for read()");
         in.read();
      }
      catch (IOException ioEx)
      {
         if (Thread.currentThread().isInterrupted())
         {
            System.out.println(getClass().getName() + ": interrupted from blocked I/O");
         }
         else
         {
            throw new RuntimeException(ioEx);
         }
      }
      System.out.println(getClass().getName() + ": exiting run()");
   }

}

/**
 * Поток, ждущий получения блокировки объекта.
 *
 * @author Denis
 */
class SynchronizedBlocked implements Runnable
{
   synchronized void f()
   {
      while (true)   // Никогда не освободит блокировку
      {
         Thread.yield();
      }
   }

   SynchronizedBlocked()
   {
      new Thread()
      {
         @Override
         public void run()
         {
            f();  // Запросить блокировку в этом потоке
         }

      }.start();
   }

   @Override
   public void run()
   {
      System.out.println(getClass().getName() + ": trying to call f()");
      f();
      System.out.println(getClass().getName() + ": exiting run()");
   }

}

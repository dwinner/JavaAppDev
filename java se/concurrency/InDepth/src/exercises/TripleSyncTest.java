package exercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Синхронизация по разным объектам.
 *
 * @author Denis
 */
public class TripleSyncTest
{
   public static final int THREAD_COUNT = 10;

   public static void main(String[] args) throws InterruptedException
   {
      // Самопальная защелка
      Runnable latchTask = new Runnable()
      {
         @Override
         public void run()
         {
            try
            {
               TimeUnit.SECONDS.sleep(5);
            }
            catch (InterruptedException ex)
            {
               Logger.getLogger(TripleSyncTest.class.getName()).log(Level.SEVERE, null, ex);
            }
         }

      };
      Thread latchThread = new Thread(latchTask);

      // 1) Синхронизация по одному объекту

      final SingleSync singleSync = new SingleSync();
      ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
      for (int i = 0; i < THREAD_COUNT; i++)
      {
         executorService.execute(new Runnable()
         {
            @Override
            public void run()
            {
               singleSync.printMessage();
               singleSync.anotherPrintMessage();
               singleSync.morePrintMessage();
            }

         });
      }

      latchThread.start();
      latchThread.join();
      System.out.println("Continue...");

      // 2) Синхронизация по трем объектам

      final TripleSync tripleSync = new TripleSync();
      for (int i = 0; i < THREAD_COUNT; i++)
      {
         executorService.execute(new Runnable()
         {
            @Override
            public void run()
            {
               tripleSync.printMessage();
               tripleSync.anotherPrintMessage();
               tripleSync.morePrintMessage();
            }

         });
      }

      Thread secondLatchThread = new Thread(latchTask);
      secondLatchThread.start();
      secondLatchThread.join();
      System.out.println("Continue...");

      executorService.shutdown();
   }

}

/**
 * Синхронизация по одному объекту
 *
 * @author Denis
 */
class SingleSync
{
   void printMessage()
   {
      synchronized (commonSyncObject)
      {
         for (int i = 0; i < COUNT; i++)
         {
            System.out.print("PM " + i + " ");
         }
         System.out.println();
      }
   }

   void anotherPrintMessage()
   {
      synchronized (commonSyncObject)
      {
         for (int i = 0; i < COUNT; i++)
         {
            System.out.print("APM " + i + " ");
         }
         System.out.println();
      }
   }

   void morePrintMessage()
   {
      synchronized (commonSyncObject)
      {
         for (int i = 0; i < COUNT; i++)
         {
            System.out.print("MPM " + i + " ");
         }
         System.out.println();
      }
   }

   private Object commonSyncObject = new Object(); // Общий объект синхронизации.
   private static final int COUNT = 7;
}

/**
 * Синхронизация по трем объектам
 *
 * @author Denis
 */
class TripleSync
{
   void printMessage()
   {
      synchronized (firstSyncObject)
      {
         for (int i = 0; i < COUNT; i++)
         {
            System.out.print("PM " + i + " ");
         }
         System.out.println();
      }
   }

   void anotherPrintMessage()
   {
      synchronized (secondSyncObject)
      {
         for (int i = 0; i < COUNT; i++)
         {
            System.out.print("APM " + i + " ");
         }
         System.out.println();
      }
   }

   void morePrintMessage()
   {
      lock.lock();
      try
      {
         for (int i = 0; i < COUNT; i++)
         {
            System.out.print("MPM " + i + " ");
         }
         System.out.println();
      }
      finally
      {
         lock.unlock();
      }
   }

   private Object firstSyncObject = new Object();
   private Object secondSyncObject = new Object();
   private Lock lock = new ReentrantLock();
   private static final int COUNT = 7;
}

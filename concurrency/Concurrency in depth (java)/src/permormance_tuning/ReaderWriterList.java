/*
 * Блокировки чтения и записи.
 */
package permormance_tuning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReaderWriterList<T>
{
   private ArrayList<T> lockedList;
   private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true); // "Честный" порядок блокировок

   public ReaderWriterList(int size, T initialValue)
   {
      lockedList = new ArrayList<>(Collections.nCopies(size, initialValue));
   }

   public T set(int index, T element)
   {
      Lock writeLock = lock.writeLock();
      writeLock.lock();
      try
      {
         return lockedList.set(index, element);
      }
      finally
      {
         writeLock.unlock();
      }
   }

   public T get(int index)
   {
      Lock readLock = lock.readLock();
      readLock.lock();
      try
      {
         // Покажем, что множество читающих потоков могут войти в ту же блокировку на чтение:
         if (lock.getReadLockCount() > 1)
         {
            System.out.println("Lock readers: " + lock.getReadLockCount());
         }
         return lockedList.get(index);
      }
      finally
      {
         readLock.unlock();
      }
   }

   public static void main(String[] args) throws Exception
   {
      new ReaderWriterListTest(30, 1);
   }

}

class ReaderWriterListTest
{
   ExecutorService exec = Executors.newCachedThreadPool();
   private final static int SIZE = 100;
   private static Random rand = new Random(47);
   private ReaderWriterList<Integer> list = new ReaderWriterList<Integer>(SIZE, 0);

   private class Writer implements Runnable
   {
      @Override
      public void run()
      {
         try
         {
            for (int i = 0; i < 20; i++)
            {
               list.set(i, rand.nextInt());
               TimeUnit.MILLISECONDS.sleep(100);
            }
         }
         catch (InterruptedException e)
         {
         }
         System.out.println("Writer finished, shutting down");
         exec.shutdownNow();
      }

   }

   private class Reader implements Runnable
   {
      @Override
      public void run()
      {
         try
         {
            while (!Thread.interrupted())
            {
               for (int i = 0; i < SIZE; i++)
               {
                  list.get(i);
                  TimeUnit.MILLISECONDS.sleep(1);
               }
            }
         }
         catch (InterruptedException e)
         {
         }
      }

   }

   ReaderWriterListTest(int readers, int writers)
   {
      for (int i = 0; i < readers; i++)
      {
         exec.execute(new Reader());
      }
      for (int i = 0; i < writers; i++)
      {
         exec.execute(new Writer());
      }
   }

}

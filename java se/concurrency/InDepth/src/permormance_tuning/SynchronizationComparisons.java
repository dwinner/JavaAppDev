package permormance_tuning;

/*
 * Объективный анализ производительности атомарных операций,
 * явных и неявных блокировок.
 */

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

abstract class Accumulator
{
   public static long cycles = 50000L;
   // Количество модификаторов и читателей во время теста:
   private static final int N = 4;
   final public static ExecutorService exec = Executors.newFixedThreadPool(N * 2);
   private static CyclicBarrier barrier = new CyclicBarrier(N * 2 + 1);
   protected volatile int index = 0;
   protected volatile long value = 0;
   protected long duration = 0;
   protected String id = "error";
   protected final static int SIZE = 100000;
   protected static int[] preLoaded = new int[SIZE + 10];

   static
   {
      // Загрузка массива случайных значений:
      Random rand = new Random(47);
      for (int i = 0; i < SIZE; i++)
      {
         preLoaded[i] = rand.nextInt();
      }
   }

   public abstract void accumulate();

   public abstract long read();

   private class Modifier implements Runnable
   {
      @Override
      public void run()
      {
         for (long i = 0; i < cycles; i++)
         {
            accumulate();
         }
         try
         {
            barrier.await();
         }
         catch (InterruptedException | BrokenBarrierException e)
         {
            throw new RuntimeException(e);
         }
      }

   }

   private class Reader implements Runnable
   {
      private volatile long value;

      @Override
      public void run()
      {
         for (long i = 0; i < cycles; i++)
         {
            value = read();
         }
         try
         {
            barrier.await();
         }
         catch (InterruptedException | BrokenBarrierException e)
         {
            throw new RuntimeException(e);
         }
      }

   }

   public void timedTest()
   {
      long start = System.nanoTime();
      for (int i = 0; i < N; i++)
      {
         exec.execute(new Modifier());
         exec.execute(new Reader());
      }
      try
      {
         barrier.await();
      }
      catch (InterruptedException | BrokenBarrierException e)
      {
         throw new RuntimeException(e);
      }
      duration = System.nanoTime() - start;
      System.out.printf("%-13s: %13d\n", id, duration);
   }

   public static void report(Accumulator acc1, Accumulator acc2)
   {
      System.out.printf("%-22s: %.2f\n", acc1.id + "/" + acc2.id,
                        (double) acc1.duration / (double) acc2.duration);
   }

}

class BaseLine extends Accumulator
{

   {
      id = "BaseLine";
   }

   @Override
   public void accumulate()
   {
      value += preLoaded[index++];
      if (index >= SIZE)
      {
         index = 0;
      }
   }

   @Override
   public long read()
   {
      return value;
   }

}

class SynchronizedTest extends Accumulator
{

   {
      id = "synchronized";
   }

   @Override
   public synchronized void accumulate()
   {
      value += preLoaded[index++];
      if (index >= SIZE)
      {
         index = 0;
      }
   }

   @Override
   public synchronized long read()
   {
      return value;
   }

}

class LockTest extends Accumulator
{

   {
      id = "Lock";
   }

   private Lock lock = new ReentrantLock();

   @Override
   public void accumulate()
   {
      lock.lock();
      try
      {
         value += preLoaded[index++];
         if (index >= SIZE)
         {
            index = 0;
         }
      }
      finally
      {
         lock.unlock();
      }
   }

   @Override
   public long read()
   {
      lock.lock();
      try
      {
         return value;
      }
      finally
      {
         lock.unlock();
      }
   }

}

class AtomicTest extends Accumulator
{

   {
      id = "Atomic";
   }

   private AtomicInteger atomicIndex = new AtomicInteger(0);
   private AtomicLong atomicValue = new AtomicLong(0);

   @Override
   public void accumulate()
   {
      /* Упс! Опираясь на несколько атомарных сущностей
       нужна синхронизация, однако это не влияет на показатели производительности */
      int i = atomicIndex.getAndIncrement();
      atomicValue.getAndAdd(preLoaded[i]);
      if (++i >= SIZE)
      {
         atomicIndex.set(0);
      }
   }

   @Override
   public long read()
   {
      return atomicValue.get();
   }

}

public class SynchronizationComparisons
{
   final static BaseLine baseLine = new BaseLine();
   final static SynchronizedTest synch = new SynchronizedTest();
   final static LockTest lock = new LockTest();
   final static AtomicTest atomic = new AtomicTest();

   static void test()
   {
      System.out.println("============================");
      System.out.printf("%-12s : %13d\n", "Cycles", Accumulator.cycles);
      baseLine.timedTest();
      synch.timedTest();
      lock.timedTest();
      atomic.timedTest();
      Accumulator.report(synch, baseLine);
      Accumulator.report(lock, baseLine);
      Accumulator.report(atomic, baseLine);
      Accumulator.report(synch, lock);
      Accumulator.report(synch, atomic);
      Accumulator.report(lock, atomic);
   }

   public static void main(String[] args)
   {
      int iterations = 5; // По умолчанию
      if (args.length > 0)
      {
         iterations = Integer.valueOf(args[0]);
      }
      // Сначала заполняется пул потоков:
      System.out.println("Warmup");
      baseLine.timedTest();
      // Now the initial test doesn't include the cost
      // of starting the threads for the first time.
      // Produce multiple data points:
      /* Здесь первоначальный запуск тестов не влияет на время
       запуска потоков. Производим несколько точек данных */
      for (int i = 0; i < iterations; i++)
      {
         test();
         Accumulator.cycles *= 2;
      }
      Accumulator.exec.shutdown();
   }

}
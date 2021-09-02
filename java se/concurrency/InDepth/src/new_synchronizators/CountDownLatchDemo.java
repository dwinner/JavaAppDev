package new_synchronizators;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 05.12.12
 * Time: 17:27
 * Синхронизаторы. Защелка с обратным отсчетом.
 */
public class CountDownLatchDemo
{
   static final int SIZE = 100;

   public static void main(String[] args)
   {
      ExecutorService exec = Executors.newCachedThreadPool();
      // Все подзадачи используют один объект CountDownLatch
      CountDownLatch latch = new CountDownLatch(SIZE);
      for (int i = 0; i < 10; i++)
         exec.execute(new WaitingTask(latch));
      for (int i = 0; i < SIZE; i++)
         exec.execute(new TaskPortion(latch));
      System.out.println("All tasks are running");
      exec.shutdown();  // Выход по завершению всех задач
   }
}

/**
 * Часть основной задачи.
 */
class TaskPortion implements Runnable
{
   private static int counter = 0;
   private final int id = counter++;
   private static Random rand = new Random(47);
   private final CountDownLatch latch;

   TaskPortion(CountDownLatch latch)
   {
      this.latch = latch;
   }

   @Override
   public void run()
   {
      try
      {
         doWork();
         latch.countDown();
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();  // TODO: Приемлемый вариант выхода
      }
   }

   void doWork() throws InterruptedException
   {
      TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
      System.out.println(this + " finished");
   }

   @Override
   public String toString()
   {
      return String.format("%1$-3d ", id);
   }
}

/**
 * Ожидание по объекту CountDownLatch.
 */
class WaitingTask implements Runnable
{
   private static int counter = 0;
   private final int id = counter++;
   private final CountDownLatch latch;

   WaitingTask(CountDownLatch latch)
   {
      this.latch = latch;
   }

   @Override
   public void run()
   {
      try
      {
         latch.await();
         System.out.println("Barrier is gone for " + this);
      }
      catch (InterruptedException e)
      {
         System.out.println(this);
      }
   }

   @Override
   public String toString()
   {
      return String.format("Waiting task %1$-3d", id);
   }
}

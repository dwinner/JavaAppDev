package terminating_tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Проверка завершения задач.
 *
 * @author Denis
 */
public class OrnamentalGarden
{
   public static void main(String[] args) throws InterruptedException
   {
      for (int i = 0; i < 5; i++)
      {
         Entrance entrance = new Entrance(i);
         EXECUTOR.execute(entrance);
      }
      TimeUnit.SECONDS.sleep(3); // Немного подождем и прекратим процесс
      // Entrance.cancel();
      EXECUTOR.shutdown();
      if (!EXECUTOR.awaitTermination(250, TimeUnit.MILLISECONDS))
      {
         System.out.println("Some tasks were not terminated!");
         System.out.println("Total: " + Entrance.getTotalCount());
         System.out.println("Sum of Entrances: " + Entrance.sumEntrances());
      }
   }

   public final static ExecutorService EXECUTOR = Executors.newCachedThreadPool();
}

class Count
{
   private int count = 0;
   private Random rand = new Random(47);

   // Удалите synchronized, чтобы увидеть конфликт
   synchronized int increment()
   {
      int temp = count;
      if (rand.nextBoolean())
      {  // Случайная рекомендация планировщику потоков
         Thread.yield();
      }
      return (count = ++temp);
   }

   synchronized int value()
   {
      return count;
   }

}

class Entrance implements Runnable
{
   private static Count count = new Count();
   private static List<Entrance> entrances = new ArrayList<>();
   private int number = 0;
   private final int id;   // Здесь не нужна синхронизация
   private static volatile boolean cancelled = false;

   // Атомарная операция на поле volatile
   static void cancel()
   {
      cancelled = true;
   }

   Entrance(int id)
   {
      this.id = id;
      // Сохраним эту задачу в списке. Также "скажем"
      // сборщику мусора не трогать убитые задачи
      entrances.add(this);
   }

   @Override
   public void run()
   {
      while (!Thread.currentThread().isInterrupted())
      {
         synchronized (this)
         {
            ++number;
         }
         System.out.println(this + " Total: " + count.increment());
         try
         {
            TimeUnit.MILLISECONDS.sleep(100);
         }
         catch (InterruptedException ex)
         {
            System.out.println("sleep interruption");
         }
         Thread.currentThread().interrupt();
      }
      System.out.println("Stopping " + this);
   }

   synchronized int getValue()
   {
      return number;
   }

   @Override
   public String toString()
   {
      return "Entrance " + id + ": " + getValue();
   }

   static int getTotalCount()
   {
      return count.value();
   }

   static int sumEntrances()
   {
      int sum = 0;
      for (Entrance entrance : entrances)
      {
         sum += entrance.getValue();
      }
      return sum;
   }

}
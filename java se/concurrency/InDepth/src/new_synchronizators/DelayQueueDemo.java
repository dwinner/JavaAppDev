package new_synchronizators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 06.12.12
 * Time: 16:21
 * Блокирующая очередь объектов с задержками выполнения задач.
 */
public class DelayQueueDemo
{
   public static void main(String[] args)
   {
      Random rand = new Random(47);
      ExecutorService exec = Executors.newCachedThreadPool();
      DelayQueue<DelayedTask> queue = new DelayQueue<>();
      // Очередь заполняется задачами со слуйчайной задержкой
      for (int i = 0; i < 20; i++)
         queue.put(new DelayedTask(rand.nextInt(5000)));
      // Назначение точки остановки
      queue.add(new DelayedTask.EndSentinel(5000, exec));
      exec.execute(new DelayedTaskConsumer(queue));
   }
}

class DelayedTask implements Runnable, Delayed
{
   private static int counter = 0;
   private final int id = counter++;
   private final int delta;
   private final long trigger;
   static final List<DelayedTask> sequence = new ArrayList<>();

   DelayedTask(int delayInMilliseconds)
   {
      delta = delayInMilliseconds;
      trigger = System.nanoTime() + NANOSECONDS.convert(delta, MILLISECONDS);
      sequence.add(this);
   }

   @Override
   public long getDelay(TimeUnit unit)
   {
      return unit.convert(trigger - System.nanoTime(), NANOSECONDS);
   }

   @Override
   public int compareTo(Delayed o)
   {
      DelayedTask that = (DelayedTask) o;
      if (trigger > that.trigger)
         return 1;
      if (trigger < that.trigger)
         return -1;
      return 0;
   }

   @Override
   public void run()
   {
      System.out.println(this);
   }

   @Override
   public String toString()
   {
      return String.format("[%-4d] Task %d", delta, id);
   }

   String summary()
   {
      return String.format("(%d:%d)", id, delta);
   }

   static class EndSentinel extends DelayedTask
   {
      private ExecutorService exec;

      EndSentinel(int delay, ExecutorService e)
      {
         super(delay);
         exec = e;
      }

      @Override
      public void run()
      {
         for (DelayedTask delayedTask : sequence)
         {
            System.out.printf("%s, ", delayedTask.summary());
         }
         System.out.println();
         System.out.print(this + " call shutdownNow()");
         exec.shutdownNow();
      }
   }
}

class DelayedTaskConsumer implements Runnable
{
   private DelayQueue<DelayedTask> q;

   DelayedTaskConsumer(DelayQueue<DelayedTask> queue)
   {
      q = queue;
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
            q.take().run();   // Выполнение задачи в текущем потоке
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();  // TODO: Приемлемый вариант выхода
      }
      System.out.println("Finish DelayedTaskConsumer");
   }
}

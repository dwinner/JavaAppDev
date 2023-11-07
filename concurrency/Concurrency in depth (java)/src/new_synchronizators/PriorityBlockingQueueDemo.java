package new_synchronizators;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 06.12.12
 * Time: 19:27
 * Приоритетная блокирующая очередь.
 */
public class PriorityBlockingQueueDemo
{
   public static void main(String[] args)
   {
      Random rand = new Random(47);
      ExecutorService exec = Executors.newCachedThreadPool();
      PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<>();
      exec.execute(new PrioritizedTaskProducer(queue, exec));
      exec.execute(new PrioritizedTaskConsumer(queue));
   }
}

class PrioritizedTask implements Runnable, Comparable<PrioritizedTask>
{
   private Random rand = new Random(47);
   private static int counter = 0;
   private final int id = counter++;
   private final int priority;   // FIXME: Использовать перечисления
   static final List<PrioritizedTask> sequence = new ArrayList<>();

   PrioritizedTask(int aPriority)
   {
      priority = aPriority;
      sequence.add(this);
   }

   @Override
   public int compareTo(PrioritizedTask o)
   {
      return priority < o.priority ? 1
        : (priority > o.priority ? -1
        : 0);
   }

   @Override
   public void run()
   {
      try
      {
         TimeUnit.MILLISECONDS.sleep(rand.nextInt(250));
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();  // TODO: Приемлемый вариант выхода
      }
      System.out.println(this);
   }

   @Override
   public String toString()
   {
      return String.format("%-3d Task %d", priority, id);
   }

   String summary()
   {
      return String.format("(%d:%d) ", id, priority);
   }

   static class EndSentinel extends PrioritizedTask
   {
      private ExecutorService exec;

      EndSentinel(ExecutorService executorService)
      {
         super(-1);  // Минимальный приоритет в этой программе
         exec = executorService;
      }

      @Override
      public void run()
      {
         int count = 0;
         for (PrioritizedTask pt : sequence)
         {
            System.out.print(pt.summary());
            if (++count % 5 == 0)
               System.out.println();
         }
         System.out.println();
         System.out.println(this + " calling shutdownNow()");
         exec.shutdownNow();
      }
   }
}

class PrioritizedTaskProducer implements Runnable
{
   private Random rand = new Random(47);
   private Queue<Runnable> queue;
   private ExecutorService exec;

   PrioritizedTaskProducer(Queue<Runnable> q, ExecutorService executorService)
   {
      queue = q;
      exec = executorService; // Используется для EndSentinel
   }

   @Override
   public void run()
   {
      /* Неограниченная очередь без блокировки.
      Быстрое заполнение случайными приоритетами. */
      for (int i = 0; i < 20; i++)
      {
         queue.add(new PrioritizedTask(rand.nextInt(10)));
         Thread.yield();
      }

      try
      {
         // Добавление высокоприоритетных задач:
         for (int i = 0; i < 10; i++)
         {
            TimeUnit.MILLISECONDS.sleep(250);
            queue.add(new PrioritizedTask(10));
         }
         // Добавление заданий, начиная с наименьших приоритетов:
         for (int i = 0; i < 10; i++)
            queue.add(new PrioritizedTask(i));

         // Предохранитель для остановки всех задач:
         queue.add(new PrioritizedTask.EndSentinel(exec));
      }
      catch (InterruptedException e)
      {
         e.printStackTrace(); // Приемлемый вариант выхода
      }
      System.out.println("Finish of PrioritizedTaskProducer");
   }
}

class PrioritizedTaskConsumer implements Runnable
{
   private PriorityBlockingQueue<Runnable> q;

   PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> priorityBlockingQueue)
   {
      q = priorityBlockingQueue;
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            // Использование текущего потока для запуска задачи
            q.take().run();
         }
      }
      catch (InterruptedException e)
      {
         e.printStackTrace(); // TODO: Приемлемый вариант выхода
      }
      System.out.println("Finish PrioritizedTaskConsumer");
   }
}

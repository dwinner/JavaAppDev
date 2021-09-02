/*
 * Можно передать только константы, объекты с неизменным состоянием,
 * "отсоединенные объекты", или другие активные объекты в качестве
 * аргументов асинхронных методов.
 */
package active_objects;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ActiveObjectDemo
{
   private ExecutorService ex = Executors.newSingleThreadExecutor();
   private Random rand = new Random(47);

   // Вставка случайной задержки имитирует эффект вычислений:
   private void pause(int factor)
   {
      try
      {
         TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(factor));
      }
      catch (InterruptedException e)
      {
         System.out.println("sleep() interrupted");
      }
   }

   public Future<Integer> calculateInt(final int x, final int y)
   {
      return ex.submit(new Callable<Integer>()
      {
         @Override
         public Integer call()
         {
            System.out.println("starting " + x + " + " + y);
            pause(500);
            return x + y;
         }

      });
   }

   public Future<Float> calculateFloat(final float x, final float y)
   {
      return ex.submit(new Callable<Float>()
      {
         @Override
         public Float call()
         {
            System.out.println("starting " + x + " + " + y);
            pause(2000);
            return x + y;
         }

      });
   }

   public void shutdown()
   {
      ex.shutdown();
   }

   public static void main(String[] args)
   {
      ActiveObjectDemo d1 = new ActiveObjectDemo();
      // Предотвращаем ConcurrentModificationException:
      List<Future<?>> results = new CopyOnWriteArrayList<>();
      for (float f = 0.0f; f < 1.0f; f += 0.2f)
      {
         results.add(d1.calculateFloat(f, f));
      }
      for (int i = 0; i < 5; i++)
      {
         results.add(d1.calculateInt(i, i));
      }
      System.out.println("All asynch calls made");
      while (results.size() > 0)
      {
         for (Future<?> f : results)
         {
            if (f.isDone())
            {
               try
               {
                  System.out.println(f.get());
               }
               catch (InterruptedException | ExecutionException e)
               {
                  throw new RuntimeException(e);
               }
               results.remove(f);
            }
         }
      }
      d1.shutdown();
   }

}

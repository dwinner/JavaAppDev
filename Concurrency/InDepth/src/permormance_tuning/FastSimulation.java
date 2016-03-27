package permormance_tuning;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Атомарность и производительность.
 *
 * @author Denis
 */
public class FastSimulation
{
   static final int N_ELEMENTS = 100000;
   static final int N_GENES = 30;
   static final int N_EVOLVERS = 50;
   static final AtomicInteger[][] GRID = new AtomicInteger[N_ELEMENTS][N_GENES];
   static Random rand = new Random(47);

   static class Evolver implements Runnable
   {
      @Override
      public void run()
      {
         while (!Thread.interrupted())
         {
            // Выбираем случайным образом элемент, с которым мы будем работать
            int element = rand.nextInt(N_ELEMENTS);
            for (int i = 0; i < N_GENES; i++)
            {
               int previous = element - 1;
               if (previous < 0)
               {
                  previous = N_ELEMENTS - 1;
               }
               int next = element + 1;
               if (next >= N_ELEMENTS)
               {
                  next = 0;
               }
               int oldvalue = GRID[element][i].get();
               // Произведем какие-то вычисления:
               int newvalue = oldvalue + GRID[previous][i].get() + GRID[next][i].get();
               newvalue /= 3; // Усредним результат
               if (!GRID[element][i].compareAndSet(oldvalue, newvalue))
               {
                  /* Отследим значения, которые не совпали с актуальными.
                   Здесь мы просто уведомляем об этом и игнорируем. */
                  System.out.println("Old value changed from " + oldvalue);
               }
            }
         }
      }

   }

   public static void main(String[] args) throws Exception
   {
      ExecutorService exec = Executors.newCachedThreadPool();
      for (int i = 0; i < N_ELEMENTS; i++)
      {
         for (int j = 0; j < N_GENES; j++)
         {
            GRID[i][j] = new AtomicInteger(rand.nextInt(1000));
         }
      }
      for (int i = 0; i < N_EVOLVERS; i++)
      {
         exec.execute(new Evolver());
      }
      TimeUnit.SECONDS.sleep(5);
      exec.shutdownNow();
   }

}

package new_synchronizators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 05.12.12
 * Time: 17:48
 * Синхронизаторы. Циклический барьер.
 */
public class HorseRaceTest
{
   private static final int DEFAULT_HORSE_NUMBER = 10;
   private static final int DEFAULT_PAUSE = 700;   // in milliseconds

   public static void main(String[] args)
   {
      int nHorses = DEFAULT_HORSE_NUMBER;
      int pause = DEFAULT_PAUSE;
      if (args.length > 0) // Необязательный аргумент
      {
         int n = Integer.valueOf(args[0]);
         nHorses = n > 0 ? n : nHorses;
      }
      if (args.length > 1) // Необязательный аргумент
      {
         int p = Integer.valueOf(args[1]);
         pause = p > -1 ? p : pause;
      }
      new HorseRace(nHorses, pause);
   }
}

class Horse implements Runnable
{
   private static int counter = 0;
   private final int id = counter++;
   private int strides = 0;
   private static Random rand = new Random(47);
   private static CyclicBarrier barrier;

   Horse(CyclicBarrier b)
   {
      barrier = b;
   }

   synchronized int getStrides()
   {
      return strides;
   }

   String tracks()
   {
      StringBuilder s = new StringBuilder(getStrides());
      for (int i = 0; i < getStrides(); i++)
         s.append('*');
      s.append(id);
      return s.toString();
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            synchronized (this)
            {
               strides += rand.nextInt(3);   // 0, 1 или 2
            }
            barrier.await();
         }
      }
      catch (InterruptedException | BrokenBarrierException multiEx)
      {
         if (multiEx instanceof BrokenBarrierException)
            throw new RuntimeException(multiEx);
         else
            multiEx.printStackTrace();
      }
   }

   @Override
   public String toString()
   {
      return String.format("Horse %d ", id);
   }

}

class HorseRace
{
   static final int FINISH_LINE = 75;
   private List<Horse> horses = new ArrayList<>();
   private ExecutorService exec = Executors.newCachedThreadPool();
   private CyclicBarrier barrier;

   HorseRace(int nHorses, final int pause)
   {
      barrier = new CyclicBarrier(nHorses, new Runnable()
      {
         @Override
         public void run()
         {
            StringBuilder s = new StringBuilder(FINISH_LINE);
            for (int i = 0; i < FINISH_LINE; i++)
               s.append('='); // Забор на беговой дорожке
            System.out.println(s);
            for (Horse horse : horses)
               System.out.println(horse.tracks());
            for (Horse horse : horses)
            {
               if (horse.getStrides() >= FINISH_LINE)
               {
                  System.out.println(horse + " won!");
                  exec.shutdownNow();  // barrier.reset();
                  return;
               }
            }
            try
            {
               TimeUnit.MILLISECONDS.sleep(pause);
            }
            catch (InterruptedException e)
            {
               e.printStackTrace();
            }
         }
      });
      for (int i = 0; i < nHorses; i++)
      {
         Horse horse = new Horse(barrier);
         horses.add(horse);
         exec.execute(horse);
      }
   }
}

package updateiteratemap;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Обновление и проход по карте из множества потоков.
 *
 * @author Denis
 */
public class UpdateIterateMap
{
   public static void main(String[] args)
   {
      UpdateIterateMap updateIterateMap = new UpdateIterateMap();
      updateIterateMap.start();
   }

   private void start()
   {
      ConcurrentMap<Integer, String> concurrentMap = new ConcurrentHashMap<>();
      for (int i = 0; i < 1000; i++)
      {
         startUpdateThread(i, concurrentMap);
      }
      try
      {
         Thread.sleep(1000);
      }
      catch (InterruptedException ex)
      {
         Logger.getLogger(UpdateIterateMap.class.getName()).log(Level.SEVERE, null, ex);
      }
      for (Entry<Integer, String> entry : concurrentMap.entrySet())
      {
         System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
      }
      for (Thread thread : updateThreads)
      {
         thread.interrupt();
      }
   }

   private void startUpdateThread(int i, final ConcurrentMap<Integer, String> concurrentMap)
   {
      Thread thread = new Thread(new Runnable()
      {
         @Override
         public void run()
         {
            while (!Thread.interrupted())
            {
               int randomInt = random.nextInt(20);
               concurrentMap.put(randomInt, UUID.randomUUID().toString());
            }
         }

      });
      thread.setName("Update thread " + i);
      updateThreads.add(thread);
      thread.start();
   }

   private Set<Thread> updateThreads = new HashSet<>();
   private Random random = new Random();
}

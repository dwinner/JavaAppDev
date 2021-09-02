package mapkeyinsert;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Вставка ключа в карту потокобезопасным образом.
 *
 * @author Denis
 */
public class MapKeyInsert
{
   public static void main(String[] args)
   {
      MapKeyInsert insertMap = new MapKeyInsert();
      insertMap.start();
   }

   private void start()
   {
      ConcurrentMap<Integer, String> concurrentMap = new ConcurrentHashMap<>();
      for (int i = 0; i < 100; i++)
      {
         startUpdateThread(i, concurrentMap);
      }

      try
      {
         Thread.sleep(1000);
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }

      for (Entry<Integer, String> entry : concurrentMap.entrySet())
      {
         System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
      }
   }

   private void startUpdateThread(final int i, final ConcurrentMap<Integer, String> concurrentMap)
   {
      Thread thread = new Thread(new Runnable()
      {
         @Override
         public void run()
         {
            int randomInt = random.nextInt(20);
            String previousEntry = concurrentMap.putIfAbsent(randomInt,
                                                             "Thread # " + i + " has made it");
            if (previousEntry != null)
            {
               System.out.println(
                 "Thread # " + i + " tried to update it but guess what, we're too late");
               return;
            }
            else
            {
               System.out.println("Thread # " + i + " has made it!");
               return;
            }
         }

      });
      thread.start();
   }

   private Set<Thread> updateThreads = new HashSet<>();
   private Random random = new Random();
}

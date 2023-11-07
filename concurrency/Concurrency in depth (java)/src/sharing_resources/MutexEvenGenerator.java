package sharing_resources;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Предотвращение потоковых конфликтов с использованием явных мьютексов.
 *
 * @author Denis
 */
public class MutexEvenGenerator extends IntGenerator
{
   private int currentEvenValue = 0;
   private Lock lock = new ReentrantLock();

   @Override
   public int next()
   {
      lock.lock();
      try
      {
         ++currentEvenValue;
         Thread.yield();
         ++currentEvenValue;
         return currentEvenValue;
      }
      finally
      {
         lock.unlock();
      }
   }

   public static void main(String[] args)
   {
      EvenChecker.test(new MutexEvenGenerator());
   }

}

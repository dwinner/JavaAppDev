package sharing_resources;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Использование объектов Lock для создания критических секций.
 *
 * @author Denis
 */
public class ExplicitCriticalSection
{
   public static void main(String[] args)
   {
      PairManager // Потокобезопасные объекты-менеджеры для класса Pair
        pman1 = new ExplicitPairManager1(),
        pman2 = new ExplicitPairManager2();
      CriticalSection.testApproaches(pman1, pman2);
   }

}

/**
 * Синхронизация целого метода.
 *
 * @author Denis
 */
class ExplicitPairManager1 extends PairManager
{
   private Lock lock = new ReentrantLock();

   @Override
   synchronized void increment()
   {
      lock.lock();
      try
      {
         getP().incrementX();
         getP().incrementY();
         store(getPair());
      }
      finally
      {
         lock.unlock();
      }
   }

}

/**
 * Использование критической секции.
 *
 * @author Denis
 */
class ExplicitPairManager2 extends PairManager
{
   private Lock lock = new ReentrantLock();

   @Override
   synchronized void increment()
   {
      Pair temp;
      lock.lock();
      try
      {
         getP().incrementX();
         getP().incrementY();
         temp = getPair();
      }
      finally
      {
         lock.unlock();
      }
      store(temp);
   }

}

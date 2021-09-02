package sharing_resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Синхронизация блоков вместо целых методов. Также демонстрирует защиту неприспособленного к многопоточности
 * класса другим классом
 *
 * @author Denis
 */
public class CriticalSection
{
   public static void main(String[] args)
   {
      PairManager // Потокобезопасные объекты-менеджеры для класса Pair
        pman1 = new PairManager1(),
        pman2 = new PairManager2();
      testApproaches(pman1, pman2);
   }

   /**
    * Сравнение двух подходов.
    *
    * @param pman1 Первый PairManager
    * @param pman2 Второй PairManager
    */
   static void testApproaches(PairManager pman1, PairManager pman2)
   {
      ExecutorService exec = Executors.newCachedThreadPool();
      PairManipulator // Манипуляторы
        pm1 = new PairManipulator(pman1),
        pm2 = new PairManipulator(pman2);
      PairChecker // Проверяющие
        pcheck1 = new PairChecker(pman1),
        pcheck2 = new PairChecker(pman2);
      exec.execute(pm1);
      exec.execute(pm2);
      exec.execute(pcheck1);
      exec.execute(pcheck2);
      try
      {
         TimeUnit.SECONDS.sleep(1);
      }
      catch (InterruptedException ex)
      {
         Logger.getLogger(CriticalSection.class.getName()).log(Level.SEVERE, null, ex);
      }
      System.out.println("pm1: " + pm1 + "\npm2: " + pm2);
      System.exit(0);
   }

   private CriticalSection()
   {
   }

}

/**
 * НЕ потоко безопасный класс.
 *
 * @author Denis
 */
class Pair
{
   private int x;
   private int y;
   static final int DEFAULT_X = 0;
   static final int DEFAULT_Y = 0;

   Pair(int x, int y)
   {
      this.x = x;
      this.y = y;
   }

   Pair()
   {
      this(DEFAULT_X, DEFAULT_Y);
   }

   int getX()
   {
      return x;
   }

   int getY()
   {
      return y;
   }

   void incrementX()
   {
      x++;
   }

   void incrementY()
   {
      y++;
   }

   @Override
   public String toString()
   {
      return "Pair{" + "x=" + x + ", y=" + y + '}';
   }

   class PairValueNotEqualException extends RuntimeException
   {
      PairValueNotEqualException()
      {
         super("Pair value not equal: " + Pair.this);
      }

   }

   void checkState()
   {
      if (x != y)
      {
         throw new PairValueNotEqualException();
      }
   }

}

/**
 * Защита класса Pair внутри приспособленного к потокам класса:
 *
 * @author Denis
 */
abstract class PairManager
{
   private List<Pair> storage = Collections.synchronizedList(new ArrayList<Pair>());
   private Pair p = new Pair();
   final AtomicInteger checkCounter = new AtomicInteger(0);

   protected void store(Pair aPair)
   {
      storage.add(aPair);
      /*
       try
       {  // Предполагается, что операция занимает некоторое время
       TimeUnit.MILLISECONDS.sleep(50);
       }
       catch (InterruptedException ex)
       {
       Logger.getLogger(PairManager.class.getName()).log(Level.SEVERE, null, ex);
       }*/
   }

   public synchronized Pair getPair()
   {
      // Создаем копию, чтобы сохранить оригинал в безопасности
      return new Pair(getP().getX(), getP().getY());
   }

   abstract void increment();

   /**
    * @return the p
    */
   synchronized Pair getP()
   {
      return p;
   }

}

/**
 * Синхронизация всего метода.
 *
 * @author Denis
 */
class PairManager1 extends PairManager
{
   @Override
   synchronized void increment()
   {
      getP().incrementX();
      getP().incrementY();
      store(getP());
   }

}

/**
 * Использование критической секции.
 *
 * @author Denis
 */
class PairManager2 extends PairManager
{
   @Override
   void increment()
   {
      Pair tempPair;
      synchronized (this)
      {
         getP().incrementX();
         getP().incrementY();
         tempPair = getPair();
      }
      store(tempPair);
   }

}

/**
 * Задача, манипулирующая данными
 *
 * @author Denis
 */
class PairManipulator implements Runnable
{
   private PairManager pm;

   PairManipulator(PairManager pairManager)
   {
      pm = pairManager;
   }

   @Override
   public String toString()
   {
      return "Pair: " + pm.getPair() + " checkCounter = " + pm.checkCounter.get();
   }

   @Override
   public void run()
   {
      while (true)
      {
         pm.increment();
      }
   }

}

/**
 * Задача, проверяющая корректность состояния
 *
 * @author Denis
 */
class PairChecker implements Runnable
{
   private PairManager pm;

   PairChecker(PairManager pm)
   {
      this.pm = pm;
   }

   @Override
   public void run()
   {
      while (true)
      {
         pm.checkCounter.incrementAndGet();
         pm.getPair().checkState();
      }
   }

}

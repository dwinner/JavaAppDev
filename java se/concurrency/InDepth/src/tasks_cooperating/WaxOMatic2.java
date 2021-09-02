package tasks_cooperating;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Использование явных объектов условий и блокировок.
 *
 * @author Denis
 */
public class WaxOMatic2
{
   public static void main(String[] args) throws InterruptedException
   {
      Car2 car = new Car2();
      ExecutorService exec = Executors.newCachedThreadPool();
      exec.execute(new WaxOff2(car));
      exec.execute(new WaxOn2(car));
      TimeUnit.SECONDS.sleep(5);
      exec.shutdownNow();
   }

}

class Car2
{
   private Lock lock = new ReentrantLock();
   private Condition condition = lock.newCondition();
   private boolean waxOn = false;

   void waxed()
   {
      lock.lock();
      try
      {
         waxOn = true;  // Готов к полировке
         condition.signalAll();
      }
      finally
      {
         lock.unlock();
      }
   }

   void buffed()
   {
      lock.lock();
      try
      {
         waxOn = false; // Готов к нанесению очередного слоя
         condition.signalAll();
      }
      finally
      {
         lock.unlock();
      }
   }

   void waitForWaxing() throws InterruptedException
   {
      lock.lock();
      try
      {
         while (waxOn == false)
         {
            condition.await();
         }
      }
      finally
      {
         lock.unlock();
      }
   }

   void waitForBuffing() throws InterruptedException
   {
      lock.lock();
      try
      {
         while (waxOn == true)
         {
            condition.await();
         }
      }
      finally
      {
         lock.unlock();
      }
   }

}

class WaxOn2 implements Runnable
{
   private Car2 car;

   WaxOn2(Car2 c)
   {
      car = c;
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            System.out.println("Wax On!");
            TimeUnit.MILLISECONDS.sleep(500);
            car.waxed();
            car.waitForBuffing();
         }
      }
      catch (InterruptedException e)
      {
         System.out.println(getClass().getName() + " exiting via interrupt");
      }
      System.out.println("Ending Wax On task");
   }

}

class WaxOff2 implements Runnable
{
   private Car2 car;

   WaxOff2(Car2 c)
   {
      car = c;
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            car.waitForWaxing();
            System.out.println("Wax Off!");
            TimeUnit.MILLISECONDS.sleep(500);
            car.buffed();
         }
      }
      catch (InterruptedException e)
      {
         System.out.println(getClass().getName() + " exiting via interrupt");
      }
      System.out.println("Ending Wax Off task");
   }

}

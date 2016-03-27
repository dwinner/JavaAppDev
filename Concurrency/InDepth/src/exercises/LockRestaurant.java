package exercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Использование явных блокировок и условий.
 *
 * @author Denis
 */
public class LockRestaurant
{
   public static void main(String[] args)
   {
      new LockRestaurant();
   }

   public LockRestaurant()
   {
      exec.execute(chef);
      exec.execute(waitPerson);
   }

   Meal meal;
   final ExecutorService exec = Executors.newCachedThreadPool();
   final Chef chef = new Chef(this);
   final WaitPerson waitPerson = new WaitPerson(this);
}

class Meal
{
   Meal(int orderNum)
   {
      this.orderNum = orderNum;
   }

   @Override
   public String toString()
   {
      return "Meal{" + "orderNum=" + orderNum + '}';
   }

   final int orderNum;
}

class WaitPerson implements Runnable
{
   WaitPerson(LockRestaurant restaurant)
   {
      this.restaurant = restaurant;
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            lock.lock();
            try
            {
               while (restaurant.meal == null)
                  condition.await();
            }
            finally
            {
               lock.unlock();
            }
            System.out.println("Wait person got " + restaurant.meal);
            restaurant.chef.lock.lock();
            try
            {
               restaurant.meal = null;
               restaurant.chef.condition.signalAll();
            }
            finally
            {
               restaurant.chef.lock.unlock();
            }
         }
      }
      catch (InterruptedException e)
      {
         System.out.println(getClass().getName() + " interrupted");
      }
   }

   private LockRestaurant restaurant;
   final Lock lock = new ReentrantLock();
   final Condition condition = lock.newCondition();
}

class Chef implements Runnable
{
   Chef(LockRestaurant lockRestaurant)
   {
      this.lockRestaurant = lockRestaurant;
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            lock.lock();
            try
            {
               while (lockRestaurant.meal != null)
                  condition.await();
            }
            finally
            {
               lock.unlock();
            }
            if (++count == 10)
            {
               System.out.println("Out of food, closing");
               lockRestaurant.exec.shutdownNow();
            }
            System.out.println("Order up!");
            lockRestaurant.waitPerson.lock.lock();
            try
            {
               lockRestaurant.meal = new Meal(count);
               lockRestaurant.waitPerson.condition.signalAll();
            }
            finally
            {
               lockRestaurant.waitPerson.lock.unlock();
            }
            TimeUnit.MILLISECONDS.sleep(100);
         }
      }
      catch (InterruptedException e)
      {
         System.out.println(getClass().getName() + " interrupted");
      }
   }

   private LockRestaurant lockRestaurant;
   final Lock lock = new ReentrantLock();
   final Condition condition = lock.newCondition();
   private int count;
}

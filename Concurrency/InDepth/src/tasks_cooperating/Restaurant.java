package tasks_cooperating;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Подход поставщик-потребитель для кооперирования задач.
 *
 * @author Denis
 */
public class Restaurant
{
   public static void main(String[] args)
   {
      new Restaurant();
   }

   public Restaurant()
   {
      exec.execute(chef);
      exec.execute(waitPerson);
   }

   Meal meal;
   final ExecutorService exec = Executors.newCachedThreadPool();
   final Chef chef = new Chef(this);
   final WaitPerson waitPerson = new WaitPerson(this);
}

/**
 * Сущностный класс.
 *
 * @author Denis
 */
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

/**
 * Человек, ждущий шеф-повара.
 *
 * @author Denis
 */
class WaitPerson implements Runnable
{
   WaitPerson(Restaurant restaurant)
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
            synchronized (this)
            {
               while (restaurant.meal == null)
               {
                  wait();  // Ждем шефа, который готовит...
               }
            }
            System.out.println("Wait person got " + restaurant.meal);
            synchronized (restaurant.chef)
            {
               restaurant.meal = null;
               restaurant.chef.notifyAll();  // Готов для другого
            }
         }
      }
      catch (InterruptedException e)
      {
         System.out.println("WaitPerson interrupted");
      }
   }

   private Restaurant restaurant;
}

/**
 * Шеф-повар, ждущий человека.
 *
 * @author Denis
 */
class Chef implements Runnable
{
   Chef(Restaurant restaurant)
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
            synchronized (this)
            {
               while (restaurant.meal != null)
               {
                  wait();  // Ждем, пока не подадут еду
               }
            }
            if (++count == 10)
            {
               System.out.println("Out of food, closing");
               restaurant.exec.shutdownNow();
            }
            System.out.println("Order up! ");
            synchronized (restaurant.waitPerson)
            {
               restaurant.meal = new Meal(count);
               restaurant.waitPerson.notifyAll();
            }
            TimeUnit.MILLISECONDS.sleep(100);
         }
      }
      catch (InterruptedException e)
      {
         System.out.println("Chef interrupted");
      }
   }

   private Restaurant restaurant;
   private int count = 0;
}

package tasks_cooperating;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Простейшее взаимодействие задач.
 *
 * @author Denis
 */
public class WaxOMatic
{
   public static void main(String[] args) throws InterruptedException
   {
      Car car = new Car();
      ExecutorService exec = Executors.newCachedThreadPool();
      exec.execute(new WaxOff(car));
      exec.execute(new WaxOn(car));
      TimeUnit.SECONDS.sleep(5); // Небольшая задержка
      exec.shutdownNow();
   }

}

class Car
{
   private boolean waxOn = false;

   synchronized void waxed()
   {
      waxOn = true;  // Готово к обработке
      notifyAll();
   }

   synchronized void buffed()
   {
      waxOn = false; // Готово к нанесению очередного слоя
      notifyAll();
   }

   synchronized void waitForWaxing() throws InterruptedException
   {
      while (waxOn)
      {
         wait();
      }
   }

   synchronized void waitForBuffing() throws InterruptedException
   {
      while (!waxOn)
      {
         wait();
      }
   }

}

class WaxOn implements Runnable
{
   private Car car;

   WaxOn(Car car)
   {
      this.car = car;
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            System.out.println(getClass().getName() + " Wax On!");
            TimeUnit.MILLISECONDS.sleep(500);
            car.waxed();
            car.waitForBuffing();
         }
      }
      catch (InterruptedException ex)
      {
         System.out.println("Exiting via interrupt");
      }
      System.out.println("Ending Wax On task");
   }

}

class WaxOff implements Runnable
{
   private Car car;

   WaxOff(Car car)
   {
      this.car = car;
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            car.waitForWaxing();
            System.out.println(getClass().getName() + " Wax Off!");
            TimeUnit.MILLISECONDS.sleep(500);
            car.buffed();
            car.waitForWaxing();
         }
      }
      catch (InterruptedException e)
      {
         System.out.println("Exiting via interrupt");
      }
      System.out.println("Ending Wax Off task");
   }

}
package synchronizers;

import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 03.11.12
 * Time: 14:46
 * Простой пример семафора.
 */
public class SemaphoreTest
{
   public static void main(String... args)
   {
      Semaphore semaphore = new Semaphore(1);
      new IncThread("A", semaphore);
      new DecThread("B", semaphore);
   }
}

/**
 * Общий ресурс.
 */
class Shared
{
   static int count = 0;
}

/**
 * Поток выполнения, увеличивающий значение счетчика на единицу.
 */
class IncThread implements Runnable
{
   IncThread(String aName, Semaphore aSemaphore)
   {
      name = aName;
      semaphore = aSemaphore;
      new Thread(this).start();
   }

   @Override
   public void run()
   {
      System.out.println(name + " is waiting for a permit");

      try
      {
         semaphore.acquire(); // Получаем разрешение
         System.out.println(name + " gets a permit.");

         // Обращаемся к общему ресурсу.
         for (int i = 0; i < 5; i++)
         {
            Shared.count++;
            System.out.println(name + ": " + Shared.count);

            Thread.sleep(200);
            Thread.yield();   // "Просим" переключить контекст
         }
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }

      // Освобождаем разрешение.
      System.out.println(name + " releases the permit.");
      semaphore.release();
   }

   private String name;
   private Semaphore semaphore;
}

/**
 * Поток выполнения, уменьшающий значение счетчика на единицу.
 */
class DecThread implements Runnable
{
   DecThread(String name, Semaphore semaphore)
   {
      this.name = name;
      this.semaphore = semaphore;
      new Thread(this).start();
   }

   @Override
   public void run()
   {
      System.out.println("Starting " + name);

      try
      {
         System.out.println(name + " is waiting for a permit.");
         semaphore.acquire(); // Получаем разрешение.
         System.out.println(name + " gets a permit.");

         // Обращаемся к общему ресурсу.
         for (int i = 0; i < 5; i++)
         {
            Shared.count--;
            System.out.println(name + ": " + Shared.count);
            Thread.sleep(200);
            Thread.yield();
         }
      }
      catch (InterruptedException e)
      {
         Thread.currentThread().interrupt();
      }

      // Освобождаем разрешение.
      System.out.println(name + " releases the permit.");
      semaphore.release();
   }

   private String name;
   private Semaphore semaphore;
}
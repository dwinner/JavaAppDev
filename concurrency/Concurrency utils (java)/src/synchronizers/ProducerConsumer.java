package synchronizers;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 03.11.12
 * Time: 15:57
 * Реализация модели "Поставщик-Потребитель с использованием семафоров".
 */
public class ProducerConsumer
{
   public static void main(String[] args)
   {
      Q q = new Q();
      new Consumer(q);
      new Producer(q);
   }
}

/**
 * Разделяемый ресурс.
 */
class Q
{
   void get()
   {
      try
      {
         // consumerSemaphore.acquire();
         if (consumerSemaphore.tryAcquire(100, TimeUnit.MILLISECONDS))
            System.out.println("Got: " + n);
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }
      finally
      {
         producerSemaphore.release();
      }
   }

   void put(int n)
   {
      try
      {
         // producerSemaphore.acquire();
         if (producerSemaphore.tryAcquire(100, TimeUnit.MILLISECONDS))
         {
            this.n = n;
            System.out.println("Put: " + n);
         }
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }
      finally
      {
         consumerSemaphore.release();
      }
   }

   private int n;
   private Semaphore consumerSemaphore = new Semaphore(0);   // Семафор потребителя пока недоступен
   private Semaphore producerSemaphore = new Semaphore(1);
}

/**
 * Поток-поставщик.
 */
class Producer implements Runnable
{
   Producer(Q q)
   {
      this.q = q;
      new Thread(this, "Producer").start();
   }

   @Override
   public void run()
   {
      for (int i = 0; i < 20; i++)
         q.put(i);
   }

   private Q q;
}

/**
 * Поток-потребитель.
 */
class Consumer implements Runnable
{
   Consumer(Q q)
   {
      this.q = q;
      new Thread(this, "Consumer").start();
   }

   @Override
   public void run()
   {
      for (int i = 0; i < 20; i++)
         q.get();
   }

   private Q q;
}

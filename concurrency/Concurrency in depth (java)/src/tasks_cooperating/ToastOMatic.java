package tasks_cooperating;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 04.12.12
 * Time: 20:41
 * Модель для использования блокирующих очередей.
 */
public class ToastOMatic
{
   public static void main(String[] args) throws InterruptedException
   {
      ToastQueue
        dryQueue = new ToastQueue(),
        bufferedQueue = new ToastQueue(),
        finishedQueue = new ToastQueue();
      ExecutorService exec = Executors.newCachedThreadPool();
      exec.execute(new Toaster(dryQueue));
      exec.execute(new Butterer(dryQueue, bufferedQueue));
      exec.execute(new Jammer(bufferedQueue, finishedQueue));
      exec.execute(new Eater(finishedQueue));
      TimeUnit.SECONDS.sleep(5);
      exec.shutdownNow();
   }
}

class Toast
{
   enum Status
   {
      DRY, BUTTERED, JAMMED
   }

   private Status status = Status.DRY;
   private final int id;

   Toast(int idn)
   {
      id = idn;
   }

   void butter()
   {
      status = Status.BUTTERED;
   }

   void jam()
   {
      status = Status.JAMMED;
   }

   Status getStatus()
   {
      return status;
   }

   int getId()
   {
      return id;
   }

   @Override
   public String toString()
   {
      return "Toast " + id + ": " + status;
   }
}

class ToastQueue extends LinkedBlockingQueue<Toast>
{
}

/**
 * Задача заполнения очереди тоастами
 */
class Toaster implements Runnable
{
   private ToastQueue toastQueue;
   private int count = 0;
   private Random rand = new Random(47);

   Toaster(ToastQueue tQ)
   {
      toastQueue = tQ;
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
            // Создание тоаста
            Toast t = new Toast(count++);
            System.out.println(t);
            // Вставляем в очередь
            toastQueue.put(t);
         }
      }
      catch (InterruptedException e)
      {
         System.out.println("Toaster interrupted");
      }
      System.out.println("Toaster off");
   }
}

/**
 * Задача нанесения масла на очередь тоастов.
 */
class Butterer implements Runnable
{
   private ToastQueue dryQueue;
   private ToastQueue butteredQueue;

   Butterer(ToastQueue dry, ToastQueue buttered)
   {
      dryQueue = dry;
      butteredQueue = buttered;
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            // Блокируем, пока следующий тоаст из очереди не станет доступен.
            Toast t = dryQueue.take();
            t.butter();
            System.out.println(t);
            butteredQueue.put(t);
         }
      }
      catch (InterruptedException e)
      {
         System.out.println("Butterer interrupted");
      }
      System.out.println("Butterer off");
   }
}

/**
 * Задача нанесения джэма на тоасты с маслом
 */
class Jammer implements Runnable
{
   private ToastQueue butteredQueue;
   private ToastQueue finishedQueue;

   Jammer(ToastQueue buttered, ToastQueue finished)
   {
      butteredQueue = buttered;
      finishedQueue = finished;
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            // Блокируем, пока следующий тоаст с маслом не станет доступен
            Toast t = butteredQueue.take();
            t.jam();
            System.out.println(t);
            finishedQueue.put(t);
         }
      }
      catch (InterruptedException e)
      {
         System.out.println("Jammer interrupted");
      }
      System.out.println("Jammer off");
   }
}

/**
 * Задача, потребляющая тоасты.
 */
class Eater implements Runnable
{
   private ToastQueue finishedQueue;
   private int counter = 0;

   Eater(ToastQueue finished)
   {
      finishedQueue = finished;
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            // Блокируем, пока следующий готовый тоаст не станет доступен
            Toast t = finishedQueue.take();
            /* Проверяем, что тоасты поступают в определенном порядке,
             и что все из них с джэмом */
            if (t.getId() != counter++ || t.getStatus() != Toast.Status.JAMMED)
            {
               System.out.println(">>>> Error: " + t);
               System.exit(1);
            }
            else
               System.out.println("Chomp! " + t);
         }
      }
      catch (InterruptedException e)
      {
         System.out.println("Eater interrupted");
      }
      System.out.println("Eater off");
   }
}

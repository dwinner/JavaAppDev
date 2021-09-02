package basic_threading;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 23.11.12 Time: 11:10 Создание потоков с использованием
 * внутренних классов.
 */
public class ThreadVariations
{
   public static void main(String[] args)
   {
      new InnerThread1("InnerThread1");
      new InnerThread2("InnerThread2");
      new InnerRunnable1("InnerRunnable1");
      new InnerRunnable2("InnerRunnable2");

      int result = -1;
      try
      {
         result = new ThreadMethod("ThreadMethod").runTask(10).get(100, TimeUnit.MILLISECONDS);
      }
      catch (InterruptedException | ExecutionException | TimeoutException e)
      {
         e.printStackTrace();
      }
      finally
      {
         if (result != -1)
         {
            System.out.println(result);
         }
         System.gc();
      }
   }

}

/**
 * Используем именованный внутренний класс.
 */
class InnerThread1
{
   InnerThread1(String name)
   {
      inner = new Inner(name);
   }

   private int countDown = 5;
   private Inner inner;

   private class Inner extends Thread
   {
      private Inner(String name)
      {
         super(name);
         start();
      }

      @Override
      public void run()
      {
         try
         {
            while (true)
            {
               System.out.println(this);
               if (--countDown == 0)
               {
                  return;
               }
               sleep(10);
            }
         }
         catch (InterruptedException e)
         {
            e.printStackTrace();
         }
      }

      @Override
      public String toString()
      {
         return getName() + ": " + countDown;
      }

   }

}

/**
 * Используем безымянный внутренний класс.
 */
class InnerThread2
{
   InnerThread2(String name)
   {
      t = new Thread(name)
      {
         @Override
         public void run()
         {
            try
            {
               while (true)
               {
                  System.out.println(this);
                  if (--countDown == 0)
                  {
                     return;
                  }
                  sleep(10);
               }
            }
            catch (InterruptedException e)
            {
               e.printStackTrace();
            }
         }

         @Override
         public String toString()
         {
            return getName() + ", " + countDown;
         }

      };
      t.start();
   }

   private int countDown = 5;
   private Thread t;
}

/**
 * Используем именованную реализацию Runnable.
 */
class InnerRunnable1
{
   InnerRunnable1(String name)
   {
      inner = new Inner(name);
   }

   private int countDown = 5;
   private Inner inner;

   private class Inner implements Runnable
   {
      private Inner(String name)
      {
         t = new Thread(this, name);
         t.start();
      }

      @Override
      public void run()
      {
         try
         {
            while (true)
            {
               System.out.println(this);
               if (--countDown == 0)
               {
                  return;
               }
               TimeUnit.MILLISECONDS.sleep(10);
            }
         }
         catch (InterruptedException e)
         {
            e.printStackTrace();
         }
      }

      private Thread t;
   }

}

/**
 * Используем анонимную реализацию Runnable
 */
class InnerRunnable2
{
   InnerRunnable2(String name)
   {
      t = new Thread(new Runnable()
      {
         @Override
         public void run()
         {
            try
            {
               while (true)
               {
                  System.out.println(this);
                  if (--countDown == 0)
                  {
                     return;
                  }
                  TimeUnit.MILLISECONDS.sleep(10);
               }
            }
            catch (InterruptedException e)
            {
               e.printStackTrace();
            }
         }

         @Override
         public String toString()
         {
            return Thread.currentThread().getName() + ": " + countDown;
         }

      }, name);
      t.start();
   }

   private int countDown = 5;
   private Thread t;
}

class ThreadMethod
{
   ThreadMethod(String name)
   {
      this.name = name;
   }

   Future<Integer> runTask(final int fibLimit)
   {
      Callable<Integer> newCallable = new Callable<Integer>()
      {
         @Override
         public Integer call() throws Exception
         {
            return retrieveFibonacciSequence(fibLimit);
         }

         private int retrieveFibonacciSequence(int fibLimit)
         {
            int first = 1;
            int second = 2;
            int sum = first + second;
            while (second < fibLimit)
            {
               int temp = first;
               first = second;
               second += temp;
               sum += second;
            }
            return sum;
         }

      };
      return executorService.submit(newCallable);
   }

   @Override
   protected void finalize() throws Throwable
   {
      if (!executorService.isShutdown())
      {
         executorService.shutdownNow();
      }
   }

   private ExecutorService executorService = Executors.newSingleThreadExecutor();
   private String name;
}
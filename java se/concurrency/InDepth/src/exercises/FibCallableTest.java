package exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 22.11.12
 * Time: 18:02
 * Упражнение 5: Возврат суммы чисел Фибоначчи из задач.
 */
public class FibCallableTest
{
   public static void main(String[] args)
   {
      ExecutorService executorService = Executors.newCachedThreadPool();
      List<Future<Integer>> futureList = new ArrayList<>();
      for (int i = 0; i < 10; i++)
         futureList.add(executorService.submit(new FibCallable(i + OFFSET)));

      try
      {
         for (Future<Integer> integerFuture : futureList)
         {
            try
            {
               int result = (!integerFuture.isDone())
                 ? integerFuture.get(100, TimeUnit.NANOSECONDS)
                 : integerFuture.get();
               System.out.println(result);
            }
            catch (TimeoutException e)
            {
               System.out.println("One future task is out of time");
            }
         }
      }
      catch (InterruptedException | ExecutionException e)
      {
         e.printStackTrace();
      }
      finally
      {
         executorService.shutdown();
      }
   }

   public static final int OFFSET = 5;
}

class FibCallable implements Callable<Integer>
{
   FibCallable(int limit)
   {
      this.limit = limit;
   }

   @Override
   public Integer call()
     throws Exception
   {
      int fibonacciSum = 0;
      for (int i = 1; i < limit; i++)
         fibonacciSum += fib(i);
      return fibonacciSum;
   }

   private int fib(int n)
   {
      return (n < 2) ? 1 : (fib(n - 2) + fib(n - 1));
   }

   private int limit;
}

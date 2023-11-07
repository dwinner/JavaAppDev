package exercises;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 22.11.12
 * Time: 1:18
 * Упражнение 2. Генерирование последовательности Фибоначчи в отдельной задаче.
 */
public class FibonacciRunnable
{
   public static void main(String[] args)
   {
      for (int i = 2; i < 8; i++)
      {
         new Thread(new FibonacciTask(i)).start();
      }
   }
}

class FibonacciTask implements Runnable
{
   FibonacciTask(int fibLimit)
   {
      this.fibLimit = fibLimit;
   }

   @Override
   public void run()
   {
      List<Integer> fibSequence = new ArrayList<>(fibLimit);
      for (int i = 1; i < fibLimit; i++)
      {
         fibSequence.add(fib(i));
         Thread.yield();
      }

      for (Integer fibItem : fibSequence)
         System.out.print(fibItem + " ");

      System.out.println(Thread.currentThread().getName() + " is shut down");
   }

   private int fib(int n)
   {
      return (n < 2) ? 1 : (fib(n - 2) + fib(n - 1));
   }

   private int fibLimit;
}

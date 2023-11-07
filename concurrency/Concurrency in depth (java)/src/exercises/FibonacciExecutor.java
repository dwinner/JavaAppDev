package exercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 22.11.12
 * Time: 17:47
 * Упражнение 4.
 */
public class FibonacciExecutor
{
   public static void main(String[] args)
   {
      ExecutorService singlePool = Executors.newSingleThreadExecutor();
      for (int i = 5; i < 10; i++)
         singlePool.execute(new FibonacciTask(i));
      singlePool.shutdown();
   }
}

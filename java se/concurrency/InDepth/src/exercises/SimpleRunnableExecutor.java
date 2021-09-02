package exercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 22.11.12
 * Time: 17:43
 * Упражнение 3: Различные типы исполнителей.
 */
public class SimpleRunnableExecutor
{
   public static void main(String[] args)
   {
      ExecutorService cachedPool = Executors.newCachedThreadPool();
      for (int i = 0; i < 10; i++)
         cachedPool.execute(new RunnableImpl("Running message"));
      cachedPool.shutdown();
   }
}

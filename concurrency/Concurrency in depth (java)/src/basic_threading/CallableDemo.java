package basic_threading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 22.11.12 Time: 17:51 Возврат значений из задач.
 */
public class CallableDemo
{
   public static void main(String[] args)
   {
      ExecutorService cachedPool = Executors.newCachedThreadPool();
      List<Future<String>> results = new ArrayList<>();
      for (int i = 0; i < 10; i++)
      {
         results.add(cachedPool.submit(new TaskWithResult(i)));
      }
      for (Future<String> futureString : results)
      {
         try
         {
            // Вызов get() блокируется до завершения:
            System.out.println(futureString.get());
         }
         catch (InterruptedException | ExecutionException e)
         {
            e.printStackTrace();
         }
         finally
         {
            cachedPool.shutdown();
         }
      }
   }

}

class TaskWithResult implements Callable<String>
{
   private int id;

   TaskWithResult(int id)
   {
      this.id = id;
   }

   @Override
   public String call()
     throws Exception
   {
      return "Result TaskWithResult " + id;
   }

}

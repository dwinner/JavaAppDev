package basic_threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 23.11.12 Time: 15:37 Генерирование исключений в задаче.
 */
public class ExceptionThread implements Runnable
{
   @Override
   public void run()
   {
      throw new RuntimeException();
   }

   public static void main(String[] args)
   {
      ExecutorService exec = Executors.newCachedThreadPool();
      exec.execute(new ExceptionThread());
   }

}

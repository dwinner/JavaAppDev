package basic_threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 23.11.12 Time: 15:42 Неперехваченное исключение.
 */
public class NaiveExceptionHandling
{
   public static void main(String[] args)
   {
      try
      {
         ExecutorService exec = Executors.newCachedThreadPool();
         exec.execute(new ExceptionThread());
      }
      catch (RuntimeException ue)
      {
         // Этого не произойдет!
      }
   }

}

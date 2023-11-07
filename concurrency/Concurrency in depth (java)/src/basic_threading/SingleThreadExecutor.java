package basic_threading;

import basic_threading.LiftOff;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 22.11.12 Time: 17:41 Пул одного потока.
 */
public class SingleThreadExecutor
{
   public static void main(String[] args)
   {
      ExecutorService singleExecutor = Executors.newSingleThreadExecutor();
      for (int i = 0; i < 5; i++)
      {
         singleExecutor.execute(new LiftOff());
      }
      singleExecutor.shutdown();
   }

}

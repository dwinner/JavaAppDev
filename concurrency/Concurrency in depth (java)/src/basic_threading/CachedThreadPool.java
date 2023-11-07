package basic_threading;

import basic_threading.LiftOff;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA. Кэшированный пул потоков.
 *
 * @author Denis Date: 22.11.12 Time: 17:35
 */
public class CachedThreadPool
{
   public static void main(String[] args)
   {
      ExecutorService exec = Executors.newCachedThreadPool();
      for (int i = 0; i < 5; i++)
      {
         exec.execute(new LiftOff());
      }
      exec.shutdown();
   }

}

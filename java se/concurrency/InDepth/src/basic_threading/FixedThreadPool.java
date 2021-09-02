package basic_threading;

import basic_threading.LiftOff;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 22.11.12 Time: 17:38 Фиксированный пул потоков.
 */
public class FixedThreadPool
{
   public static void main(String[] args)
   {
      // В аргументе конструктора передается количество потоков
      ExecutorService service = Executors.newFixedThreadPool(5);
      for (int i = 0; i < 5; i++)
      {
         service.execute(new LiftOff());
      }
      service.shutdown();
   }

}

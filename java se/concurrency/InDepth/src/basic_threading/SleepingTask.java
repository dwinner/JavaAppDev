package basic_threading;

import basic_threading.LiftOff;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 22.11.12 Time: 18:28 Состояние ожидания.
 */
public class SleepingTask extends LiftOff
{
   public static void main(String[] args)
   {
      ExecutorService exec = Executors.newCachedThreadPool();
      for (int i = 0; i < 5; i++)
      {
         exec.execute(new SleepingTask());
      }
      exec.shutdown();
   }

   @Override
   public void run()
   {
      while (countDown-- > 0)
      {
         System.out.println(status());
         try
         {
            TimeUnit.MILLISECONDS.sleep(100);
         }
         catch (InterruptedException e)
         {
            e.printStackTrace();
         }
      }
   }

}

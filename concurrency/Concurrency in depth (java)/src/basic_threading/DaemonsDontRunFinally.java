package basic_threading;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 22.11.12 Time: 23:15 Потоки-демоны не выполняют секцию
 * finally.
 */
public class DaemonsDontRunFinally
{
   public static void main(String[] args)
   {
      Thread t = new Thread(new Runnable()
      {
         @Override
         public void run()
         {
            System.out.println("Start a daemon");
            try
            {
               TimeUnit.SECONDS.sleep(1);
            }
            catch (InterruptedException e)
            {
               e.printStackTrace();
            }
            finally
            {
               System.out.println("Really must happen?!");
            }
         }

      });
      t.setDaemon(true);
      t.start();
   }

}

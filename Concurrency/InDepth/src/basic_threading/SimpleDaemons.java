package basic_threading;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 22.11.12 Time: 22:33 Потоки-демоны не препятствуют завершению
 * работы программы.
 */
public class SimpleDaemons implements Runnable
{
   @Override
   public void run()
   {
      try
      {
         while (true)
         {
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println(Thread.currentThread() + " " + this);
         }
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }
   }

   public static void main(String[] args) throws InterruptedException
   {
      for (int i = 0; i < 10; i++)
      {
         Thread daemon = new Thread(new SimpleDaemons());
         daemon.setDaemon(true); // Необходимо вызвать перед start()
         daemon.start();
      }
      System.out.println("All daemons are started");
      TimeUnit.MILLISECONDS.sleep(175);
   }

}

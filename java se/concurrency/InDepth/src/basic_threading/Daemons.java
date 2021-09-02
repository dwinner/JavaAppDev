package basic_threading;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 22.11.12 Time: 23:07 Потоки, порождаемые демонами, также
 * являются демонами.
 */
public class Daemons
{
   public static void main(String[] args) throws InterruptedException
   {
      Thread d = new Thread(new Daemon());
      d.setDaemon(true);
      d.start();
      System.out.println("d.isDaemon() = " + d.isDaemon() + ", ");
      // Даем потокам-демонам завершить процесс запуска
      TimeUnit.MILLISECONDS.sleep(1);
   }

}

class Daemon implements Runnable
{
   private Thread[] t = new Thread[10];

   @Override
   public void run()
   {
      for (int i = 0; i < t.length; i++)
      {
         t[i] = new Thread(new Runnable()
         {
            @Override
            public void run()
            {
               while (true)
               {
                  Thread.yield();
               }
            }

         });
         t[i].start();
         System.out.println("Daemon spawn " + i + " started. ");
      }
      for (int i = 0; i < t.length; i++)
      {
         System.out.println("t[" + i + "].isDaemon() = " + t[i].isDaemon() + ", ");
      }
      while (true)
      {
         Thread.yield();
      }
   }

}

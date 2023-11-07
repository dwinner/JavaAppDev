package basic_threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 22.11.12 Time: 22:42 Использование ThreadFactory для создания
 * демонов.
 */
public class DaemonFromFactory
{
   /**
    * Генератор фабрики потоков-демонов
    *
    * @return Фабрика потоков-демонов
    */
   public static ThreadFactory createDaemonThreadFactory()
   {
      return new ThreadFactory()
      {
         @Override
         public Thread newThread(Runnable r)
         {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
         }

      };
   }

   public static void main(String[] args)
     throws InterruptedException
   {
      ExecutorService service =
        Executors.newCachedThreadPool(createDaemonThreadFactory());
      for (int i = 0; i < 10; i++)
      {
         service.execute(new Runnable()
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

         });
      }
      System.out.println("All daemons are started");
      TimeUnit.MILLISECONDS.sleep(500);   // Задержка
   }

}

package basic_threading;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 22.11.12 Time: 22:55 Пул потоков-демонов.
 */
public class DaemonThreadPoolExecutor extends ThreadPoolExecutor
{
   public DaemonThreadPoolExecutor()
   {
      super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
            new ThreadFactory()
      {
         @Override
         public Thread newThread(Runnable r)
         {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
         }

      });
   }

}

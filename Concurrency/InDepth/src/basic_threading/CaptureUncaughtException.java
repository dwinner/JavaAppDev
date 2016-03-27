package basic_threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 23.11.12 Time: 15:47 Перехват неотловленных исключений.
 */
public class CaptureUncaughtException
{
   public static void main(String[] args)
   {
      ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
      exec.execute(new ExceptionThread2());
      exec.shutdown();
   }

}

class ExceptionThread2 implements Runnable
{
   @Override
   public void run()
   {
      Thread currentThread = Thread.currentThread();
      System.out.println("run() by " + currentThread);
      System.out.println("eh = " + currentThread.getUncaughtExceptionHandler());
      throw new RuntimeException();
   }

}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler
{
   @Override
   public void uncaughtException(Thread t, Throwable e)
   {
      System.out.println("caught " + e);
   }

}

class HandlerThreadFactory implements ThreadFactory
{
   @Override
   public Thread newThread(Runnable r)
   {
      System.out.println(this + " creating new thread");
      Thread t = new Thread(r);
      System.out.println("created " + t);
      t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
      System.out.println("eh = " + t.getUncaughtExceptionHandler());
      return t;
   }

}

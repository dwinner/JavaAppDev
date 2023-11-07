package basic_threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 23.11.12 Time: 16:01 Обработчик неотловленных исключений по
 * умолчанию.
 */
public class SettingDefaultHandler
{
   public static void main(String[] args)
   {
      Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
      ExecutorService exec = Executors.newCachedThreadPool();
      exec.execute(new ExceptionThread());
      exec.shutdown();
   }

}

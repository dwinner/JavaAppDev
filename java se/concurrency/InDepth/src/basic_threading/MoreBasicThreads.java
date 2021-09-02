package basic_threading;

import basic_threading.LiftOff;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 22.11.12 Time: 0:50 Добавление новых потоков.
 */
public class MoreBasicThreads
{
   public static void main(String[] args)
   {
      for (int i = 0; i < 5; i++)
      {
         Thread thread = new Thread(new LiftOff());
         thread.setDaemon(true);
         thread.start();
         System.out.println();
      }
      System.out.println("Waiting for LiftOff");
   }

}

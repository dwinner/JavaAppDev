package basic_threading;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 23.11.12 Time: 15:18 Отзывчивость интерфейса.
 */
public class ResponsiveUI extends Thread
{
   private static volatile double d = 1;

   public ResponsiveUI()
   {
      setDaemon(true);
      start();
   }

   @Override
   public void run()
   {
      while (true)
      {
         d = d + (Math.PI + Math.E) / d;
      }
   }

   public static void main(String[] args) throws IOException
   {
      // new UnresponsiveUI();   // Придется убивать процесс вручную
      new ResponsiveUI();
      System.in.read();
      System.out.println(d);  // Показать прогресс
   }

}

class UnresponsiveUI
{
   private volatile double d = 1;

   UnresponsiveUI() throws IOException
   {
      while (d > 0)
      {
         d = d + (Math.PI + Math.E) / d;
      }
      System.in.read(); // Никогда не произойдет
   }

}

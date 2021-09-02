package basic_threading;

import basic_threading.LiftOff;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 22.11.12 Time: 0:48 Простейший вариант использования класса
 * Thread.
 */
public class BasicThreads
{
   public static void main(String[] args)
   {
      Thread t = new Thread(new LiftOff());
      t.start();
      System.out.println("Waiting for LiftOff");
   }

}

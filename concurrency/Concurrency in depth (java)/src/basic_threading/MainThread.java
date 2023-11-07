package basic_threading;

import basic_threading.LiftOff;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 22.11.12 Time: 0:30 Вызов в главном потоке. Синхронное
 * выполнение
 */
public class MainThread
{
   public static void main(String[] args)
   {
      LiftOff launch = new LiftOff();
      launch.run();
   }

}

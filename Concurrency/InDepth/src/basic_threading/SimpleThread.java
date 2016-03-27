package basic_threading;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 23.11.12 Time: 10:54 Прямое наследование от класса Thread.
 */
public class SimpleThread extends Thread
{
   private int countDown = 5;
   private static int threadCount = 0;

   public SimpleThread()
   {
      super(Integer.toString(++threadCount));   // Сохранение имени потока
      start();
   }

   @Override
   public String toString()
   {
      return "#" + getName() + "(" + countDown + "), ";
   }

   @Override
   public void run()
   {
      while (true)
      {
         System.out.print(this);
         if (--countDown == 0)
         {
            return;
         }
      }
   }

   public static void main(String[] args)
   {
      for (int i = 0; i < 5; i++)
      {
         new SimpleThread();
      }
   }

}

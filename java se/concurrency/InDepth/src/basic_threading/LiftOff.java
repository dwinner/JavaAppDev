package basic_threading;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 22.11.12 Time: 0:19 Реализация интерфейса Runnable.
 */
public class LiftOff implements Runnable
{
   protected int countDown = 10; // Значение по умолчанию
   private static int taskCount = 0;
   private final int id = taskCount++;

   public LiftOff()
   {
   }

   public LiftOff(int countDown)
   {
      this.countDown = countDown;
   }

   public String status()
   {
      return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "), ";
   }

   /*@Override
    public void run()
    {
    while (countDown-- > 0)
    {
    System.out.print(status());
    Thread.yield();
    }
    }*/
   @Override
   public void run()
   {
      while (!Thread.currentThread().isInterrupted())
      {
         countDown--;
         System.out.println(status());
         Thread.yield();
         if (countDown == 0)
         {
            Thread.currentThread().interrupt();
         }
      }
   }

}

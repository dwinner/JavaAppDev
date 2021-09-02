package basic_threading;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 23.11.12 Time: 14:37 Присоединение к потоку.
 */
public class Joining
{
   public static void main(String[] args)
   {
      Sleeper sleepy = new Sleeper("Sleepy", 1500),
        grumpy = new Sleeper("Grumpy", 1500);
      Joiner dopey = new Joiner("Dopey", sleepy),
        doc = new Joiner("Doc", grumpy);
      grumpy.interrupt();
   }

}

class Sleeper extends Thread
{
   private int duration;

   public Sleeper(String name, int sleepTime)
   {
      super(name);
      duration = sleepTime;
      start();
   }

   @Override
   public void run()
   {
      try
      {
         sleep(duration);
      }
      catch (InterruptedException e)
      {
         System.out.println(getName() + " is interrupted " + ". isInterrupted() = " + isInterrupted());
         return;
      }
      System.out.println(getName() + " has been activated");
   }

}

class Joiner extends Thread
{
   private Sleeper sleeper;

   public Joiner(String name, Sleeper sleeper)
   {
      super(name);
      this.sleeper = sleeper;
      start();
   }

   @Override
   public void run()
   {
      try
      {
         sleeper.join();
      }
      catch (InterruptedException e)
      {
         System.out.println(getName() + " interrupted");
      }
      System.out.println(getName() + " join is finished");
   }

}

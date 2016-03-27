package deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 05.12.12
 * Time: 8:35
 * Обедающий философ.
 */
public class Philosopher implements Runnable
{
   private Chopstick left;
   private Chopstick right;
   private final int id;
   private final int ponderFactor;
   private Random rand = new Random(47);

   private void pause() throws InterruptedException
   {
      if (ponderFactor == 0)
         return;
      TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
   }

   public Philosopher(Chopstick leftChopstick, Chopstick rightChopstick, int anId, int ponder)
   {
      left = leftChopstick;
      right = rightChopstick;
      id = anId;
      ponderFactor = ponder;
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            System.out.println(this + " thinking; ");
            pause();
            // Философ проголодался
            System.out.println(this + " take right chopstick; ");
            right.take();
            System.out.println(this + " take left chopstick; ");
            left.take();
            System.out.println(this + " eat; ");
            pause();
            right.drop();
            left.drop();
         }
      }
      catch (InterruptedException e)
      {
         System.out.println(this + " exit via interrupting");
      }
   }

   @Override
   public String toString()
   {
      return "Philosopher-" + id;
   }
}

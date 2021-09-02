package deadlock;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 05.12.12
 * Time: 8:32
 * Палочки для обедающих философов.
 */
public class Chopstick
{
   private boolean taken = false;

   public synchronized void take() throws InterruptedException
   {
      while (taken)
         wait();
      taken = true;
   }

   public synchronized void drop()
   {
      taken = false;
      notifyAll();
   }
}

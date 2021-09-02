import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 17.11.12
 * Time: 23:16
 * Нетрадиционная блокировка разделяемых ресурсов.
 */
public class LockTest
{
   public static void main(String[] args)
   {
      Lock lock = new ReentrantLock();
      new LockThread(lock, "A");
      new LockThread(lock, "B");
   }
}

class Shared
{
   static int count = 0;
}

class LockThread implements Runnable
{
   LockThread(Lock aLock, String aName)
   {
      lock = aLock;
      name = aName;
      new Thread(this).start();
   }

   @Override
   public void run()
   {
      System.out.println("Starting " + name);

      try
      {
         System.out.println(name + " is waiting to lock count.");
         lock.lock();
         System.out.println(name + " is locking count.");
         Shared.count++;
         System.out.println(name + ": " + Shared.count);
         System.out.println(name + " is sleeping");
         try
         {
            Thread.sleep(1000);
         }
         catch (InterruptedException e)
         {
            e.printStackTrace();
         }
      }
      finally
      {
         System.out.println(name + " is unlocking count.");
         lock.unlock();
      }
   }

   private String name;
   private Lock lock;
}

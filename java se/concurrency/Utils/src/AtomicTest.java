import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 17.11.12
 * Time: 23:42
 * Атомарные операции.
 */
public class AtomicTest
{
   public static void main(String[] args)
   {
      new AtomicThread("A");
      new AtomicThread("B");
      new AtomicThread("C");
   }
}

class AtomicityShared
{
   static AtomicInteger atomicInteger = new AtomicInteger(0);
}

class AtomicThread implements Runnable
{
   AtomicThread(String name)
   {
      this.name = name;
      new Thread(this).start();
   }

   @Override
   public void run()
   {
      System.out.println("Starting " + name);
      for (int i = 1; i <= 3; i++)
         System.out.println(name + " got: " + AtomicityShared.atomicInteger.getAndSet(i));
   }

   private String name;
}

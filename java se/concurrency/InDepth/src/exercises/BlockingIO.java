package exercises;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 05.12.12
 * Time: 8:12
 * Использование блокирующих очередей для блокирующих операций ввода/вывода.
 */
public class BlockingIO
{
   public static void main(String[] args) throws IOException,
                                                 InterruptedException
   {
      Sender sender = new Sender();
      Receiver receiver = new Receiver(sender);
      ExecutorService exec = Executors.newCachedThreadPool();
      exec.execute(sender);
      exec.execute(receiver);
      TimeUnit.SECONDS.sleep(5);
      exec.shutdownNow();
   }
}

class Sender implements Runnable
{
   private Random rand = new Random(47);
   private TransferQueue<Character> charQueue = new LinkedTransferQueue<>();

   TransferQueue<Character> getCharQueue()
   {
      return charQueue;
   }

   @Override
   public void run()
   {
      try
      {
         while (true)
         {
            for (char c = 'A'; c <= 'z'; c++)
            {
               charQueue.transfer(c);
               TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
            }
         }
      }
      catch (InterruptedException e)
      {
         System.out.println(getClass().getName() + " sleep interrupted: " + e);
      }
   }
}

class Receiver implements Runnable
{
   private TransferQueue<Character> charQueue;

   Receiver(Sender aSender) throws IOException
   {
      charQueue = aSender.getCharQueue();
   }

   @Override
   public void run()
   {
      try
      {
         while (true)
         {
            // Блокируется до появления следующего символа.
            System.out.print("Read " + charQueue.take() + ", ");
         }
      }
      catch (InterruptedException intEx)
      {
         System.out.println(intEx + " Receiver interrupted");
      }
   }
}
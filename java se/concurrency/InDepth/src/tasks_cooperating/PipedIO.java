package tasks_cooperating;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 05.12.12
 * Time: 7:49
 * Использование каналов ввода/вывода между потоками.
 */
public class PipedIO
{
   public static void main(String[] args) throws IOException,
                                                 InterruptedException
   {
      Sender sender = new Sender();
      Receiver receiver = new Receiver(sender);
      ExecutorService exec = Executors.newCachedThreadPool();
      exec.execute(sender);
      exec.execute(receiver);
      TimeUnit.SECONDS.sleep(4);
      exec.shutdownNow();
   }
}

class Sender implements Runnable
{
   private Random rand = new Random(47);
   private PipedWriter out = new PipedWriter();

   PipedWriter getPipedWriter()
   {
      return out;
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
               out.write(c);
               TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
            }
         }
      }
      catch (IOException | InterruptedException e)
      {
         if (e instanceof IOException)
            System.out.println(getClass().getName() + " write exception: " + e);
         else
            System.out.println(getClass().getName() + " sleep interrupted: " + e);
      }
   }
}

class Receiver implements Runnable
{
   private PipedReader in;

   Receiver(Sender sender) throws IOException
   {
      in = new PipedReader(sender.getPipedWriter());
   }

   @Override
   public void run()
   {
      try
      {
         while (true)
         {
            // Блокируется до появления следующего символа.
            System.out.print("Read " + (char) in.read() + ", ");
         }
      }
      catch (IOException ioEx)
      {
         System.out.println(ioEx + " Receiver read exception");
      }
   }
}

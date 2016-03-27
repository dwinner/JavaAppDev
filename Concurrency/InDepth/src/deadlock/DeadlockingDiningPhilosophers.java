package deadlock;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 05.12.12
 * Time: 8:49
 * Демонстрация скрытой возможности взаимной блокировки. Args: ponder size timeout
 */
public class DeadlockingDiningPhilosophers
{
   public static void main(String[] args) throws InterruptedException,
                                                 IOException
   {
      int ponder = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_PONDER_FACTOR;
      int size = args.length > 1 ? Integer.parseInt(args[1]) : DEFAULT_SIZE;

      ExecutorService exec = Executors.newCachedThreadPool();
      Chopstick[] sticks = new Chopstick[size];
      for (int i = 0; i < sticks.length; i++)
         sticks[i] = new Chopstick();
      for (int i = 0; i < size; i++)
         exec.execute(new Philosopher(sticks[i], sticks[(i + 1) % size], i, ponder));
      if (args.length == 3 && args[2].equals("timeout"))
         TimeUnit.SECONDS.sleep(5);
      else
      {
         System.out.println("Press the 'Enter' to exit the program");
         System.in.read();
      }
      exec.shutdownNow();
   }

   private static final int DEFAULT_PONDER_FACTOR = 0;   // Коэффициент ожидания
   private static final int DEFAULT_SIZE = 5;   // Количество палочек для еды
}

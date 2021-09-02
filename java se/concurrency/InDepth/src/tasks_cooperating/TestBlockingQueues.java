package tasks_cooperating;

import basic_threading.LiftOff;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 04.12.12
 * Time: 19:58
 * Виды блокирующих очередей.
 */
public class TestBlockingQueues
{
   public static void main(String[] args)
   {
      test("LinkedBlockingQueue",   // Неограниченный размер
           new LinkedBlockingQueue<LiftOff>());
      test("ArrayBlockingQueue",    // Фиксированный размер
           new ArrayBlockingQueue<LiftOff>(3));
      test("SynchronousQueue",      // Одноэлементная очередь
           new SynchronousQueue<LiftOff>());
   }

   static void getKey()
   {
      /* Устраняем различие между Windows/Linux в длине
      нажатия клавиши Enter */
      try
      {
         new BufferedReader(new InputStreamReader(System.in)).readLine();
      }
      catch (IOException ioEx)
      {
         throw new RuntimeException(ioEx);
      }
   }

   static void getKey(String message)
   {
      System.out.print(message);
      getKey();
   }

   static void test(String msg, BlockingQueue<LiftOff> queue)
   {
      System.out.println(msg);
      LiftOffRunner runner = new LiftOffRunner(queue);
      Thread t = new Thread(runner);
      t.start();
      for (int i = 0; i < 5; i++)
         runner.add(new LiftOff(5));
      getKey("Press 'Enter' (" + msg + ")");
      t.interrupt();
      System.out.println("Finished " + msg + " test");
   }
}

class LiftOffRunner implements Runnable
{
   private BlockingQueue<LiftOff> rockets;

   LiftOffRunner(BlockingQueue<LiftOff> queue)
   {
      rockets = queue;
   }

   void add(LiftOff lo)
   {
      try
      {
         rockets.put(lo);
      }
      catch (InterruptedException e)
      {
         System.out.println("Interrupted during put()");
      }
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            LiftOff rocket = rockets.take();
            rocket.run();  // Используем тот же поток
         }
      }
      catch (InterruptedException e)
      {
         System.out.println("Waking from take()");
      }
      System.out.println("Exiting LiftOffRunner");
   }
}

package synchronizers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 04.11.12
 * Time: 19:59
 * Циклический барьер для набора потоков.
 */
public class CyclicBarrierTest
{
   public static void main(String[] args)
   {
      CyclicBarrier barrier = new CyclicBarrier(3, new BarAction());
      System.out.println("Starting");

      // Первая фаза барьера
      new MyThread(barrier, "A-1");
      new MyThread(barrier, "B-1");
      new MyThread(barrier, "C-1");

      // Вторая фаза барьера
      new MyThread(barrier, "A-2");
      new MyThread(barrier, "B-2");
      new MyThread(barrier, "C-2");

      // Третья фаза барьера
      new MyThread(barrier, "A-3");
      new MyThread(barrier, "B-3");
      new MyThread(barrier, "C-3");
   }
}

/**
 * Поток, использующий циклический барьер.
 */
class MyThread implements Runnable
{
   MyThread(CyclicBarrier cyclicBarrier, String name)
   {
      this.cyclicBarrier = cyclicBarrier;
      this.name = name;
      new Thread(this).start();
   }

   @Override
   public void run()
   {
      System.out.println(name);
      try
      {
         cyclicBarrier.await();
      }
      catch (InterruptedException | BrokenBarrierException e)
      {
         if (e instanceof InterruptedException)
            Thread.currentThread().interrupt();
         else
            throw new RuntimeException(e);
      }
   }

   private CyclicBarrier cyclicBarrier;
   private String name;
}

/**
 * Поток, который будет вызван по достижении циклического барьера.
 */
class BarAction implements Runnable
{
   @Override
   public void run()
   {
      System.out.println("Barrier reached!");
   }
}

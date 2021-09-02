import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 17.11.12
 * Time: 15:14
 * Простой пример исполнителя.
 */
public class ExecutorTest
{
   public static void main(String[] args)
   {
      CountDownLatch firstLatch = new CountDownLatch(5);
      CountDownLatch secondLatch = new CountDownLatch(5);
      CountDownLatch thirdLatch = new CountDownLatch(5);
      CountDownLatch thourthLatch = new CountDownLatch(5);
      ExecutorService exeService = Executors.newFixedThreadPool(2);
      System.out.println("Starting");
      // Запуск потоков через исполнитель
      exeService.execute(new CountDownThread("A", firstLatch));
      exeService.execute(new CountDownThread("B", secondLatch));
      exeService.execute(new CountDownThread("C", thirdLatch));
      exeService.execute(new CountDownThread("D", thourthLatch));
      try
      {
         firstLatch.await();
         secondLatch.await();
         thirdLatch.await();
         thourthLatch.await();
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }
      exeService.shutdown();
      System.out.println("Done");
   }
}

class CountDownThread implements Runnable
{
   CountDownThread(String name, CountDownLatch latch)
   {
      this.name = name;
      this.latch = latch;
   }

   @Override
   public void run()
   {
      for (int i = 0; i < 5; i++)
      {
         System.out.println(name + ": " + i);
         latch.countDown();
      }
   }

   private String name;
   private CountDownLatch latch;
}

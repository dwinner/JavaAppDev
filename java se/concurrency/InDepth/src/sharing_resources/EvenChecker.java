package sharing_resources;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 23.11.12 Time: 17:56 Задача, потребляющая числа.
 */
public class EvenChecker implements Runnable
{
   public static int DEFAULT_THREAD_COUNT = 10;
   private IntGenerator generator;
   private final int id;

   public EvenChecker(IntGenerator generator, int id)
   {
      this.generator = generator;
      this.id = id;
   }

   @Override
   public void run()
   {
      while (!generator.isCanceled())
      {
         int val = generator.next();
         if (val % 2 != 0)
         {
            System.out.println(val + " is not even!");
            generator.cancel();  // Отмена всех EvenChecker
         }
      }
   }

   /**
    * Тестирование произвольного типа IntGenerator
    *
    * @param gp Генератор
    * @param count Число потоков
    */
   public static void test(IntGenerator gp, int count)
   {
      System.out.println("Press Ctrl+C to exit the program");
      ExecutorService exec = Executors.newCachedThreadPool();
      for (int i = 0; i < count; i++)
      {
         exec.execute(new EvenChecker(gp, i));
      }
      exec.shutdown();
   }

   /**
    * Тестирование произвольного типа IntGenerator
    *
    * @param gp Генератор
    */
   public static void test(IntGenerator gp)
   {
      test(gp, DEFAULT_THREAD_COUNT);
   }

}

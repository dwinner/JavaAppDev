import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 17.11.12
 * Time: 22:39
 * Интерфейс Callable.
 */
public class CallableTest
{
   public static void main(String[] args)
   {
      ExecutorService executorService = Executors.newFixedThreadPool(3);
      Future<Integer> future1;
      Future<Double> future2;
      Future<Integer> future3;

      System.out.println("Starting");

      future1 = executorService.submit(new Sum(10));
      future2 = executorService.submit(new Hypot(3, 4));
      future3 = executorService.submit(new Factorial(5));

      try
      {
         System.out.println(future1.get(10, TimeUnit.MILLISECONDS));
         System.out.println(future2.get(10, TimeUnit.MILLISECONDS));
         System.out.println(future3.get(10, TimeUnit.MILLISECONDS));
      }
      catch (InterruptedException | ExecutionException | TimeoutException e)
      {
         e.printStackTrace();
      }

      executorService.shutdown();
      System.out.println("Done");
   }
}

class Sum implements Callable<Integer>
{
   Sum(int value)
   {
      stop = value;
   }

   @Override
   public Integer call() throws Exception
   {
      int sum = 0;
      for (int i = 1; i <= stop; i++)
         sum += i;
      return sum;
   }

   private int stop;
}

class Hypot implements Callable<Double>
{
   Hypot(double aFirstSide, double aSecondSide)
   {
      firstSide = aFirstSide;
      secondSide = aSecondSide;
   }

   @Override
   public Double call() throws Exception
   {
      return Math.sqrt(firstSide * firstSide + secondSide * secondSide);
   }

   private double firstSide;
   private double secondSide;
}

class Factorial implements Callable<Integer>
{
   Factorial(int value)
   {
      stop = value;
   }

   @Override
   public Integer call() throws Exception
   {
      int factor = 1;
      for (int i = 2; i <= stop; i++)
         factor *= i;
      return factor;
   }

   private int stop;
}

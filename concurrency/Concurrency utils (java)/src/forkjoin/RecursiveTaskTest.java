package forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 18.11.12
 * Time: 14:42
 * Параллельное выполнение при возврате значений.
 */
public class RecursiveTaskTest
{
   public static void main(String[] args)
   {
      ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
      double[] nums = new double[5000];
      for (int i = 0; i < nums.length; i++)
         nums[i] = (((i % 2) == 0) ? i : -i);
      Sum task = new Sum(nums, 0, nums.length);
      double summation = forkJoinPool.invoke(task);
      System.out.println("Summation " + summation);
   }
}

class Sum extends RecursiveTask<Double>
{
   Sum(double[] values, int start, int end)
   {
      data = values;
      this.start = start;
      this.end = end;
   }

   @Override
   protected Double compute()
   {
      double sum = 0;
      if ((end - start) < seqThreshold)
      {  // Последовательное выполнение
         for (int i = start; i < end; i++)
            sum += data[i];
      }
      else
      {  // Дальнейшее разделение задач
         int middle = (start + end) / 2;
         Sum subTaskA = new Sum(data, start, middle);
         Sum subTaskB = new Sum(data, middle, end);
         // Асинхронный запуск разделенных задач
         subTaskA.fork();
         subTaskB.fork();
         // Ждем результаты и объединяем их
         sum = subTaskA.join() + subTaskB.join();
      }
      return sum;
   }

   private static final int seqThreshold = 500; // Порог
   private double[] data;
   private int start;
   private int end;
}

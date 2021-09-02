package forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 18.11.12
 * Time: 13:11
 * Простой пример ветвления-объединения задач для параллельной обработки.
 */
public class ForkJoinTest
{
   public static void main(String[] args)
   {
      ForkJoinPool pool = new ForkJoinPool();

      double[] numbers = new double[100000];
      for (int i = 0; i < numbers.length; i++)
         numbers[i] = i;
      System.out.println("A portion of the original sequence:");
      for (int i = 0; i < 10; i++)
         System.out.print(numbers[i] + " ");
      System.out.println("\n");

      SqrtTransform transformTask = new SqrtTransform(numbers, 0, numbers.length);
      pool.invoke(transformTask);   // Запуск главной задачи

      System.out.println("A portion of the transformed sequence:");
      for (int i = 0; i < 10; i++)
         System.out.format("%.4f ", numbers[i]);
      System.out.println();
   }
}

class SqrtTransform extends RecursiveAction
{
   SqrtTransform(double[] values, int aStart, int anEnd)
   {
      data = values;
      start = aStart;
      end = anEnd;
   }

   @Override
   protected void compute()
   {
      if ((end - start) < seqThreshold)
      {  // Последовательная обработка
         for (int i = start; i < end; i++)
            data[i] = Math.sqrt(data[i]);
      }
      else
      {  // Дальнейшее разделение данных для параллельной обработки
         int middle = (start + end) / 2;
         invokeAll(new SqrtTransform(data, start, middle),
                   new SqrtTransform(data, middle, end));
      }
   }

   private double[] data;
   private int start;
   private int end;
   private static final int seqThreshold = 1000;   // Пороговое значение
}

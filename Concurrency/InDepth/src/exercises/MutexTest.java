package exercises;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 24.11.12 Time: 3:11 Защита доступа к данным.
 */
public class MutexTest
{
   public static void main(String[] args)
   {
      FactorialGenerator.test(new SyncSharedData(new SharedData()));
   }

}

/**
 * Класс для гипотетически разделяемого объекта.
 */
class SharedData
{
   private volatile boolean cancelled = false;
   private long beginFactor = 1;
   private BigInteger currentFactor = new BigInteger(Long.toString(beginFactor));
   private List<BigInteger> factorials = new ArrayList<>();

   /**
    * Генерирование очередного факториала числа.
    *
    * @return Факториал числа
    */
   BigInteger factor()
   {
      beginFactor++;
      BigInteger factor = new BigInteger("1");
      for (long i = 1; i < beginFactor; i++)
      {
         factor = factor.multiply(new BigInteger(Long.toString(i)));
      }
      factorials.add(factor);
      currentFactor = factor;
      return factor;
   }

   /**
    * Получение последнего значения факториала
    *
    * @return Последнее значение факториала
    */
   BigInteger getCurrentFactor()
   {
      return currentFactor;
   }

   /**
    * Проверка упорядоченности чисел в списке по возрастанию.
    *
    * @return true, если проверка закончилась успехом.
    */
   boolean checkForSort()
   {
      if (factorials.size() <= 1)
      {
         return true;
      }
      for (int i = 0; i < factorials.size() - 1; i++)
      {
         if (factorials.get(i).compareTo(factorials.get(i + 1)) > 0)
         {
            return false;
         }
      }
      return true;
   }

   /**
    * Проверка уникальности факториалов.
    *
    * @return true, если все факториалы уникальны.
    */
   boolean checkForUnique()
   {
      if (factorials.size() <= 1)
      {
         return true;
      }
      Set<BigInteger> factorSet = new HashSet<>(factorials.size());  // FIXME: Лишнее создание объектов
      boolean unique;
      for (BigInteger aFactor : factorials)
      {
         unique = factorSet.add(aFactor);
         if (!unique)
         {
            return false;
         }
      }
      return true;
   }

   /**
    * Отмена генерации.
    */
   void cancel()
   {
      cancelled = true;
   }

   /**
    * Проверка отмены генератора
    *
    * @return true, если генератор был отменен
    */
   boolean isCanceled()
   {
      return cancelled;
   }

   /**
    * Получение списка факториалов числа.
    *
    * @return Список факториалов числа.
    */
   List<BigInteger> getFactorials()
   {
      return Collections.unmodifiableList(factorials);
   }

   /**
    * Печать факториалов.
    */
   void printFactorials()
   {
      for (BigInteger aFactor : getFactorials())
      {
         System.out.print(aFactor + " ");
      }
      System.out.println();
   }

}

/**
 * Синхронизированный декоратор для SharedData
 */
class SyncSharedData extends SharedData
{
   SyncSharedData(SharedData sharedData)
   {
      this.sharedData = sharedData;
   }

   @Override
   synchronized BigInteger factor()
   {
      return sharedData.factor();
   }

   @Override
   synchronized List<BigInteger> getFactorials()
   {
      return sharedData.getFactorials();
   }

   @Override
   synchronized boolean checkForSort()
   {
      return sharedData.checkForSort();
   }

   @Override
   synchronized void printFactorials()
   {
      sharedData.printFactorials();
   }

   @Override
   public void cancel()
   {
      sharedData.cancel();
   }

   @Override
   synchronized BigInteger getCurrentFactor()
   {
      return sharedData.getCurrentFactor();
   }

   @Override
   boolean isCanceled()
   {
      return sharedData.isCanceled();
   }

   @Override
   synchronized boolean checkForUnique()
   {
      return sharedData.checkForUnique();
   }

   private SharedData sharedData;
}

/**
 * Задача, потребляющая факториалы чисел.
 */
class FactorialGenerator implements Runnable
{
   private SharedData sharedData;
   static final int DEFAULT_THREAD_COUNT = 100;

   FactorialGenerator(SharedData data)
   {
      sharedData = data;
   }

   @Override
   public void run()
   {
      while (!sharedData.isCanceled())
      {
         BigInteger nextFactor = sharedData.factor();
         System.out.println("Next factorial: " + nextFactor);
         Thread.yield();
         System.out.println("Current factorial: " + sharedData.getCurrentFactor());
         if (!sharedData.checkForSort())
         {
            // Конфликт доступа: порядок вставки был нарушен
            System.out.println("Concurrent conflict: Factorials must be placed at sorted order");
            sharedData.cancel();
         }
         Thread.yield();
         if (!sharedData.checkForUnique())
         {
            // Конфликт доступа: факториалы не уникальны
            System.out.println("Concurrent conflict: Factorials must be unique");
            sharedData.cancel();
         }
         sharedData.printFactorials();
      }
   }

   /**
    * Тестирование данных на корретность доступа в многопоточном режиме
    *
    * @param sharedData Разделяемые данные
    * @param threadCount Количество потоков для запуска
    */
   static void test(SharedData sharedData, int threadCount)
   {
      System.out.println("Press Ctrl+C to exit the program");
      ExecutorService exeService = Executors.newFixedThreadPool(threadCount);
      for (int i = 0; i < threadCount; i++)
      {
         exeService.execute(new FactorialGenerator(sharedData));
      }
      exeService.shutdown();
   }

   static void test(SharedData sharedData)
   {
      test(sharedData, DEFAULT_THREAD_COUNT);
   }

}

package apputils;

import java.lang.reflect.Array;
import java.util.Random;

/**
 * Генератор массивов для некоторых классов оболочек.
 *
 * @author Denis
 * @version 0.0.1 10-12-2012
 */
public class ArrayGenerator
{
   /**
    * Создание массива обобщенного типа
    *
    * @param <T> Параметр типа массива
    * @param arrayType Тип массива
    * @param length длина массива
    * @return Новый массив типа обощенного типа
    */
   @SuppressWarnings("unchecked")
   public static <T> T[] newArray(Class<T> arrayType, int length)
   {
      return (T[]) Array.newInstance(arrayType, length);
   }

   /**
    * Заполняет массив случайными целыми значениями.
    *
    * @param array Заполненный массив
    */
   public static void fillArrayByRandomValues(Integer[] array)
   {
      for (int i = 0; i < array.length; i++)
      {
         array[i] = random.nextInt();
      }
   }

   /**
    * Заполняет массив случайными целыми значениями.
    *
    * @param array Заполненный массив
    * @param limit Предел генератора случаных чисел
    */
   public static void fillArrayByRandomValues(Integer[] array, int limit)
   {
      for (int i = 0; i < array.length; i++)
      {
         array[i] = random.nextInt(limit);
      }
   }

   /**
    * Заполняет массив случайными вещественными значениями двойной точности.
    *
    * @param array Заполненный массив
    */
   public static void fillArrayByRandomValues(Double[] array)
   {
      for (int i = 0; i < array.length; i++)
      {
         array[i] = random.nextDouble();
      }
   }

   /**
    * Заполняет массив случайными длинными целыми значениями.
    *
    * @param array Заполненный массив
    */
   public static void fillArrayByRandomValues(Long[] array)
   {
      for (int i = 0; i < array.length; i++)
      {
         array[i] = random.nextLong();
      }
   }

   /**
    * Заполняет массив случайными вещественными значениями.
    *
    * @param array Заполненный массив
    */
   public static void fillArrayByRandomValues(Float[] array)
   {
      for (int i = 0; i < array.length; i++)
      {
         array[i] = random.nextFloat();
      }
   }

   private ArrayGenerator()
   {
   }

   private static Random random = new Random();
}

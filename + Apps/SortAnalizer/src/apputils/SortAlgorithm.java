package apputils;

import apputils.SortingAlgs;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SortEntity;

/**
 * Абстрактный класс для алгоритмов сортировки.
 *
 * @author Denis
 * @version 0.0.1 14-12-2012
 */
public abstract class SortAlgorithm
{
   /**
    * Получение типа алгоритма сортировки.
    *
    * @return Тип алгоритма сортировки.
    */
   public SortingAlgs getAlgType()
   {
      return algType;
   }

   /**
    * Получение объекта-сущности для алгоритма сортировки.
    *
    * @return Объект-сущность для алгоритма сортировки.
    */
   public SortEntity getSortEntity()
   {
      return sortEntity;
   }

   /**
    * Template-метод для измерения времени и собственно сортировки.
    *
    * @param arrayToSort Исходный массив сравниваемых элементов
    */
   public final void sortTemplate(Comparable[] arrayToSort)
   {
      SortEntity entity = getSortEntity();
      long startTime = System.nanoTime();
      sort(arrayToSort);
      long endTime = System.nanoTime();
      entity.setSortTime(endTime - startTime);
   }

   /**
    * Стратегический метод получения алгоритма сортировки.
    *
    * @param sortingAlgs Один из алгоритмов сортировки
    * @return Новый объект алгоритма сортировки
    */
   public static SortAlgorithm newSortAlgorithm(SortingAlgs sortingAlgs)
   {
      switch (sortingAlgs)
      {
         case DEFAULT:
            return newDefaultSort();
         case SELECTION:
            return newSelectionSort();
         case INSERTION:
            return newInsertionSort();
         case SHELL:
            return newShellSort();
         case MERGE:
            return newMergeSort();
         case MERGE_BOTTOM_UP:
            return newMergeBottomUpSort();
         case QUICK:
            return newQuickSort();
         case QUICK_TRIPLE:
            return newQuickTripleSort();
         default:
            return newDefaultSort();
      }
   }

   /**
    * Метод-генератор получения сортировки по умолчанию.
    *
    * @return Объект для сортировки по умолчанию.
    */
   public static SortAlgorithm newDefaultSort()
   {
      return new DefaultSort(SortingAlgs.DEFAULT);
   }

   /**
    * Метод-генератор получения сортировки по выбором.
    *
    * @return Объект для сортировки по выбором.
    */
   public static SortAlgorithm newSelectionSort()
   {
      return new SelectionSort(SortingAlgs.SELECTION);
   }

   /**
    * Метод-генератор получения сортировки вставками.
    *
    * @return Объект для сортировки вставками.
    */
   public static SortAlgorithm newInsertionSort()
   {
      return new InsertionSort(SortingAlgs.INSERTION);
   }

   /**
    * Метод-генератор получения сортировки методом Шелла.
    *
    * @return Объект для сортировки методом Шелла.
    */
   public static SortAlgorithm newShellSort()
   {
      return new ShellSort(SortingAlgs.SHELL);
   }

   /**
    * Метод-генератор получения сортировки слиянием.
    *
    * @return Объект для сортировки слиянием.
    */
   public static SortAlgorithm newMergeSort()
   {
      return new MergeSort(SortingAlgs.MERGE);
   }

   /**
    * Метод-генератор получения сортировки слиянием снизу вверх.
    *
    * @return Объект для сортировки слиянием снизу вверх.
    */
   public static SortAlgorithm newMergeBottomUpSort()
   {
      return new MergeBottomUpSort(SortingAlgs.MERGE_BOTTOM_UP);
   }

   /**
    * Метод-генератор получения быстрой сортировки.
    *
    * @return Объект для быстрой сортировки.
    */
   public static SortAlgorithm newQuickSort()
   {
      return new QuickSort(SortingAlgs.QUICK);
   }

   /**
    * Метод-генератор получения быстрой сортировки тройным разбиением.
    *
    * @return Объект для быстрой сортировки тройным разбиением.
    */
   public static SortAlgorithm newQuickTripleSort()
   {
      return new QuickTripleSort(SortingAlgs.QUICK_TRIPLE);
   }

   /**
    * Создает объект алгоритма сортировки.
    *
    * @param anAlgType Тип алгоритма сортировки.
    */
   protected SortAlgorithm(SortingAlgs anAlgType)
   {
      algType = anAlgType;
      sortEntity = new SortEntity(algType);
   }

   /**
    * Сортирует массив в порядке возрастания.
    *
    * @param arrayToSort Исходный массив сравниваемых элементов
    */
   protected abstract void sort(Comparable[] arrayToSort);

   /**
    * Меняет местами элементы, расположенные по индексам.
    *
    * @param <E> Параметр обобщенного типа
    * @param anArray Исходный массив
    * @param i Первый индекс для обмена
    * @param j Второй индекс для обмена
    */
   protected static <E> void exchange(E[] anArray, int i, int j)
   {
      E temp = anArray[i];
      anArray[i] = anArray[j];
      anArray[j] = temp;
   }

   /**
    * Сравнение элементов
    *
    * @param <E> Параметр сравниваемого типа
    * @param first Первый элемент
    * @param second Второй элемент
    * @return true, если первый элемент меньше второго, false в противном случае
    */
   protected static <E extends Comparable<E>> boolean less(E first, E second)
   {
      return first.compareTo(second) < 0;
   }

   /**
    * Проверяет, отсортирован ли массив по возрастанию
    *
    * @param <E> Параметр сравниваемого типа
    * @param anArray Исходный массив
    * @return true, если массив отсортирован по возрастанию, false - если не отсортирован
    */
   protected static <E extends Comparable<E>> boolean isSorted(E[] anArray)
   {
      return isSorted(anArray, false);
   }

   /**
    * Проверяет, отсортирован ли массив
    *
    * @param <E> Параметр сравниваемого типа
    * @param anArray Исходный массив
    * @param reverseOrder Порядок сортировки; true - проверяет сортировку по убыванию, false - по возрастанию.
    * @return true, если массив отсортирован, false - если не отсортирован
    */
   protected static <E extends Comparable<E>> boolean isSorted(E[] anArray, boolean reverseOrder)
   {
      if (!reverseOrder)
      {
         for (int i = 1; i < anArray.length; i++)
         {
            if (less(anArray[i], anArray[i - 1]))
            {
               return false;
            }
         }
         return true;
      }
      else
      {
         for (int i = 1; i < anArray.length; i++)
         {
            if (less(anArray[i - 1], anArray[i]))
            {
               return false;
            }
         }
         return true;
      }
   }

   private SortingAlgs algType;     // Тип алгоритма сортировки.
   private SortEntity sortEntity;   // Тип для отслеживания сортировки.

   /**
    * Сортировка по-умолчанию.
    */
   private static class DefaultSort extends SortAlgorithm
   {
      DefaultSort(SortingAlgs anAlgType)
      {
         super(anAlgType);
      }

      @Override
      protected void sort(Comparable[] arrayToSort)
      {
         // Имитатор сортировки
         final SortEntity currentEntity = getSortEntity();
         final int increment = 5;
         final Timer progressTimer = new Timer();
         progressTimer.scheduleAtFixedRate(new TimerTask()
         {
            @Override
            public void run()
            {
               if (currentProgress != 100.0)
               {
                  currentProgress += increment;
               }
               else
               {
                  progressTimer.cancel();
               }
               currentEntity.setSortProgress(currentProgress);
            }

            private double currentProgress = .0;
         }, 100, 100);
         Arrays.sort(arrayToSort);  // Сортировка
      }

   }

   /**
    * Сортировка выбором.
    */
   private static class SelectionSort extends SortAlgorithm
   {
      SelectionSort(SortingAlgs anAlgType)
      {
         super(anAlgType);
      }

      @Override
      @SuppressWarnings("unchecked")
      protected void sort(Comparable[] arrayToSort)
      {
         for (int i = 0; i < arrayToSort.length; i++)
         {
            int min = i;
            for (int j = i + 1; j < arrayToSort.length; j++)
            {
               if (less(arrayToSort[j], arrayToSort[min]))
               {
                  min = j;
               }
            }
            exchange(arrayToSort, i, min);
            getSortEntity().setSortProgress(100 * ((double) i / (double) arrayToSort.length));
         }
         getSortEntity().setSortProgress(100.0);
      }

   }

   /**
    * Сортировка вставкой.
    */
   private static class InsertionSort extends SortAlgorithm
   {
      InsertionSort(SortingAlgs anAlgType)
      {
         super(anAlgType);
      }

      @Override
      @SuppressWarnings("unchecked")
      protected void sort(Comparable[] arrayToSort)
      {
         for (int i = 1; i < arrayToSort.length; i++)
         {
            for (int j = i; j > 0 && less(arrayToSort[j], arrayToSort[j - 1]); j--)
            {
               exchange(arrayToSort, j, j - 1);
            }
            getSortEntity().setSortProgress(100 * ((double) i / (double) arrayToSort.length));
         }
         getSortEntity().setSortProgress(100.0);
      }

   }

   /**
    * Сортировка методом Шелла.
    */
   private static class ShellSort extends SortAlgorithm
   {
      ShellSort(SortingAlgs anAlgType)
      {
         super(anAlgType);
      }

      @Override
      @SuppressWarnings("unchecked")
      protected void sort(Comparable[] arrayToSort)
      {
         // Имитатор сортировки
         final SortEntity currentEntity = getSortEntity();
         final int increment = 5;
         final Timer progressTimer = new Timer();
         progressTimer.scheduleAtFixedRate(new TimerTask()
         {
            @Override
            public void run()
            {
               if (currentProgress != 100.0)
               {
                  currentProgress += increment;
               }
               else
               {
                  progressTimer.cancel();
               }
               currentEntity.setSortProgress(currentProgress);
            }

            private double currentProgress = .0;
         }, 100, 100);
         // Сортировка
         int arrayLenght = arrayToSort.length;
         int h = 1;
         while (h < arrayLenght / 3)
         {
            h = 3 * h + 1;
         }
         while (h >= 1)
         {
            for (int i = h; i < arrayLenght; i++)
            {
               for (int j = i; j >= h && less(arrayToSort[j], arrayToSort[j - h]); j -= h)
               {
                  exchange(arrayToSort, j, j - h);
               }
            }
            h /= 3;
         }
      }

   }

   /**
    * Сортировка слиянием.
    */
   private static class MergeSort extends SortAlgorithm
   {
      MergeSort(SortingAlgs anAlgType)
      {
         super(anAlgType);
      }

      @Override
      protected void sort(Comparable[] arrayToSort)
      {
         // Имитатор сортировки
         final SortEntity currentEntity = getSortEntity();
         final int increment = 5;
         final Timer progressTimer = new Timer();
         progressTimer.scheduleAtFixedRate(new TimerTask()
         {
            @Override
            public void run()
            {
               if (currentProgress != 100.0)
               {
                  currentProgress += increment;
               }
               else
               {
                  progressTimer.cancel();
               }
               currentEntity.setSortProgress(currentProgress);
            }

            private double currentProgress = .0;
         }, 100, 100);
         // Сортировка
         Comparable<?>[] auxArray = new Comparable<?>[arrayToSort.length];
         sort(arrayToSort, auxArray, 0, arrayToSort.length - 1);
      }

      /**
       * Рекурсивная сортировка слянием.
       *
       * @param anArray Массив сравниваемых типов
       * @param auxArray Промежуточный массив
       * @param low Нижний индекс
       * @param hi Верхний индекс
       */
      private void sort(Comparable[] anArray, Comparable[] auxArray, int low, int hi)
      {
         if (hi <= low)
         {
            return;
         }
         int middle = low + (hi - low) / 2;
         sort(anArray, auxArray, low, middle);        // Сортируем левую половину
         sort(anArray, auxArray, middle + 1, hi);     // Сортируем правую половину.
         merge(anArray, auxArray, low, middle, hi);   // Объединяем результаты.
      }

      /**
       * Промежуточное объединение массива.
       *
       * @param anArray Массив сравниваемых типов
       * @param auxArray Промежуточный массив
       * @param low Нижний индекс
       * @param middle Средний индекс
       * @param hi Верхний индекс
       */
      @SuppressWarnings("unchecked")
      protected void merge(Comparable[] anArray, Comparable[] auxArray, int low, int middle, int hi)
      {
         for (int k = low; k <= hi; k++)
         {
            auxArray[k] = anArray[k];
         }

         int i = low, j = middle + 1;
         for (int k = low; k <= hi; k++)
         {
            if (i > middle)
            {
               anArray[k] = auxArray[j];
               j++;
            }
            else if (j > hi)
            {
               anArray[k] = auxArray[i];
               i++;
            }
            else if (less(auxArray[j], auxArray[i]))
            {
               anArray[k] = auxArray[j];
               j++;
            }
            else
            {
               anArray[k] = auxArray[i];
               i++;
            }
         }
      }

   }

   /**
    * Сортировка слиянием снизу вверх.
    */
   private static class MergeBottomUpSort extends MergeSort
   {
      MergeBottomUpSort(SortingAlgs anAlgType)
      {
         super(anAlgType);
      }

      @Override
      protected void sort(Comparable[] arrayToSort)
      {
         // Имитатор сортировки
         final SortEntity currentEntity = getSortEntity();
         final int increment = 5;
         final Timer progressTimer = new Timer();
         progressTimer.scheduleAtFixedRate(new TimerTask()
         {
            @Override
            public void run()
            {
               if (currentProgress != 100.0)
               {
                  currentProgress += increment;
               }
               else
               {
                  progressTimer.cancel();
               }
               currentEntity.setSortProgress(currentProgress);
            }

            private double currentProgress = .0;
         }, 100, 100);
         // Сортировка
         int arrayLength = arrayToSort.length;
         Comparable<?>[] auxArray = new Comparable<?>[arrayLength];
         for (int n = 1; n < arrayLength; n += n)
         {
            for (int i = 0; i < arrayLength - n; i += n + n)
            {
               int low = i;
               int middle = i + n - 1;
               int hi = Math.min(i + n + n - 1, arrayLength - 1);
               merge(arrayToSort, auxArray, low, middle, hi);
            }
         }
      }

   }

   /**
    * "Быстрая" сортировка.
    */
   private static class QuickSort extends SortAlgorithm
   {
      QuickSort(SortingAlgs anAlgType)
      {
         super(anAlgType);
      }

      @Override
      protected void sort(Comparable[] arrayToSort)
      {
         // Имитатор сортировки
         final SortEntity currentEntity = getSortEntity();
         final int increment = 5;
         final Timer progressTimer = new Timer();
         progressTimer.scheduleAtFixedRate(new TimerTask()
         {
            @Override
            public void run()
            {
               if (currentProgress != 100.0)
               {
                  currentProgress += increment;
               }
               else
               {
                  progressTimer.cancel();
               }
               currentEntity.setSortProgress(currentProgress);
            }

            private double currentProgress = .0;
         }, 100, 100);
         sort(arrayToSort, 0, arrayToSort.length - 1);   // Сортировка
      }

      /**
       * Быстрая сортировка подмассива от arrayToSort[low] до arrayToSort[hi]
       *
       * @param arrayToSort Массив для сортировки
       * @param low Нижний предел
       * @param hi Верхний предел
       */
      private void sort(Comparable[] arrayToSort, int low, int hi)
      {
         if (hi <= low)
         {
            return;
         }
         int j = partition(arrayToSort, low, hi);
         sort(arrayToSort, low, j - 1);
         sort(arrayToSort, j + 1, hi);
      }

      /**
       * Часть подмассива anArray[low .. hi] через возвращение индекса j
       *
       * @param anArray Исходный массив
       * @param low Нижний предел
       * @param hi Верхний предел
       * @return Индекс границы подмассива
       */
      @SuppressWarnings("unchecked")
      private static int partition(Comparable[] anArray, int low, int hi)
      {
         int i = low;
         int j = hi + 1;
         Comparable<?> v = anArray[low];
         while (true)
         {
            while (less(anArray[++i], v))
            {
               if (i == hi)
               {
                  break;
               }
            }
            while (less(v, anArray[--j]))
            {
               if (j == low)
               {
                  break;
               }
            }
            if (i >= j)
            {
               break;
            }

            exchange(anArray, i, j);
         }
         exchange(anArray, low, j);

         return j;
      }

   }

   /**
    * Быстрая сортировка тройным разбиением.
    */
   private static class QuickTripleSort extends SortAlgorithm
   {
      QuickTripleSort(SortingAlgs anAlgType)
      {
         super(anAlgType);
      }

      @Override
      protected void sort(Comparable[] arrayToSort)
      {
         // Имитатор сортировки
         final SortEntity currentEntity = getSortEntity();
         final int increment = 5;
         final Timer progressTimer = new Timer();
         progressTimer.scheduleAtFixedRate(new TimerTask()
         {
            @Override
            public void run()
            {
               if (currentProgress != 100.0)
               {
                  currentProgress += increment;
               }
               else
               {
                  progressTimer.cancel();
               }
               currentEntity.setSortProgress(currentProgress);
            }

            private double currentProgress = .0;
         }, 100, 100);
         sortTriple(arrayToSort, 0, arrayToSort.length - 1);   // Сортировка
      }

      /**
       * Быстрая сортировка подмассива arrayToSort[low .. hi] через 3-х кратное разбиение
       *
       * @param arrayToSort Массив для сортировки
       * @param low Нижний предел
       * @param hi Верхний предел
       */
      @SuppressWarnings("unchecked")
      private void sortTriple(Comparable[] arrayToSort, int low, int hi)
      {
         if (hi <= low)
         {
            return;
         }
         int lt = low, gt = hi;
         Comparable<?> v = arrayToSort[low];
         int i = low;
         while (i <= gt)
         {
            int cmp = arrayToSort[i].compareTo(v);
            if (cmp < 0)
            {
               exchange(arrayToSort, lt++, i++);
            }
            else if (cmp > 0)
            {
               exchange(arrayToSort, i, gt--);
            }
            else
            {
               i++;
            }
         }
         sortTriple(arrayToSort, low, lt - 1);
         sortTriple(arrayToSort, gt + 1, hi);
      }

   }

}

package controller;
/*
 * TODO: Заменить Callable на Runnable.
 * TODO: Для безопасности запуска новых алгоритмов, добавить синхронизатор CountDownLatch.
 */

import apputils.SortAlgorithm;
import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * Задача, сортирующая массив и возвращающая время сортировки.
 *
 * @author Denis
 * @version 0.0.1 14-12-2012
 */
public class SortTask implements Callable<Long>
{
   /**
    * Конструктор задачи сортировки.
    *
    * @param aSortAlgorithm Алгоритм сортировки
    * @param anArray Массив для сортировки
    */
   public SortTask(SortAlgorithm aSortAlgorithm, Comparable[] anArray)
   {
      sortAlg = aSortAlgorithm;
      arrayToSort = Arrays.copyOf(anArray, anArray.length);
   }

   @Override
   public Long call() throws Exception
   {
      sortAlg.sortTemplate(arrayToSort);
      return sortAlg.getSortEntity().getSortTime();
   }

   private SortAlgorithm sortAlg;
   private Comparable[] arrayToSort;
}

package model;

import apputils.SortingAlgs;
import java.util.Observable;

/**
 * Сущность для объединения деталей сортировочных алгоритмов.
 *
 * @author Denis
 * @version 0.0.1
 */
public class SortEntity extends Observable
{
   /**
    * Конструктор сортировочной сущности.
    *
    * @param sortingAlgs Тип алгоритма сортировки
    */
   public SortEntity(SortingAlgs sortingAlgs)
   {
      algName = sortingAlgs.getAlgName();
      sortProgress = .0;
      sortTime = 0L;
      sortOverhead = sortingAlgs.getAlgName();
   }

   /**
    * Получение читабельного названия алгоритма сортировки.
    *
    * @return Читабельное название алгоритма сортировки
    */
   public String getAlgName()
   {
      return algName;
   }

   /**
    * Получение прогресса алгоритма сортировки.
    *
    * @return Прогресс алгоритма сортировки
    */
   public double getSortProgress()
   {
      return sortProgress;
   }

   /**
    * Получение времени, которое потребовалось для сортировки
    *
    * @return Время, ушедшее на сортировку
    */
   public long getSortTime()
   {
      return sortTime;
   }

   /**
    * Наименование для анализа.
    *
    * @return Наименование для анализа
    */
   public String getSortOverhead()
   {
      return sortOverhead;
   }

   /**
    * Установка прогресса и уведомление наблюдателей.
    *
    * @param newValue Новое значение прогресса
    */
   public void setSortProgress(double newValue)
   {
      sortProgress = newValue;
      setChanged();
      notifyObservers();
   }

   /**
    * Установка времени алгоритма и уведомление наблюдателей.
    *
    * @param newValue Новое значение времени
    */
   public void setSortTime(long newValue)
   {
      sortTime = newValue;
      setChanged();
      notifyObservers();
   }

   private String algName;
   private double sortProgress;
   private long sortTime;
   private String sortOverhead;
}

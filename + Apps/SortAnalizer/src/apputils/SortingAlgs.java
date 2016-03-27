package apputils;

/**
 * Перечисление алгоритмов сортировки.
 *
 * @author Denis
 * @version 0.0.1 10-12-2012
 */
public enum SortingAlgs
{
   DEFAULT("Default sort"), // Сортировка по умолчанию
   SELECTION("Selection sort"), // Сортировка выбором
   INSERTION("Insertion sort"), // Сортировка вставкой
   SHELL("Shell sort"), // Сортировка Шелла
   MERGE("Merge sort"), // Сортировка слиянием
   MERGE_BOTTOM_UP("Merge bottom up sort"), // Сортировка слиянием снизу вверх
   QUICK("Quick sort"), // Быстрая сортировка
   QUICK_TRIPLE("Quick 3 way sort");         // Быстрая сортировка тройным разбиением

   /**
    * Читабельное имя алгоритма.
    *
    * @return Читабельное имя алгоритма
    */
   public String getAlgName()
   {
      return algName;
   }

   private SortingAlgs(String algorothmName)
   {
      algName = algorothmName;
   }

   private String algName; // Читабельное имя алгоритма
}

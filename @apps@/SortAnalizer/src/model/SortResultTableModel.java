package model;

import java.util.*;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.table.AbstractTableModel;
import model.SortEntity;

/**
 * Модель таблицы сортировки.
 *
 * @author Denis
 * @version 0.0.1 15-12-2012
 */
public class SortResultTableModel extends AbstractTableModel implements Observer
{
   /**
    * Конструктор модели таблицы.
    */
   public SortResultTableModel()
   {
      sortEntities = new LinkedList<>();  // FIXME: Проверить на межпоточные конфликты
   }

   /**
    * Конструктор модели таблицы
    *
    * @param sortEntityList Список сущностей для модели таблицы
    */
   public SortResultTableModel(List<SortEntity> sortEntityList)
   {
      sortEntities = sortEntityList;     // FIXME: Проверить на межпоточные конфликты
   }

   /**
    * Получение всех сущностей таблицы.
    *
    * @return Список сущностей таблицы.
    */
   public List<SortEntity> getSortEntities()
   {
      return Collections.unmodifiableList(sortEntities);
   }

   @Override
   public int getRowCount()   // Количество строк
   {
      return sortEntities.size();
   }

   @Override
   public int getColumnCount()   // Количество столбцов
   {
      return columnNames.length;
   }

   @Override
   public Object getValueAt(int rowIndex, int columnIndex)  // Получение значений в ячейках
   {
      SortEntity entity = sortEntities.get(rowIndex);
      switch (columnIndex)
      {
         case ALG_NAME_COLUMN:
            return entity.getAlgName();
         case SORT_PROGRESS_COLUMN:
            return entity.getSortProgress();
         case TIME_COLUMN:
            return entity.getSortTime();
         case STAT_OVERHEAD_COLUMN:
            return entity.getSortOverhead();
         default:
            throw new IllegalArgumentException("Column index is illegal");
      }
   }

   @Override
   public String getColumnName(int column)   // Имя заголовка столбца
   {
      return columnNames[column];
   }

   @Override
   public Class<?> getColumnClass(int columnIndex) // Класс для столбца
   {
      return columnClasses[columnIndex];
   }

   @Override
   public void update(Observable o, Object arg) // Обновление наблюдаемого объекта
   {
      int updatedIndex = sortEntities.indexOf(o);
      fireTableRowsUpdated(updatedIndex, updatedIndex);
   }

   @Override
   public boolean isCellEditable(int rowIndex, int columnIndex)   // Флаг редактирования ячейки
   {
      if (columnIndex == STAT_OVERHEAD_COLUMN)
      {
         return true;
      }
      return false;
   }

   /**
    * Добавление сущности к модели таблицы.
    *
    * @param aSortEntity
    */
   public void addEntity(SortEntity aSortEntity)
   {  // FIXME: Проверить на межпоточные конфликты
      aSortEntity.addObserver(this);
      sortEntities.add(aSortEntity);
      fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
   }

   /**
    * Получение сущности модели таблицы для конкретной строки.
    *
    * @param row Номер строки
    * @return Сущность для строки
    */
   public SortEntity getSortEntityAtRow(int row)
   {  // FIXME: Проверить на межпоточные конфликты
      if (row >= 0 && row < sortEntities.size())
      {
         return sortEntities.get(row);
      }
      else
      {
         throw new IllegalArgumentException("Row index is out of bounds: " + row);
      }
   }

   /**
    * Удаление сущности модели таблицы.
    *
    * @param row Номер строки
    */
   public void clearSortEntityAtRow(int row)
   {  // FIXME: Проверить на межпоточные конфликты
      if (row >= 0 && row < sortEntities.size())
      {
         sortEntities.remove(row);
         fireTableRowsDeleted(row, row);
      }
   }

   /**
    * Удаление всех сущностей из модели таблицы.
    */
   public void clearAllEntities()
   {  // FIXME: Проверить на межпоточные конфликты
      if (!sortEntities.isEmpty())
      {
         sortEntities.clear();
         fireTableRowsDeleted(0, getRowCount());
      }
   }

   private List<SortEntity> sortEntities; // Список значений для каждой строки
   private static final String[] columnNames =  // Имена для колонок таблицы.
   {  // TODO: i18n
      "Algorithm name",
      "Sort progress",
      "Time (in milliseconds)",
      "Stat overhead"
   };
   private static final Class[] columnClasses = // Классы для каждого значения колонки.
   {
      String.class,
      JProgressBar.class,
      Long.class,
      JButton.class
   };
   private static final int ALG_NAME_COLUMN = 0;
   private static final int SORT_PROGRESS_COLUMN = 1;
   private static final int TIME_COLUMN = 2;
   private static final int STAT_OVERHEAD_COLUMN = 3;
}

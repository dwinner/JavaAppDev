package com.corejsf;

import java.util.Arrays;
import java.util.Comparator;
import javax.faces.model.DataModel;

public class SortFilterModel<E> extends DataModel<E>
{
   private DataModel<E> model;
   private Integer[] rows;

   public SortFilterModel()   // Требуется по спецификации JSF
   {
      setWrappedData(null);
   }

   public SortFilterModel(E[] names)   // Рекомендуется в спецификации JSF
   {
      setWrappedData(names);
   }

   public SortFilterModel(DataModel<E> model)
   {
      this.model = model;
      initializeRows();
   }

   private E getData(int row)
   {
      int originalIndex = model.getRowIndex();
      model.setRowIndex(row);
      E thisRowData = model.getRowData();
      model.setRowIndex(originalIndex);
      return thisRowData;
   }

   public void sortBy(final Comparator<E> dataComp)
   {
      Comparator<Integer> rowComp = new Comparator<Integer>()
      {
         @Override
         public int compare(Integer firstInt, Integer secondInt)
         {
            E first = getData(firstInt);
            E second = getData(secondInt);
            return dataComp.compare(first, second);
         }

      };
      Arrays.sort(rows, rowComp);
   }

   // <editor-fold defaultstate="collapsed" desc="Делегируемые методы">
   @Override
   public final void setWrappedData(Object data)
   {
      model.setWrappedData(data);
      initializeRows();
   }

   @Override
   public boolean isRowAvailable()
   {
      return model.isRowAvailable();
   }

   @Override
   public int getRowCount()
   {
      return model.getRowCount();
   }

   @Override
   public E getRowData()
   {
      return model.getRowData();
   }

   @Override
   public int getRowIndex()
   {
      return model.getRowIndex();
   }

   @Override
   public void setRowIndex(int rowIndex)
   {
      model.setRowIndex(rowIndex >= 0 && rowIndex < rows.length ? rows[rowIndex] : rowIndex);
   }

   @Override
   public Object getWrappedData()
   {
      return model.getWrappedData();
   }
   // </editor-fold>

   private void initializeRows()
   {
      int rowCount = model.getRowCount();
      if (rowCount != -1)
      {
         rows = new Integer[rowCount];
         for (int i = 0; i < rowCount; ++i)
         {
            rows[i] = i;
         }
      }
   }

}

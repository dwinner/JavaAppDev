package view;
/*
 * TODO: Создать метод-генератор для объекта отображения форматированных меток времени
 * TODO: Написать объект редактирования для кнопок таблицы.
 */

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * Фабрика визуализаторов для ячеек таблицы.
 *
 * @author Denis
 * @version 0.0.1 14-12-2012
 */
public class TableRendererFactory
{
   /**
    * Объект отображения для кнопок таблицы.
    *
    * @return Объект отображения для кнопок таблицы.
    */
   public static TableCellRenderer createButtonTableCellRenderer()
   {
      return new TableCellRenderer()
      {
         @Override
         public Component getTableCellRendererComponent(JTable table,
                                                        Object value,
                                                        boolean isSelected,
                                                        boolean hasFocus,
                                                        int row,
                                                        int column)
         {
            OVERHEAD_BUTTON.setText(value.toString());
            return OVERHEAD_BUTTON;
         }

      };
   }

   /**
    * Объект отображения для индикаторов хода выполнения.
    *
    * @return Объект отображения для индикаторов хода выполнения.
    */
   public static TableCellRenderer createProgressTableCellRenderer()
   {
      return new DefaultTableCellRenderer()
      {
         @Override
         public Component getTableCellRendererComponent(JTable table,
                                                        Object value,
                                                        boolean isSelected,
                                                        boolean hasFocus,
                                                        int row,
                                                        int column)
         {
            if (value instanceof Number)
            {
               sortProgressBar.setValue(((Number) value).intValue());
               return sortProgressBar;
            }
            else
            {
               return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
         }

         private JProgressBar sortProgressBar = new JProgressBar();


         {
            sortProgressBar.setStringPainted(true);
         }

      };
   }

   private TableRendererFactory()
   {
   }

   private final static JButton OVERHEAD_BUTTON = new JButton();
}

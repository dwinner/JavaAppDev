// Создание простой модели для таблицы
import java.awt.HeadlessException;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class SimpleTableModel extends JFrame
{
    public SimpleTableModel() throws HeadlessException
    {
        super("Simple Table Model");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаем таблицу на основе нашей модели
        JTable table = new JTable(new SimpleModel());
        table.setRowHeight(32);
        getContentPane().add(new JScrollPane(table));
        
        // Выводим окно на экран
        setSize(400, 300);
        setVisible(true);
    }

    // Наша модель
    private class SimpleModel extends AbstractTableModel
    {
        // Количество строк
        @Override
        public int getRowCount()
        {
            return 100000;
        }

        // Количество столбцов
        @Override
        public int getColumnCount()
        {
            return 3;
        }

        // Данные в ячейке
        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            boolean flag = (rowIndex == 0) ? true : false;
            // Разные данные для разных столбцов
            switch (columnIndex)
            {
                case 0:     return "" + rowIndex;
                case 1:     return flag;
                case 2:     return new ImageIcon("JTable.gif");
                default:    return "Empty";
            }
        }

        // Тип данных, хранимых в столбце
        @Override
        public Class<?> getColumnClass(int column)
        {
            switch (column)
            {
                case 1:     return Boolean.class;
                case 2:     return Icon.class;
                default:    return Object.class;
            }
        }
    }

    public static void main(String[] args)
    {
        new SimpleTableModel();
    }
}
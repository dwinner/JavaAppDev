/*
 * Редактор для ячеек таблицы, отображающих даты.
 */

import java.awt.Component;
import java.awt.HeadlessException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/**
 * Редактор ячеек таблицы в виде спиннера дат.
 * @author oracle_pr1
 */
class DateCellEditor extends AbstractCellEditor implements TableCellEditor
{
    // Редактор - счетчик
    private JSpinner editor;

    // Конструктор
    DateCellEditor()
    {
        // Настраиваем счетчик
        SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        editor = new JSpinner(model);
    }

    // Возвращает компонент, применяемый для редактирования
    @Override
    public Component getTableCellEditorComponent(
        JTable table,
        Object value,
        boolean isSelected,
        int row,
        int column)
    {
        // Меняем значение и возвращаем счетчик
        editor.setValue(value);
        return editor;
    }

    // Возвращает текущее значение в редакторе
    @Override
    public Object getCellEditorValue()
    {
        return editor.getValue();
    }
}

/**
 * Применение специализированного редактора для ячеек таблицы.
 * @author oracle_pr1
 */
public class DateCellEditorTest extends JFrame
{
    // Заголовок столбцов таблицы
    private String[] columns =
    {
        "Operation", "Date"
    };
    // Данные таблицы
    private Object[][] data =
    {
        {
            "Покупка", new Date()
        },
        {
            "Продажа", new Date()
        }
    };

    public DateCellEditorTest() throws HeadlessException
    {
        super("Custom table cell editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем таблицу на основе стандартной модели
        DefaultTableModel model = new DefaultTableModel(data, columns)
        {
            // Необходимо указать тип столбца
            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return data[0][columnIndex].getClass();
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(20);
        
        // Указываем редактор для ячеек
        table.setDefaultEditor(Date.class, new DateCellEditor());

        // Выводим окно на экран
        getContentPane().add(new JScrollPane(table));
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new DateCellEditorTest();
    }
}
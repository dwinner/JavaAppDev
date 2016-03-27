/*
 * Применение стандартного редактора для таблиц
 */

import java.awt.HeadlessException;
import javax.swing.*;

public class TableDefaultEditing extends JFrame
{
    // Название столбцов
    private String[] columns =
    {
        "Name",
        "Favourite color"
    };
    // Данные для таблицы
    private String[][] data =
    {
        {
            "Иван", "Зеленый"
        },
        {
            "Александр", "Красный"
        },
        {
            "Петр", "Синий"
        }
    };

    public TableDefaultEditing() throws HeadlessException
    {
        super("Table Default editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем таблицу
        JTable table = new JTable(data, columns);
        // Настраиваем стандартный редактор
        JComboBox<String> combo = new JComboBox<>(
            new String[]
            {
                "Green",
                "Yellow",
                "White"
            });
        DefaultCellEditor editor = new DefaultCellEditor(combo);
        table.getColumnModel().getColumn(1).setCellEditor(editor);
        // Выводим окно на экран
        getContentPane().add(new JScrollPane(table));
        setSize(200, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new TableDefaultEditing();
    }
}
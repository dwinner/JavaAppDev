/*
 * Использование стандартной модели столбцов таблицы и объектов TableColumn
 */

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class UsingTableColumnModel extends JFrame
{
    // Модель столбцов
    private TableColumnModel columnModel;
    // Названия столбцов таблицы
    private String[] columnNames =
    {
        "Имя", "Любимый цвет", "Напиток"
    };
    // Данные для таблицы
    private String[][] data =
    {
        {
            "Иван", "Зеленый", "Апельсиновый сок"
        },
        {
            "Александр", "Бежевый", "Зеленый чай"
        }
    };

    public UsingTableColumnModel() throws HeadlessException
    {
        super("Using Table Column Model");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Наша таблица
        JTable table = new JTable(data, columnNames);

        // Получаем стандартную модель
        columnModel = table.getColumnModel();
        columnModel.addColumnModelListener(new TableColumnModelListener()
        {
            @Override
            public void columnAdded(TableColumnModelEvent e)
            {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void columnRemoved(TableColumnModelEvent e)
            {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void columnMoved(TableColumnModelEvent e)
            {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void columnMarginChanged(ChangeEvent e)
            {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void columnSelectionChanged(ListSelectionEvent e)
            {
                // throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        // Меняем размеры столбцов
        Enumeration<TableColumn> e = columnModel.getColumns();
        while (e.hasMoreElements())
        {
            TableColumn column = e.nextElement();
            column.setMinWidth(30);
            column.setMaxWidth(90);
        }

        // Создаем пару кнопок
        JPanel buttons = new JPanel();
        JButton move = new JButton("Поменять местами");
        move.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Меняем местами первые два столбца
                columnModel.moveColumn(0, 1);
            }
        });
        buttons.add(move);
        JButton add = new JButton("Добавить");
        add.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Добавляем столбец
                TableColumn newColumn = new TableColumn(1, 100);
                newColumn.setHeaderValue("<html><b>New!</b>");
                columnModel.addColumn(newColumn);
            }
        });
        buttons.add(add);

        // Выводим окно на экран
        getContentPane().add(new JScrollPane(table));
        getContentPane().add(buttons, BorderLayout.SOUTH);
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new UsingTableColumnModel();
    }
}
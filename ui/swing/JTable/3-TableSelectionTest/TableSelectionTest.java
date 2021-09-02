import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Эта программа демонстрирует выбор, добавление и удаление строк и столбцов.
 * @version 1.03 2007-08-01
 * @author Cay Horstmann
 */
public class TableSelectionTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new TableSelectionFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Этот фрейм показывает таблицу умножения и имеет меню для настройки
 * режимов выбора строк, столбцов и ячеек, а также для добавления и
 * удаления строк и столбцов.
 */
class TableSelectionFrame extends JFrame
{
    private DefaultTableModel model;
    private JTable table;
    private ArrayList<TableColumn> removedColumns;

    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 300;

    public TableSelectionFrame() throws HeadlessException
    {
        setTitle("TableSelectionTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Настройка таблицы умножения.

        model = new DefaultTableModel(10, 10);
        for (int i = 0; i < model.getRowCount(); i++)
            for (int j = 0; j < model.getColumnCount(); j++)
                model.setValueAt((i + 1) * (j + 1), i, j);

        table = new JTable(model);

        add(new JScrollPane(table), "Center");

        removedColumns = new ArrayList<>();

        // Создание меню.
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu selectionMenu = new JMenu("Selection");
        menuBar.add(selectionMenu);

        final JCheckBoxMenuItem rowsItem = new JCheckBoxMenuItem("Rows");
        final JCheckBoxMenuItem columnsItem = new JCheckBoxMenuItem("Columns");
        final JCheckBoxMenuItem cellsItem = new JCheckBoxMenuItem("Cells");

        rowsItem.setSelected(table.getRowSelectionAllowed());
        columnsItem.setSelected(table.getColumnSelectionAllowed());
        cellsItem.setSelected(table.getCellSelectionEnabled());

        rowsItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                table.clearSelection();
                table.setRowSelectionAllowed(rowsItem.isSelected());
                cellsItem.setSelected(table.getCellSelectionEnabled());
            }
        });
        selectionMenu.add(rowsItem);

        columnsItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                table.clearSelection();
                table.setColumnSelectionAllowed(columnsItem.isSelected());
                cellsItem.setSelected(table.getCellSelectionEnabled());
            }
        });
        selectionMenu.add(columnsItem);

        cellsItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                table.clearSelection();
                table.setCellSelectionEnabled(cellsItem.isSelected());
                rowsItem.setSelected(table.getRowSelectionAllowed());
                columnsItem.setSelected(table.getColumnSelectionAllowed());
            }
        });
        selectionMenu.add(cellsItem);

        JMenu tableMenu = new JMenu("Edit");
        menuBar.add(tableMenu);

        JMenuItem hideColumnsItem = new JMenuItem("Hide Columns");
        hideColumnsItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                int[] selected = table.getSelectedColumns();
                TableColumnModel columnModel = table.getColumnModel();

                // Удаление столбцов из представления, начиная с последнего индекса,
                // чтобы не были затронуты номера столбцов

                for (int i = selected.length - 1; i >= 0; i--)
                {
                    TableColumn column = columnModel.getColumn(selected[i]);
                    table.removeColumn(column);

                    // Хранение удаленных столбцов
                    // для команды "show columns" (показать столбцы)

                    removedColumns.add(column);
                }
            }
        });
        tableMenu.add(hideColumnsItem);

        JMenuItem showColumnsItem = new JMenuItem("Show Columns");
        showColumnsItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                // Восстановление всех удаленных столбцов
                for (TableColumn tc : removedColumns)
                    table.addColumn(tc);
                removedColumns.clear();
            }
        });
        tableMenu.add(showColumnsItem);

        JMenuItem addRowItem = new JMenuItem("Add Row");
        addRowItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                // Добавление новой строки в таблицу умножения в модели
                Integer[] newCells = new Integer[model.getColumnCount()];
                for (int i = 0; i < newCells.length; i++)
                    newCells[i] = (i + 1) * (model.getRowCount() + 1);
                model.addRow(newCells);
            }
        });
        tableMenu.add(addRowItem);

        JMenuItem removeRowsItem = new JMenuItem("Remove Rows");
        removeRowsItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                int[] selected = table.getSelectedRows();

                for (int i = selected.length - 1; i >= 0; i--)
                    model.removeRow(selected[i]);
            }
        });
        tableMenu.add(removeRowsItem);

        JMenuItem clearCellsItem = new JMenuItem("Clear Cells");
        clearCellsItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                for (int i = 0; i < table.getRowCount(); i++)
                    for (int j = 0; j < table.getColumnCount(); j++)
                        if (table.isCellSelected(i, j))
                            table.setValueAt(0, i, j);
            }
        });
        tableMenu.add(clearCellsItem);
    }
}
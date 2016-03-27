
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import javax.swing.*;
import javax.swing.table.*;

/**
 * Данная проррамма демонстрирует как работать со строками и столбцами в таблице.
 * <p/>
 * @version 1.20 2007-08-01
 * @author Cay Horstmann
 */
public class TableRowColumnTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new PlanetTableFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Данный фрейм содержит таблицу с данными о планетах.
 */
class PlanetTableFrame extends JFrame
{
    private Object[][] cells =
    {
        {
            "Mercury", 2440.0, 0, false, Color.YELLOW, new ImageIcon("Mercury.gif")
        },
        {
            "Venus", 6052.0, 0, false, Color.YELLOW, new ImageIcon("Venus.gif")
        },
        {
            "Earth", 6378.0, 1, false, Color.BLUE, new ImageIcon("Earth.gif")
        },
        {
            "Mars", 3397.0, 2, false, Color.RED, new ImageIcon("Mars.gif")
        },
        {
            "Jupiter", 71492.0, 16, true, Color.ORANGE, new ImageIcon("Jupiter.gif")
        },
        {
            "Saturn", 60268.0, 18, true, Color.ORANGE, new ImageIcon("Saturn.gif")
        },
        {
            "Uranus", 25559.0, 17, true, Color.BLUE, new ImageIcon("Uranus.gif")
        },
        {
            "Neptune", 24766.0, 8, true, Color.BLUE, new ImageIcon("Neptune.gif")
        },
        {
            "Pluto", 1137.0, 1, false, Color.BLACK, new ImageIcon("Pluto.gif")
        }
    };
    private String[] columnNames =
    {
        "Planet", "Radius", "Moons", "Gaseous", "Color", "Image"
    };
    public static final int COLOR_COLUMN = 4;
    public static final int IMAGE_COLUMN = 5;
    private JTable table;
    private HashSet<Integer> removedRowIndices;
    private ArrayList<TableColumn> removedColumns;
    private JCheckBoxMenuItem rowsItem;
    private JCheckBoxMenuItem columnsItem;
    private JCheckBoxMenuItem cellsItem;
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 500;
    
    PlanetTableFrame()
    {
        setTitle("TableRowColumnTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        TableModel model = new DefaultTableModel(cells, columnNames)
        {            
            @Override
            public Class<?> getColumnClass(int c)
            {
                return cells[0][c].getClass();
            }
        };

        table = new JTable(model);

        table.setRowHeight(100);
        table.getColumnModel().getColumn(COLOR_COLUMN).setMinWidth(250);
        table.getColumnModel().getColumn(IMAGE_COLUMN).setMinWidth(100);

        final TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        sorter.setComparator(COLOR_COLUMN, new Comparator<Color>()
        {
            @Override
            public int compare(Color c1, Color c2)
            {
                int d = c1.getBlue() - c2.getBlue();
                if (d != 0)
                {
                    return d;
                }
                d = c1.getGreen() - c2.getGreen();
                if (d != 0)
                {
                    return d;
                }
                return c1.getRed() - c2.getRed();
            }
        });
        sorter.setSortable(IMAGE_COLUMN, false);
        add(new JScrollPane(table), BorderLayout.CENTER);

        removedRowIndices = new HashSet<>();
        removedColumns = new ArrayList<>();

        final RowFilter<TableModel, Integer> filter = new RowFilter<TableModel, Integer>()
        {
            @Override
            public boolean include(Entry<? extends TableModel, ? extends Integer> entry)
            {
                return !removedRowIndices.contains(entry.getIdentifier());
            }
        };

        // Создание меню.

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu selectionMenu = new JMenu("Selection");
        menuBar.add(selectionMenu);

        rowsItem = new JCheckBoxMenuItem("Rows");
        columnsItem = new JCheckBoxMenuItem("Columns");
        cellsItem = new JCheckBoxMenuItem("Cells");

        rowsItem.setSelected(table.getRowSelectionAllowed());
        columnsItem.setSelected(table.getColumnSelectionAllowed());
        cellsItem.setSelected(table.getCellSelectionEnabled());

        rowsItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                table.clearSelection();
                table.setRowSelectionAllowed(rowsItem.isSelected());
                updateCheckboxMenuItems();
            }
        });
        selectionMenu.add(rowsItem);

        columnsItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                table.clearSelection();
                table.setColumnSelectionAllowed(columnsItem.isSelected());
                updateCheckboxMenuItems();
            }
        });
        selectionMenu.add(columnsItem);

        cellsItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                table.clearSelection();
                table.setCellSelectionEnabled(cellsItem.isSelected());
                updateCheckboxMenuItems();
            }
        });
        selectionMenu.add(cellsItem);

        JMenu tableMenu = new JMenu("Edit");
        menuBar.add(tableMenu);

        JMenuItem hideColumnsItem = new JMenuItem("Hide Columns");
        hideColumnsItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                int[] selected = table.getSelectedColumns();
                TableColumnModel columnModel = table.getColumnModel();

                // Удаление столбцов из веда, начиная с посдеднего
                // индекса так, чтобы не затрагивать номер столбца

                for (int i = selected.length - 1; i >= 0; i--)
                {
                    TableColumn column = columnModel.getColumn(selected[i]);
                    table.removeColumn(column);

                    // Сохранение удаленных столбцов для команды "show columns"

                    removedColumns.add(column);
                }
            }
        });
        tableMenu.add(hideColumnsItem);

        JMenuItem showColumnsItem = new JMenuItem("Show Columns");
        showColumnsItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                // Возвращение всех удаленных столбцов
                for (TableColumn tc : removedColumns)
                {
                    table.addColumn(tc);
                }
                removedColumns.clear();
            }
        });
        tableMenu.add(showColumnsItem);

        JMenuItem hideRowsItem = new JMenuItem("Hide Rows");
        hideRowsItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                int[] selected = table.getSelectedRows();
                for (int i : selected)
                {
                    removedRowIndices.add(table.convertRowIndexToModel(i));
                }
                sorter.setRowFilter(filter);
            }
        });
        tableMenu.add(hideRowsItem);

        JMenuItem showRowsItem = new JMenuItem("Show Rows");
        showRowsItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                removedRowIndices.clear();
                sorter.setRowFilter(filter);
            }
        });
        tableMenu.add(showRowsItem);

        JMenuItem printSelectionItem = new JMenuItem("Print Selection");
        printSelectionItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                int[] selected = table.getSelectedRows();
                System.out.println("Selected rows: " + Arrays.toString(selected));
                selected = table.getSelectedColumns();
                System.out.println("Selected columns: " + Arrays.toString(selected));
            }
        });
        tableMenu.add(printSelectionItem);
    }

    private void updateCheckboxMenuItems()
    {
        rowsItem.setSelected(table.getRowSelectionAllowed());
        columnsItem.setSelected(table.getColumnSelectionAllowed());
        cellsItem.setSelected(table.getCellSelectionEnabled());
    }
}

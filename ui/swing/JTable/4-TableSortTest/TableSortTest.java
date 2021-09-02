
/**
 * @version 1.01 2004-08-22
 * @author Cay Horstmann
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * ��� ��������� �������������, ��� ����������� ������� �������. ������ �������� ����� �� ���������
 * ���������������� ������� ������� ��� ����������.
 */
public class TableSortTest
{
    public static void main(String[] args)
    {
        JFrame frame = new TableSortFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

/**
 * ������ ����� �������� ������ � ��������.
 */
class TableSortFrame extends JFrame
{
    private Object[][] cells =
    {
        {
            "Mercury", 2440.0, 0, false, Color.yellow
        },
        {
            "Venus", 6052.0, 0, false, Color.yellow
        },
        {
            "Earth", 6378.0, 1, false, Color.blue
        },
        {
            "Mars", 3397.0, 2, false, Color.red
        },
        {
            "Jupiter", 71492.0, 16, true, Color.orange
        },
        {
            "Saturn", 60268.0, 18, true, Color.orange
        },
        {
            "Uranus", 25559.0, 17, true, Color.blue
        },
        {
            "Neptune", 24766.0, 8, true, Color.blue
        },
        {
            "Pluto", 1137.0, 1, false, Color.black
        }
    };
    private String[] columnNames =
    {
        "Planet", "Radius", "Moons", "Gaseous", "Color"
    };
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 200;

    TableSortFrame()
    {
        setTitle("TableSortTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // �������� ������ ������� � ������������ ��� ���

        DefaultTableModel model = new DefaultTableModel(cells, columnNames);
        final SortFilterModel sorter = new SortFilterModel(model);

        // ������� ��� �������

        final JTable table = new JTable(sorter);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ��������� ����������� �������� ������ �� ��������� �������

        table.getTableHeader().addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent event)
            {
                // �������� �������� ������.
                if (event.getClickCount() < 2)
                {
                    return;
                }

                // ������ ������ ������� �� ��������� ����.
                int tableColumn = table.columnAtPoint(event.getPoint());

                // ��������� � ������ ������ � �������������.
                int modelColumn = table.convertColumnIndexToModel(tableColumn);
                sorter.sort(modelColumn);
            }
        });
    }
}

/**
 * ������ ������ ������� ����� ��� ������������ ������ � ���������� ����� ������, ������� ���������
 * ������ ���, ��� ��� ������ ��� ����������� ������� ���� �������������.
 */
class SortFilterModel extends AbstractTableModel
{
    private TableModel model;
    private int sortColumn;
    private Row[] rows;

    /**
     * ��������������� ������� ���������� ��� ������.
     * <p/>
     * @param m ������ ������� ��� �����, ������� ����� �������������.
     */
    SortFilterModel(TableModel m)
    {
        model = m;
        rows = new Row[model.getRowCount()];
        for (int i = 0; i < rows.length; i++)
        {
            rows[i] = new Row();
            rows[i].index = i;
        }
    }

    /**
     * ���������� �����.
     * <p/>
     * @param c ����� �������, ������� ������ ���� ������������.
     */
    public void sort(int c)
    {
        sortColumn = c;
        Arrays.sort(rows);
        fireTableDataChanged();
    }

    // ���������� ������������ ������ ��� ���� �������, ������� ����� ������ � ������.
    @Override
    public Object getValueAt(int r, int c)
    {
        return model.getValueAt(rows[r].index, c);
    }

    @Override
    public boolean isCellEditable(int r, int c)
    {
        return model.isCellEditable(rows[r].index, c);
    }

    @Override
    public void setValueAt(Object aValue, int r, int c)
    {
        model.setValueAt(aValue, rows[r].index, c);
    }

    // ������������� ���� ��������� ������� ������.
    @Override
    public int getRowCount()
    {
        return model.getRowCount();
    }

    @Override
    public int getColumnCount()
    {
        return model.getColumnCount();
    }

    @Override
    public String getColumnName(int c)
    {
        return model.getColumnName(c);
    }

    @Override
    public Class<?> getColumnClass(int c)
    {
        return model.getColumnClass(c);
    }

    /**
     * ������ ���������� ����� �������� ������ ������ � ������. ������ ���� ����� ������������ ��
     * ������ ������� ��� ����������, ������� ������������� ������.
     */
    private class Row implements Comparable<Row>
    {
        public int index;

        @Override
        @SuppressWarnings("unchecked")
        public int compareTo(Row other)
        {
            Object a = model.getValueAt(index, sortColumn);
            Object b = model.getValueAt(other.index, sortColumn);
            if (a instanceof Comparable)
            {
                return ((Comparable) a).compareTo(b);
            }
            else
            {
                return a.toString().compareTo(b.toString());
            }
        }
    }
}

package pkg3.jtableandfxchart;

import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javax.swing.table.AbstractTableModel;

/**
 * Модель таблицы.
 *
 * @author Denis
 */
public class SampleTableModel extends AbstractTableModel
{
    private static ObservableList<BarChart.Series<?, ?>> bcData;
    private final String[] names =
    {
        "2007",
        "2008",
        "2009"
    };
    private Object[][] data =
    {
        {
            new Double(567),
            new Double(956),
            new Double(1154)
        },
        {
            new Double(1292),
            new Double(1665),
            new Double(1927)
        },
        {
            new Double(1292),
            new Double(2559),
            new Double(2774)
        }
    };

    public double getTickUnit()
    {
        return 1000;
    }

    public List<String> getColumnNames()
    {
        return Arrays.asList(names);
    }

    @Override
    public int getRowCount()
    {
        return data.length;
    }

    @Override
    public int getColumnCount()
    {
        return names.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        return names[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex)
    {
        if (value instanceof Double)
        {
            data[rowIndex][columnIndex] = (Double) value;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @SuppressWarnings("unchecked")
    public ObservableList<BarChart.Series<?, ?>> getBarChartData()
    {
        if (bcData == null)
        {
            bcData = FXCollections.<BarChart.Series<?, ?>>observableArrayList();
            for (int row = 0; row < getRowCount(); row++)
            {
                ObservableList<BarChart.Data<?, ?>> series =
                   FXCollections.<BarChart.Data<?, ?>>observableArrayList();
                for (int column = 0; column < getColumnCount(); column++)
                {
                    series.add(new BarChart.Data(getColumnName(column), getValueAt(row, column)));
                }
                bcData.add(new BarChart.Series(series));
            }
        }
        return bcData;
    }
}

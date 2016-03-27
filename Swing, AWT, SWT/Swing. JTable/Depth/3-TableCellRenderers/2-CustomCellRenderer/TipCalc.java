
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 * Объект отображения ячеек таблицы для денежных величин
 * <p/>
 * @author oracle_pr1
 */
class MoneyRenderer extends DefaultTableCellRenderer
{
    @Override
    public Component getTableCellRendererComponent(JTable tbl,
        Object val,
        boolean isSelected,
        boolean hasFocus,
        int row,
        int column)
    {
        JLabel rendComp = (JLabel) super.getTableCellRendererComponent(tbl, val, isSelected, hasFocus, row, column);
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        rendComp.setText(((Double) val).doubleValue() != 0 ? "$ " + nf.format(val) : "");
        return rendComp;
    }
}

/**
 * Модель таблицы для представления денежных величин
 * <p/>
 * @author oracle_pr1
 */
class MoneyInfoModel extends AbstractTableModel
{
    private int numRows;
    private String colNames[];
    final public static String[] DEFAULT_COLUMN_NAMES =
    {
        "Price", "10% Tip", "20% Tip", "30% Tip", "Suggested"
    };
    private double[] other;

    MoneyInfoModel(int numRows, String colNames[])
    {
        super();
        this.numRows = numRows;
        this.colNames = colNames;
        this.other = new double[numRows];
    }

    MoneyInfoModel(int numRows)
    {
        this(numRows, DEFAULT_COLUMN_NAMES);
    }

    @Override
    public int getRowCount()
    {
        return this.numRows;
    }

    @Override
    public int getColumnCount()
    {
        return this.colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        if (columnIndex == 0)
        {
            return new Double(rowIndex + 1);
        }
        else if (columnIndex >= 1 && columnIndex < 4)
        {
            return new Double((rowIndex + 1) * columnIndex * 0.1);
        }
        else
        {
            return new Double(other[rowIndex]);
        }
    }

    @Override
    public String getColumnName(int idx)
    {
        return colNames[idx];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return (columnIndex >= 0 && columnIndex <= colNames.length - 1) ? Double.class : Object.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return (columnIndex == colNames.length - 1) ? true : false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        if (isCellEditable(rowIndex, columnIndex))
        {
            other[rowIndex] = ((Double) aValue).doubleValue();
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }
}

/**
 * Поведение объектов отображения ячеек таблицы
 * <p/>
 * @author oracle_pr1
 */
public class TipCalc
{
    private JTable jtblMoneyInfo;
    private JLabel jlab;
    final public static String[] columnNames =
    {
        "Price", "10% Tip", "20% Tip", "30% Tip", "Suggested"
    };

    public TipCalc(int size)
    {
        JFrame jfrm = new JFrame("A Tip Calculator");
        jfrm.getContentPane().setLayout(new FlowLayout());
        jfrm.setSize(500, 300);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jlab = new JLabel();
        jtblMoneyInfo = new JTable(new MoneyInfoModel(size, TipCalc.columnNames));
        jtblMoneyInfo.setGridColor(Color.red);
        jtblMoneyInfo.setDefaultRenderer(Double.class, new MoneyRenderer());
        JScrollPane jscrlp = new JScrollPane(jtblMoneyInfo);
        jtblMoneyInfo.setPreferredScrollableViewportSize(new Dimension(400, 130));
        TableModel tm = jtblMoneyInfo.getModel();
        tm.addTableModelListener(new TableModelListener()
        {
            @Override
            public void tableChanged(TableModelEvent tme)
            {
                if (tme.getType() == TableModelEvent.UPDATE)
                {
                    jlab.setText("New suggested tip: "
                        + NumberFormat.getCurrencyInstance().format(
                        jtblMoneyInfo.getModel().getValueAt(tme.getFirstRow(), tme.getColumn())));
                }
            }
        });
        jfrm.getContentPane().add(jscrlp);
        jfrm.getContentPane().add(jlab);
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @SuppressWarnings("ResultOfObjectAllocationIgnored")
            @Override
            public void run()
            {
                new TipCalc(50);
            }
        });
    }
}
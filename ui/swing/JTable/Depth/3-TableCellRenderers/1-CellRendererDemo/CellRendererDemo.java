
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * ������� ������ �����������, �������������� �������� �������� double ��������
 * ������� ����� ���������� �����.
 * <p/>
 * @author oracle_pr1
 */
class MyRenderer extends DefaultTableCellRenderer
{
    @Override
    public Component getTableCellRendererComponent(JTable jtab,
        Object v,
        boolean selected,
        boolean focus,
        int r,
        int c)
    {
        // ��������� ���������� �� ���������.
        JLabel rendComp =
            (JLabel) super.getTableCellRendererComponent(jtab, v, selected, focus, r, c);

        // ��������� ������� ��������������.
        NumberFormat nf = NumberFormat.getNumberInstance();

        // ��������� ��� ����������� ������� ������ � ������� �����.
        nf.setMinimumFractionDigits(4);
        nf.setMaximumFractionDigits(4);

        // � �������� ������ ����� ��������������� ����������������� ��������.
        rendComp.setText(nf.format(v));

        // ����������� ����������������� ������� ���������������.
        return rendComp;
    }
}

/**
 * �������� ������ �������, ������� ������������ �������� ������
 * <p/>
 * @author oracle_pr1
 */
class NumInfoModel extends AbstractTableModel
{
    private int numRows;
    private String colNames[] =
    {
        "Value", "Prime", "Square", "Square Root"
    };

    NumInfoModel(int len)
    {
        super();
        numRows = len;
    }

    @Override
    public int getRowCount()
    {
        return numRows;
    }

    @Override
    public int getColumnCount()
    {
        return 4;
    }

    @Override
    public String getColumnName(int c)
    {
        return colNames[c];
    }

    @Override
    public Object getValueAt(int r, int c)
    {
        if (c == 0)
        {
            return new Integer(r + 2);
        }
        else if (c == 1)
        {
            return (isPrime(r + 2)) ? "Yes" : "No";
        }
        else if (c == 2)
        {
            return new Integer((r + 2) * (r + 2));
        }
        else
        {
            return new Double(Math.sqrt(r + 2));
        }
    }

    /**
     * ��������, �������� �� ����� �������
     * <p/>
     * @param v �����
     * <p/>
     * @return true, ���� ��� �������, false � ��������� ������
     */
    final static public boolean isPrime(int v)
    {
        int i;
        for (i = 2; i <= v / i; i++)
        {
            if ((v % i) == 0)
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public Class<?> getColumnClass(int c)
    {
        /*
         * ��� �������, ������������� ���������� ������, ���������� ������� ����� Double.
         * ��� ��������� ��������
         * ������������ ����� Object
         */
        return (c == 3) ? Double.class : Object.class;
    }
}

/**
 * ������� ���������������� ������� ����������� ����� �������.
 * @author oracle_pr1
 */
public class CellRendererDemo
{
    private JTable jtabNumInfo;

    public CellRendererDemo()
    {
        JFrame jfrm = new JFrame("Use a custom cell renderer");
        jfrm.getContentPane().setLayout(new FlowLayout());
        jfrm.setSize(500, 200);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jtabNumInfo = new JTable(new NumInfoModel(99));
        
        // ���������� ������� ��������������� ����������� �����
        jtabNumInfo.setDefaultRenderer(Double.class, new MyRenderer());
        JScrollPane jscrlp = new JScrollPane(jtabNumInfo);
        jtabNumInfo.setPreferredScrollableViewportSize(new Dimension(450, 110));
        jfrm.getContentPane().add(jscrlp);
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new CellRendererDemo();
            }
        });
    }
}
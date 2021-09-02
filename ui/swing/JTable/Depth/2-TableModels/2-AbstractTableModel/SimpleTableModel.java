// �������� ������� ������ ��� �������
import java.awt.HeadlessException;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class SimpleTableModel extends JFrame
{
    public SimpleTableModel() throws HeadlessException
    {
        super("Simple Table Model");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ������� ������� �� ������ ����� ������
        JTable table = new JTable(new SimpleModel());
        table.setRowHeight(32);
        getContentPane().add(new JScrollPane(table));
        
        // ������� ���� �� �����
        setSize(400, 300);
        setVisible(true);
    }

    // ���� ������
    private class SimpleModel extends AbstractTableModel
    {
        // ���������� �����
        @Override
        public int getRowCount()
        {
            return 100000;
        }

        // ���������� ��������
        @Override
        public int getColumnCount()
        {
            return 3;
        }

        // ������ � ������
        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            boolean flag = (rowIndex == 0) ? true : false;
            // ������ ������ ��� ������ ��������
            switch (columnIndex)
            {
                case 0:     return "" + rowIndex;
                case 1:     return flag;
                case 2:     return new ImageIcon("JTable.gif");
                default:    return "Empty";
            }
        }

        // ��� ������, �������� � �������
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
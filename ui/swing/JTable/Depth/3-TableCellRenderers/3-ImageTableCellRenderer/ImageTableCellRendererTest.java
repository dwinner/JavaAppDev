
import java.awt.Component;
import java.awt.HeadlessException;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * �����, �������� ������ � ����� ������
 * <p/>
 * @author dwinner@inbox.ru
 */
class ImageTableCell
{
    private Icon icon;
    private String text;

    ImageTableCell(Icon anIcon, String aText)
    {
        icon = anIcon;
        text = aText;
    }

    public Icon getIcon()
    {
        return icon;
    }

    public void setIcon(Icon anIcon)
    {
        icon = anIcon;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String aText)
    {
        text = aText;
    }
}

/**
 * ����� ��� ���������� ������ � ������
 * <p/>
 * @author dwinner@inbox.ru
 */
class ImageTableCellRenderer extends DefaultTableCellRenderer
{
    @Override
    public Component getTableCellRendererComponent(
        JTable table,
        Object value,
        boolean isSelected,
        boolean hasFocus,
        int row,
        int column)
    {
        // ����� ���������� ��������� ��� ����������. �������� ������ ������� ����
        if (value instanceof ImageTableCell)
        {
            ImageTableCell imageCell = (ImageTableCell) value;
            // �������� ����������� ������� �� �������� ������
            JLabel label = (JLabel) super.getTableCellRendererComponent(
                table,
                imageCell.getText(),
                isSelected,
                hasFocus,
                row,
                column);
            // ������������� ������ ��� JLabel
            label.setIcon(imageCell.getIcon());
            return label;
        }
        else
        {
            return super.getTableCellRendererComponent(
                table,
                value,
                isSelected,
                hasFocus,
                row,
                column);
        }
    }
}

/**
 * ����������� � ������� ������������ ������������� �������.
 * <p/>
 * @author dwinner@inbox.ru
 */
public class ImageTableCellRendererTest extends JFrame
{
    public ImageTableCellRendererTest() throws HeadlessException
    {
        super("Registering Table Renderer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ������� ������� �� ������ ����� ������
        JTable table = new JTable(new SpecialModel());
        
        // ������������ ������ ��� ���������� ����� ������
        table.setDefaultRenderer(ImageTableCell.class, new ImageTableCellRenderer());
        
        // ������� ���� �� �����
        getContentPane().add(new JScrollPane(table));
        pack();
        setVisible(true);
    }

    /**
     * ������ �������.
     */
    private class SpecialModel extends AbstractTableModel
    {
        // ������
        private Icon
            image1 = new ImageIcon("lion.gif"),
            image2 = new ImageIcon("scorpion.gif");
        // �������� ��������
        private String[] columnNames =
        {
            "Company",
            "Address"
        };
        // ������ �������
        private Object[][] data =
        {
            {
                new ImageTableCell(image1, "Mega Works"), "<html><font color=\"red\">Paris</font>"
            },
            {
                new ImageTableCell(image2, "Media Terra"), "<html><b>London</b>"
            }
        };

        // ���������� �����
        @Override
        public int getRowCount()
        {
            return data.length;
        }

        // ���������� ��������
        @Override
        public int getColumnCount()
        {
            return columnNames.length;
        }

        // �������� ��������
        @Override
        public String getColumnName(int column)
        {
            return columnNames[column];
        }

        // ��� ������ �������
        @Override
        public Class<?> getColumnClass(int columnIndex)
        {
            return data[0][columnIndex].getClass();
        }

        // �������� � ������
        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            return data[rowIndex][columnIndex];
        }
    }

    public static void main(String[] args)
    {
        new ImageTableCellRendererTest();
    }
}
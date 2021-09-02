/*
 * ��������� ��������� ������� JTableHeader.
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class UsingTableHeader extends JFrame
{
    // ������ ��� �������
    private String[][] data =
    {
        {
            "����", "+18 �"
        },
        {
            "����", "+22 �"
        },
        {
            "������", "+19 �"
        }
    };
    // �������� ��������
    private String[] columnNames =
    {
        "�����", "������� �����������"
    };

    public UsingTableHeader() throws HeadlessException
    {
        super("Using Table Header");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ������� �������
        JTable table = new JTable(data, columnNames);
        
        // ����������� ��������� �������
        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);
        
        // ������������ ������������ ������
        header.setDefaultRenderer(new HeaderRenderer());
        
        // ��������� ������� � ������ ���������
        getContentPane().add(new JScrollPane(table));
        setSize(400, 300);
        setVisible(true);
    }

    // ����������� ������������ ������ ��� ���������
    private static class HeaderRenderer extends DefaultTableCellRenderer
    {
        private final static Border border =
            BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.GRAY);

        // �����, ������������ ��������� ��� ����������
        @Override
        public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column)
        {
            // �������� ����������� ������� �� �������� ������
            JLabel label = (JLabel) super.getTableCellRendererComponent(
                table,
                value,
                isSelected,
                hasFocus,
                row,
                column);
            // ����������� ������ ����� � ���� ����
            label.setBackground(Color.BLACK);
            label.setForeground(Color.WHITE);
            label.setBorder(border);
            return label;
        }
    }

    public static void main(String[] args)
    {
        new UsingTableHeader();
    }
}
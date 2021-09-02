/*
 * ��������� ��������� �������� ���� ������.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SimpleTablesLook extends JFrame
{
    // ������ � ��������� ��� �������
    private Object[][] data = new String[][]
    {
        {
            "������", "�����", "����������"
        },
        {
            "�����������", "�������", "��������"
        }
    };
    private Object[] columns = new String[]
    {
        "������",
        "����",
        "������"
    };

    public SimpleTablesLook() throws HeadlessException
    {
        super("SimpleTablesLook");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ������� � ������� ����������� ����� ��������
        JTable table1 = new JTable(data, columns);
        
        // ��������� ���������� � ������
        table1.setRowHeight(40);
        table1.setIntercellSpacing(new Dimension(10, 10));
        table1.setGridColor(Color.green);
        table1.setShowVerticalLines(false);

        // ������� � ������� �������
        JTable table2 = new JTable(data, columns);
        table2.setForeground(Color.red);
        table2.setSelectionForeground(Color.yellow);
        table2.setSelectionBackground(Color.blue);
        table2.setShowGrid(false);

        // ��������� ������� � ������ �� ���� �����
        JPanel contents = new JPanel(new GridLayout(0, 2, 5, 5));
        contents.add(new JScrollPane(table1));
        contents.add(new JScrollPane(table2));
        
        // ������� ���� �� �����
        setContentPane(contents);
        setSize(600, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new SimpleTablesLook();
    }
}
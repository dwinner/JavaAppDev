// ������� �������, ����������� � ������� ������� �������������
import java.awt.HeadlessException;
import java.util.Vector;
import javax.swing.*;

public class SimpleTables extends JFrame
{
    // ������ ��� ������
    private Object[][] colors = new String[][]
    {
        {
            "�������", "�������", "�����"
        },
        {
            "������", "���������", "�����"
        }
    };
    // �������� ���������� ��������
    private Object[] colorsHeader = new String[]
    {
        "����", "��� ����", "���� ����"
    };

    public SimpleTables() throws HeadlessException
    {
        super("Simple tables");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ��������� ������� ������
        JTable table1 = new JTable(colors, colorsHeader);
        JTable table2 = new JTable(5, 5);
        // ������� �� ������ �������, ���������� �� ��������
        Vector data = new Vector();
        Vector row1 = new Vector();
        Vector row2 = new Vector();
        // ������ � �����������
        Vector columnNames = new Vector();
        // ���������� �������
        for (int i = 0; i < 3; i++)
        {
            row1.add("Cell 1 : " + i);
            row2.add("Cell 2 : " + i);
            columnNames.add("Column # " + i);
        }
        data.add(row1);
        data.add(row2);
        JTable table3 = new JTable(data, columnNames);
        // ��������� ������� � ������ � ������� �������������
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(table1));
        contents.add(new JScrollPane(table2));
        contents.add(table3);
        // ������� ���� �� �����
        setContentPane(contents);
        setSize(350, 400);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new SimpleTables();
    }
}
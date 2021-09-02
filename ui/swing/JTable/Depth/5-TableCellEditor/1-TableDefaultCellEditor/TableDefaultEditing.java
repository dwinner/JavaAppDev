/*
 * ���������� ������������ ��������� ��� ������
 */

import java.awt.HeadlessException;
import javax.swing.*;

public class TableDefaultEditing extends JFrame
{
    // �������� ��������
    private String[] columns =
    {
        "Name",
        "Favourite color"
    };
    // ������ ��� �������
    private String[][] data =
    {
        {
            "����", "�������"
        },
        {
            "���������", "�������"
        },
        {
            "����", "�����"
        }
    };

    public TableDefaultEditing() throws HeadlessException
    {
        super("Table Default editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� �������
        JTable table = new JTable(data, columns);
        // ����������� ����������� ��������
        JComboBox<String> combo = new JComboBox<>(
            new String[]
            {
                "Green",
                "Yellow",
                "White"
            });
        DefaultCellEditor editor = new DefaultCellEditor(combo);
        table.getColumnModel().getColumn(1).setCellEditor(editor);
        // ������� ���� �� �����
        getContentPane().add(new JScrollPane(table));
        setSize(200, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new TableDefaultEditing();
    }
}
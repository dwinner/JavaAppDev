// �������� ������� �������������� �������
import javax.swing.*;
import java.util.*;

public class SimpleComboBoxes extends JFrame
{    
    // ������ � ���������� ������
    public String[] elements = new String[]
    {
        "�����������",
        "�����",
        "������",
        "�������"
    };
    
    public SimpleComboBoxes()
    {
        super("Simple Combo Boxes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� ���� �������������� �������
        JComboBox combo1 = new JComboBox(elements);
        // ������ �������� �������� ������
        combo1.setPrototypeDisplayValue("������� �������");
        // ������ �������������� ������
        Vector data = new Vector();
        for (int i = 0; i < 10; i++)
        {
            data.add("Element #: " + i);
        }
        JComboBox combo2 = new JComboBox(data);
        // ������ ��� �������������
        combo2.setEditable(true);
        combo2.setMaximumRowCount(6);
        // ��������� ������ � ������ � ������� ���� �� �����
        JPanel contents = new JPanel();
        contents.add(combo1);
        contents.add(combo2);
        setContentPane(contents);
        setSize(100, 200);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new SimpleComboBoxes();
    }

}
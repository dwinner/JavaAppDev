// ������������� ������� JCheckBox
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Checkboxes extends JFrame
{    
    public Checkboxes()
    {
        super("Checkboxes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // �������� ������ �����������
        Container c = getContentPane();
        
        // ���������� ���������������� ������������
        c.setLayout(new FlowLayout());
        
        // ��������� ������
        JCheckBox ch1 = new JCheckBox("I love JFC", true);
        
        // ������ ��������� ������� � ����� ����������� ������
        JPanel panel = new JPanel(new GridLayout(0, 1, 0, 7));
        panel.setBorder(BorderFactory.createTitledBorder("���������"));
        String[] names =
        {
            "���� �����",
            "��� � ������",
            "����������"
        };
        JCheckBox check;
        for (int i = 0; i < names.length; i++)
        {
            check = new JCheckBox(names[i]);
            panel.add(check);
        }
        
        // ��������� ��� � ���������
        c.add(ch1);
        c.add(panel);
        
        // ������� ���� �� �����
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new Checkboxes();
    }

}
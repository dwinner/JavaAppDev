// ������������� ��������������
import javax.swing.*;
import java.awt.*;

public class RadioButtons extends JFrame
{    
    public RadioButtons()
    {
        super("Radio Buttons");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // �������� ������ �����������
        Container c = getContentPane();
        
        // ���������� ���������������� ������������
        c.setLayout(new FlowLayout());
        
        // ��������� �������������
        JRadioButton r1 = new JRadioButton("��� �� ����");
        
        // ������ ��������� �������������� � ����� ����������� ������
        JPanel panel = new JPanel(new GridLayout(0, 1, 0, 5));
        panel.setBorder(BorderFactory.createTitledBorder("������� ���"));
        ButtonGroup bg = new ButtonGroup();
        String[] names =
        {
            "������� ��� Java",
            "MS Windows",
            "CDE/Motif"
        };
        JRadioButton radio;
        for (int i = 0; i < names.length; i++)
        {
            radio = new JRadioButton(names[i]);
            panel.add(radio);
            bg.add(radio);
        }
        
        // ��������� ��� � ���������
        c.add(r1);
        c.add(panel);
        
        // ������� ���� �� �����
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new RadioButtons();
    }

}
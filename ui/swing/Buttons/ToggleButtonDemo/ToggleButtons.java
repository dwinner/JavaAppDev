// ������������� ������������ JToggleButton
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToggleButtons extends JFrame
{    
    private JToggleButton button1, button2;
    
    public ToggleButtons()
    {
        super("Toggle Buttons");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // �������� ������ �����������
        Container c = getContentPane();
        // ���������� ���������������� ������������
        c.setLayout(new FlowLayout());
        // ������� ���� ������������
        button1 = new JToggleButton("First", true);
        button2 = new JToggleButton("Second", false);
        // �������� ���������� ������� � ����� ���������
        button2.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent ie)
            {
                button1.setSelected(!button1.isSelected());
            }
        });
        c.add(button1);
        c.add(button2);
        // ������� ���� �� �����
        pack();
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ToggleButtons();
    }

}
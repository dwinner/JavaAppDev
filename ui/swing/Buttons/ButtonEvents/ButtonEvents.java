// ��������� ������� �� ������
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonEvents extends JFrame
{    
    private JTextArea info;
    private JButton button;
    
    public ButtonEvents()
    {
        super("Button Events");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // �������� ������ �����������
        Container c = getContentPane();
        
        // ������� ������ � �������� � �� ����� ����
        button = new JButton("������� �� ����!");
        c.add(button, BorderLayout.NORTH);
        
        // ���� ��� ������ ��������� � ��������
        info = new JTextArea("���� ������� �� ����\n");
        c.add(new JScrollPane(info));
        
        // ����������� � ����� ������ ���������� �������
        // ��������� ������� ��� ���������� ������
        button.addActionListener(new ActionL());
        button.addChangeListener(new ChangeL());
        
        // ������������ ��������� ����� �� �����
        button.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent ie)
            {
                info.append("��� ��������� �� ������� �� �������");
            }
        });
        
        // ������� ���� �� �����
        setSize(400, 300);
        setVisible(true);
    }
    
    private class ActionL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            info.append("�������� ��������� � ������� ������! �� - " + ae.getActionCommand() + "\n");
        }
    }
    
    private class ChangeL implements ChangeListener
    {
        public void stateChanged(ChangeEvent ce)
        {
            info.append("�������� ��������� � ����� ��������� ������!\n");
        }
    }
    
    public static void main(String[] args)
    {
        new ButtonEvents();
    }

}
// ������������ ������ ������-�������������
  
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
   
public class ToggleDemo
{
    private JLabel jlab;
    private JToggleButton jtbn;
  
    public ToggleDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Demonstrate a Toggle Button");  
  
        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout()); 
  
        // ��������� ��������� �������� ������.
        jfrm.setSize(290, 80);  
  
        // ���������� ��������� ��� �������� ���������� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
  
        // �������� �����, ������������ ������ ������.
        jlab = new JLabel("Button is off.");  
 
        // �������� ������-�������������.
        jtbn =  new JToggleButton("On/Off"); 
 
        // ��������� ����������� ������� �������� jtbn, ������������ � ���� ��������������
        // ����������� ������. ��� ��������� ������� ������-������������� ������������ ItemListener.
        jtbn.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent ie)
            {
                // ��� ����������� ��������� ������ ������������ ����� isSelected()
                jlab.setText("Button is " + (jtbn.isSelected() ? "on." : "off."));
            }
        });
  
        // ��������� ������-������������� � ����� � ������ ������ �����������.
        jfrm.getContentPane().add(jtbn);   
        jfrm.getContentPane().add(jlab);  
 
        // ����������� ������.
        jfrm.setVisible(true);
    }
 
    public static void main(String args[])
    {
        // �������� ������ � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new ToggleDemo();
            }
        });
    }
}
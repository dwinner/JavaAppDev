// ������������� �������� � ������� ������������
import javax.swing.*;
import java.awt.BorderLayout;

public class BoxStruts extends JFrame
{    
    public BoxStruts()
    {
        super("Box Struts");
        setSize(250, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ������ � ������������ ������� �������������
        Box p = Box.createVerticalBox();
        p.add(new JButton("First"));
        
        // �������� ������������ ��������
        p.add(Box.createVerticalStrut(5));
        
        // ����� ��������� � �������� ������� �������
        p.add(new JButton("Second"));
        p.add(Box.createVerticalStrut(5));
        p.add(new JButton("Third"));
        
        // ������ � �������������� ������� �������������
        Box p2 = Box.createHorizontalBox();
        
        // �������� ����� ������� � ����� ������������
        p2.add(Box.createHorizontalStrut(10));
        p2.add(new JButton("One"));
        
        // �������� �������������� ��������
        p2.add(Box.createHorizontalStrut(25));
        p2.add(new JButton("Two"));
        
        // ��������� ������ �� ����� � �� ����
        getContentPane().add(p, BorderLayout.NORTH);
        getContentPane().add(p2, BorderLayout.SOUTH);
        
        // ������� ���� �� �����
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new BoxStruts();
    }

}
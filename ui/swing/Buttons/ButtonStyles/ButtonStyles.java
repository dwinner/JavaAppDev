// ��������� �������� ���� ������ JButton � ������� �������, ������, ����� � �.�.
import javax.swing.*;
import java.awt.*;

public class ButtonStyles extends JFrame
{    
    public ButtonStyles()
    {
        super("Button Styles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ���������� ���������������� ������������
        Container c = getContentPane();
        c.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        // ����� ������� ������
        JButton button = new JButton("������� ������");
        c.add(button);
        
        // ������ �� �������� �� ��� ������ �����
        button = new JButton();
        button.setIcon(new ImageIcon("images.jpg"));
        button.setRolloverIcon(new ImageIcon("images2.jpg"));
        button.setPressedIcon(new ImageIcon("java.png"));
        button.setDisabledIcon(new ImageIcon("java-icon.png"));
        
        // ��� ����� ������ ����� ������ ��� �������� ����� � ��������
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        c.add(button);
        
        // ������ � ���������� ������ � HTML-�������
        button = new JButton("<html><h2><font color=\"yellow\">������� ������</font></h2>");
        button.setBackground(Color.green);
        c.add(button);
        
        // ��������� ������������ ������ � �����������
        button = new JButton("��������� ������������", new ImageIcon("chrome.png"));
        button.setMargin(new Insets(10, 10, 10, 10));
        button.setVerticalAlignment(SwingConstants.TOP);
        button.setHorizontalAlignment(SwingConstants.RIGHT);
        button.setHorizontalTextPosition(SwingConstants.LEFT);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setIconTextGap(10);
        
        // ������� ������ �������, ����� ������� ������������
        button.setPreferredSize(new Dimension(300, 100));
        c.add(button);
        
        // ����������� ������
        button = new JButton("���������");
        button.setEnabled(false);
        c.add(button);
        
        // ������� ���� �� �����
        setSize(400, 350);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new ButtonStyles();
    }

}
// ��������� ��������� ������� ������� ������� ���������
import javax.swing.*;
import java.awt.Color;

public class SimpleScrollPanes extends JFrame
{
    public SimpleScrollPanes()
    {
        super("Simple Scroll Panes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // �������
        JLabel label = new JLabel(new ImageIcon("painting.gif"));
        // ������ ����������� ������ ���������
        JScrollPane scrollPane = new JScrollPane(
                label/*,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS*/
        );
        // ��������� ��������
        scrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.BLUE));
        scrollPane.setWheelScrollingEnabled(false);
        // ������� ���� �� �����
        getContentPane().add(scrollPane);
        setSize(400, 300);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new SimpleScrollPanes();
    }

}
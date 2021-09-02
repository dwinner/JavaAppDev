// ����������� ������������ ������
import javax.swing.*;
import java.awt.*;

public class LayeredTest extends JFrame
{    
    public LayeredTest()
    {
        super("Layered Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // �������� ������������ ������ JLayeredPane
        JLayeredPane lp = getLayeredPane();
        
        // ��� ������
        Figure fg1 = new Figure(Color.RED, 0, "POPUP");
        Figure fg2 = new Figure(Color.BLUE, 0, "PALETTE1");
        Figure fg3 = new Figure(Color.GREEN, 1, "PALETTE2");
        
        // ������������ ����� � ����
        fg1.setBounds(0, 10, 120, 120);
        fg2.setBounds(60, 80, 160, 180);
        fg3.setBounds(90, 15, 250, 180);
        
        // ��������� � ��������� ����
        lp.add(fg1, JLayeredPane.POPUP_LAYER);
        lp.add(fg2, JLayeredPane.PALETTE_LAYER);
        lp.add(fg3, JLayeredPane.PALETTE_LAYER);
        
        // ����� ������� ����� �� �����
        lp.setPosition(fg3, 0);
        setSize(300, 200);
        setVisible(true);
    }
    
    // �����, ����������� �������� ��� ���� ����� � �������
    private static class Figure extends JComponent
    {
        private Color color;
        private int type;
        private String text;
        
        Figure(Color color, int type, String text)
        {
            this.color = color;
            this.type = type;
            this.text = text;
            setOpaque(false);
        }
        
        @Override public void paintComponent(Graphics g)
        {
            // ���������� ������
            g.setColor(color);
            switch (type)
            {
                case 0: g.fillOval(0, 0, 90, 90); break;
                case 1: g.fillRect(0, 0, 130, 80); break;
            }
            g.setColor(Color.white);
            g.drawString(text, 10, 35);
        }
    }
	
	public static void main(String[] args)
	{
	    new LayeredTest();
	}

}
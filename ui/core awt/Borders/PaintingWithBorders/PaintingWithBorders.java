// ��������� ���������� � ������ �����
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;

public class PaintingWithBorders extends JFrame
{    
    public PaintingWithBorders()
    {
        super("Painting with borders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ������� � ������ ���������� �����
        CustomComponent cc = new CustomComponent();
        cc.setBorder(BorderFactory.createTitledBorder("�����!"));
        
        // ������� ��������� � ����
        getContentPane().add(cc);
        setSize(400, 300);
        setVisible(true);
    }
    
    /**
     * ��������� � ����������� ���������� ����������
     */
    private class CustomComponent extends JComponent
    {
        @Override public void paintComponent(Graphics g)
        {
            // �������� ���������� �������������
            Rectangle rect = AbstractBorder.getInteriorRectangle(
                this,
                getBorder(),
                0,
                0,
                getWidth(),
                getHeight()
            );
            
            // ������ � ���
            g.setColor(Color.white);
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
    }
    
    public static void main(String[] args)
    {
        new PaintingWithBorders();
    }

}
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * @version 1.32 2007-04-14
 * @author Cay Horstmann
 */
public class DrawTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                DrawFrame frame = new DrawFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * �����, ���������� ������ ��� ���������.
 */
class DrawFrame extends JFrame
{
    public static final int DEFAULT_WIDTH  = 400;
    public static final int DEFAULT_HEIGHT = 400;
    
    public DrawFrame()
    {
        setTitle("DrawTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // ���������� ������ � ������.

        DrawComponent component = new DrawComponent();
        add(component);
    }
}

/**
 * ���������, ������������ �������������� � �������.
 */
class DrawComponent extends JComponent
{
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        // ��������� ��������������

        double leftX = 100;
        double topY = 100;
        double width = 200;
        double height = 150;

        Rectangle2D rect = new Rectangle2D.Double(leftX, topY, width, height);
        g2.draw(rect);

        // ��������� �������, ���������� � �������������.

        Ellipse2D ellipse = new Ellipse2D.Double();
        ellipse.setFrame(rect);
        g2.draw(ellipse);

        // ��������� ���������.

        g2.draw(new Line2D.Double(leftX, topY, leftX + width, topY + height));

        // ��������� ����������, ����� ������� ��������� � ������� ��������������.

        double centerX = rect.getCenterX();
        double centerY = rect.getCenterY();
        double radius = 150;

        Ellipse2D circle = new Ellipse2D.Double();
        circle.setFrameFromCenter(centerX, centerY, centerX + radius, centerY + radius);
        g2.draw(circle);
    }
}

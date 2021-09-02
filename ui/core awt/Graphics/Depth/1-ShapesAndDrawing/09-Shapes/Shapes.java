/*
 * Shapes ������������� ��������� ������ Java 2D.
 */

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Shapes extends JFrame
{
    // �����������
    public Shapes()
    {
        super("Drawing 2D shapes");
    }

    // ��������� ����� � ������� Java 2D API
    @Override
    public void paint(Graphics g)
    {
        // ����� ������ paint �����������
        super.paint(g);

        // ��������� ������� Graphics 2D
        Graphics2D graphics2D = (Graphics2D) g;

        // ��������� ������� � ����������� �����-������� ��������
        graphics2D.setPaint(new GradientPaint(5, 30, Color.blue, 35, 100, Color.yellow, true));
        graphics2D.fill(new Ellipse2D.Double(5, 30, 65, 100));

        // ��������� �������������� �������
        graphics2D.setPaint(Color.red);
        graphics2D.setStroke(new BasicStroke(10.0f));
        graphics2D.draw(new Rectangle2D.Double(80, 30, 65, 100));

        // ��������� �������������� �� ������������ ������ � �������������� BufferedImage
        BufferedImage bufferedImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.yellow);    // ��������� ������
        graphics.fillRect(0, 0, 10, 10);    // ����������� ������������ ��������������
        graphics.setColor(Color.black);     // ��������� ������
        graphics.drawRect(1, 1, 6, 6);      // ����������� ��������������
        graphics.setColor(Color.blue);      // ��������� �����
        graphics.fillRect(1, 1, 3, 3);      // ����������� ������������ ��������������
        graphics.setColor(Color.red);       // ��������� �������
        graphics.fillRect(4, 4, 3, 3);      // ����������� ������������ ��������������
        // ����������� burreredImage � ����������� ��������� ������ JFrame
        graphics2D.setPaint(new TexturePaint(bufferedImage, new Rectangle(10, 10)));
        graphics2D.fill(new RoundRectangle2D.Double(155, 30, 75, 100, 50, 50));

        // ��������� ������� ���� �����
        graphics2D.setPaint(Color.white);
        graphics2D.setStroke(new BasicStroke(6.0f));
        graphics2D.draw(new Arc2D.Double(240, 30, 75, 100, 0, 270, Arc2D.PIE));

        // ��������� ����� ������� � ������
        graphics2D.setPaint(Color.green);
        graphics2D.draw(new Line2D.Double(395, 30, 320, 150));

        float dashes[] =
        {
            10, 2
        };

        graphics2D.setPaint(Color.yellow);
        graphics2D.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashes, 0));
        graphics2D.draw(new Line2D.Double(320, 30, 395, 150));
    }

    public static void main(String[] args)
    {
        Shapes shapesApp = new Shapes();
        shapesApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        shapesApp.setSize(425, 160);
        shapesApp.setVisible(true);
    }
}
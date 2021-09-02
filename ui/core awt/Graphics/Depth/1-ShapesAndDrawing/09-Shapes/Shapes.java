/*
 * Shapes демонстрирует некоторые фигуры Java 2D.
 */

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Shapes extends JFrame
{
    // Конструктор
    public Shapes()
    {
        super("Drawing 2D shapes");
    }

    // Рисование фигур с помощью Java 2D API
    @Override
    public void paint(Graphics g)
    {
        // Вызов метода paint суперкласса
        super.paint(g);

        // Получение средств Graphics 2D
        Graphics2D graphics2D = (Graphics2D) g;

        // Рисование эллипса с градиентной желто-голубой заливкой
        graphics2D.setPaint(new GradientPaint(5, 30, Color.blue, 35, 100, Color.yellow, true));
        graphics2D.fill(new Ellipse2D.Double(5, 30, 65, 100));

        // Рисование прямоугольника красным
        graphics2D.setPaint(Color.red);
        graphics2D.setStroke(new BasicStroke(10.0f));
        graphics2D.draw(new Rectangle2D.Double(80, 30, 65, 100));

        // Рисование прямоугольника со скругленными углами с использованием BufferedImage
        BufferedImage bufferedImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setColor(Color.yellow);    // Рисование желтым
        graphics.fillRect(0, 0, 10, 10);    // Отображение закрашенного прямоугольника
        graphics.setColor(Color.black);     // Рисование черным
        graphics.drawRect(1, 1, 6, 6);      // Отображение прямоугольника
        graphics.setColor(Color.blue);      // Рисование синим
        graphics.fillRect(1, 1, 3, 3);      // Отображение закрашенного прямоугольника
        graphics.setColor(Color.red);       // Рисование красным
        graphics.fillRect(4, 4, 3, 3);      // Отображение закрашенного прямоугольника
        // Отображение burreredImage в графическом контексте панели JFrame
        graphics2D.setPaint(new TexturePaint(bufferedImage, new Rectangle(10, 10)));
        graphics2D.fill(new RoundRectangle2D.Double(155, 30, 75, 100, 50, 50));

        // Рисование сектора дуги белым
        graphics2D.setPaint(Color.white);
        graphics2D.setStroke(new BasicStroke(6.0f));
        graphics2D.draw(new Arc2D.Double(240, 30, 75, 100, 0, 270, Arc2D.PIE));

        // Рисование линий зеленым и желтым
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
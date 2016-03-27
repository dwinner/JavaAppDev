/*
 * Рисование произвольной траектории.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import javax.swing.JFrame;

public class Shapes2 extends JFrame
{
    // Задание цвета фона и строки заголовка окна
    public Shapes2()
    {
        super("Drawing 2D Shapes");
        getContentPane().setBackground(Color.gray);
    }

    // Рисование произвольных траекторий
    @Override
    public void paint(Graphics g)
    {
        // Вызов метода paint суперкласса
        super.paint(g);

        int[] xPoints =
        {
            55, 67, 109, 73, 83, 55, 27, 37, 1, 43
        };
        int[] yPoints =
        {
            0, 36, 36, 54, 96, 72, 96, 54, 36, 36
        };

        Graphics2D graphics2D = (Graphics2D) g;

        // Создание звезды из набора точек
        GeneralPath star = new GeneralPath();

        // Установка начальной точки траектории
        star.moveTo(xPoints[0], yPoints[0]);

        // Создание звезды - звезда при этом не отображается
        for (int count = 1; count < xPoints.length; count++)
        {
            star.lineTo(xPoints[count], yPoints[count]);
        }

        // Замыкание фигуры
        star.closePath();

        // Смещение начала в (200, 200)
        graphics2D.translate(200, 200);

        // Вращение вокруг начала и рисование звезд случайными цветами
        for (int count = 1; count <= 20; count++)
        {
            graphics2D.rotate(Math.PI / 10.0);

            // Установка случайного цвета для рисования
            graphics2D.setColor(new Color(
                (int) (Math.random() * 256),
                (int) (Math.random() * 256),
                (int) (Math.random() * 256)));

            // Отображение закрашенной звезды
            graphics2D.fill(star);
        }
    }

    public static void main(String[] args)
    {
        Shapes2 app = new Shapes2();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(400, 400);
        app.setVisible(true);
    }
}
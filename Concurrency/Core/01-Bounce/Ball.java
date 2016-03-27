import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Мяч, перемещающийся и отскакивающий от границ прямоугольника.
 * @version 1.33 2007-05-07
 * @author Cay Horstmann
 */
public class Ball
{
    private static final int XSIZE = 15;
    private static final int YSIZE = 15;
    private double x = 0;
    private double y = 0;
    private double dx = 1;
    private double dy = 1;
    
    /**
     * Перемещает мяч в следующую позицию, меняя направление
     * при столкновении с краем поля.
     * @param bounds ограничивающий прямоугольник
     */
    public void move(Rectangle2D bounds)
    {
        x += dx;
        y += dy;
        if (x < bounds.getMinX())
        {
            x = bounds.getMinX();
            dx = -dx;
        }
        if (x + XSIZE >= bounds.getMaxX())
        {
            x = bounds.getMaxX() - XSIZE;
            dx = -dx;
        }
        if (y < bounds.getMinY())
        {
            y = bounds.getMinY();
            dy = -dy;
        }
        if (y + YSIZE >= bounds.getMaxY())
        {
            y = bounds.getMaxY() - YSIZE;
            dy = -dy;
        }
    }
    
    /**
     * Получает форму мяча в текущей позиции.
     * @return Форма эллипса
     */
    public Ellipse2D getShape()
    {
        // FIXME: Возможно не создавать лишних объектов
        return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
    }
}

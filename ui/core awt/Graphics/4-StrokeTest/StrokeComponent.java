import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;

/**
 * Компонент, в котором рисуются две соединенные линии с использованием разных объектов штрихов и
 * который позволяет пользователю перетаскивать три определяющих расположение линий точки.
 */
public class StrokeComponent extends JComponent
{
    private Point2D[] points;
    private static int SIZE = 10;
    private int current;
    private float width;
    private int cap;
    private int join;
    private boolean dash;

    public StrokeComponent()
    {
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent mouseEvent)
            {
                Point p = mouseEvent.getPoint();
                for (int i = 0; i < points.length; i++)
                {
                    double x = points[i].getX() - SIZE / 2;
                    double y = points[i].getY() - SIZE / 2;
                    Rectangle2D r = new Rectangle2D.Double(x, y, SIZE, SIZE);
                    if (r.contains(p))
                    {
                        current = i;
                        return;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent)
            {
                current = -1;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseDragged(MouseEvent mouseEvent)
            {
                if (current == -1)
                {
                    return;
                }
                points[current] = mouseEvent.getPoint();
                repaint();
            }
        });

        points = new Point2D[3];
        points[0] = new Point2D.Double(200, 100);
        points[1] = new Point2D.Double(100, 200);
        points[2] = new Point2D.Double(200, 200);
        current = -1;
        width = 8.0F;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        GeneralPath path = new GeneralPath();
        path.moveTo((float) points[0].getX(), (float) points[0].getY());
        for (int i = 1; i < points.length; i++)
        {
            path.lineTo((float) points[i].getX(), (float) points[i].getY());
        }
        BasicStroke stroke;
        if (dash)
        {
            float miterLimit = 10.0F;
            float[] dashPattern =
            {
                10F, 10F, 10F, 10F, 10F, 10F, 30F, 10F, 30F,
                10F, 30F, 10F, 10F, 10F, 10F, 10F, 10F, 30F
            };
            float dashPhase = 0;
            stroke = new BasicStroke(width, cap, join, miterLimit, dashPattern, dashPhase);
        }
        else
        {
            stroke = new BasicStroke(width, cap, join);
        }
        g2.setStroke(stroke);
        g2.draw(path);
    }

    /**
     * Установка стиля соединения линий.
     * <p/>
     * @param j Стиль соединения линий.
     */
    public void setJoin(int j)
    {
        join = j;
        repaint();
    }

    /**
     * Установка стиля окончания линий.
     * <p/>
     * @param c Стиль окончания линии.
     */
    public void setCap(int c)
    {
        cap = c;
        repaint();
    }

    /**
     * Переключение между сплошной и пунктирной линией
     * <p/>
     * @param d false для сплошной, true для пунктирной линии
     */
    public void setDash(boolean d)
    {
        dash = d;
        repaint();
    }
}

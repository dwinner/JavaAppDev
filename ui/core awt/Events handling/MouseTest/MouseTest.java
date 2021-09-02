
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Обработка событий мыши.
 * @version 1.32 2007-06-12
 * @author Cay Horstmann
 */
public class MouseTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                MouseFrame frame = new MouseFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }

        });
    }

}

/**
 * Фрейм, содержащий панель, предназначенную для проверки операций с мышью.
 */
class MouseFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    MouseFrame()
    {
        setTitle("MouseTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Добавление компонентов к фрейму.

        MouseComponent component = new MouseComponent();
        add(component);
    }

}

/**
 * Компонент, поддерживающий добавление и удаление квадратов с помощью мыши.
 */
class MouseComponent extends JComponent
{
    private static final int SIDELENGTH = 10;
    private ArrayList<Rectangle2D> squares;
    private Rectangle2D current;

    MouseComponent()
    {
        squares = new ArrayList<>();
        current = null;

        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        // Рисование всех квадратов.
        for (Rectangle2D r : squares)
        {
            g2.draw(r);
        }
    }

    /**
     * Нахождение первого квадрата, который содержит заданную точку.
     * <p/>
     * @param p Точка
     * <p/>
     * @return Первый квадрат, содержащий точку p
     */
    public Rectangle2D find(Point2D p)
    {
        for (Rectangle2D r : squares)
        {
            if (r.contains(p))
            {
                return r;
            }
        }
        return null;
    }

    /**
     * Добавление квадрата к набору.
     * <p/>
     * @param p Центр квадрата
     */
    public void add(Point2D p)
    {
        double x = p.getX();
        double y = p.getY();

        current = new Rectangle2D.Double(x - SIDELENGTH / 2,
           y - SIDELENGTH / 2,
           SIDELENGTH,
           SIDELENGTH);
        squares.add(current);
        repaint();
    }

    /**
     * Удаление квадрата из набора.
     * <p/>
     * @param s the square to remove
     */
    public void remove(Rectangle2D s)
    {
        if (s == null)
        {
            return;
        }
        if (s == current)
        {
            current = null;
        }
        squares.remove(s);
        repaint();
    }

    // Квадрат, в пределах которого находится курсор.
    private class MouseHandler extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent event)
        {
            // Добавить новый квадрат, если курсор находится за пределами всех квадратов
            current = find(event.getPoint());
            if (current == null)
            {
                add(event.getPoint());
            }
        }

        @Override
        public void mouseClicked(MouseEvent event)
        {
            // После двойного щелчка текущий квадрат удаляется
            current = find(event.getPoint());
            if (current != null && event.getClickCount() >= 2)
            {
                remove(current);
            }
        }

    }

    private class MouseMotionHandler implements MouseMotionListener
    {
        @Override
        public void mouseMoved(MouseEvent event)
        {
            // Если курсор находится в пределах квадрата, он принимает крестообразный вид.
            setCursor(find(event.getPoint()) == null ? Cursor.getDefaultCursor()
               : Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }

        @Override
        public void mouseDragged(MouseEvent event)
        {
            if (current != null)
            {
                int x = event.getX();
                int y = event.getY();

                // Перетаскивание текущего квадрата в точку (x, y)
                current.setFrame(x - SIDELENGTH / 2, y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
                repaint();
            }
        }

    }

}

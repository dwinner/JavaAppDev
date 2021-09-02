
/**
 * @version 1.31 2004-05-04
 * @author Cay Horstmann
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Sketch
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                SketchFrame frame = new SketchFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм с панелью для рисования фигур.
 */
class SketchFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    SketchFrame()
    {
        setTitle("Sketch");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Добавление панели к фрейму.

        SketchPanel panel = new SketchPanel();
        add(panel);
    }
}

/**
 * Панель для рисования с помощью клавиатуры.
 */
class SketchPanel extends JPanel
{
    private Point2D last;
    private ArrayList<Line2D> lines;
    private static final int SMALL_INCREMENT = 1;
    private static final int LARGE_INCREMENT = 5;

    SketchPanel()
    {
        last = new Point2D.Double(100, 100);
        lines = new ArrayList<>();
        KeyHandler listener = new KeyHandler();
        addKeyListener(listener);
        setFocusable(true);
    }

    /**
     * Добавление нового отрезка к рисунку.
     * <p/>
     * @param dx Перемещение в направлении x
     * @param dy Перемещение в направлении y
     */
    public void add(int dx, int dy)
    {
        // Вычисление конца нового отрезка.
        Point2D end = new Point2D.Double(last.getX() + dx, last.getY() + dy);

        // Добавление нового отрезка.
        Line2D line = new Line2D.Double(last, end);
        lines.add(line);
        repaint();

        // Запоминается очередная конечная точка.
        last = end;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Рисование всех линий
        for (Line2D l : lines)
        {
            g2.draw(l);
        }
    }

    private class KeyHandler implements KeyListener
    {
        @Override
        public void keyPressed(KeyEvent event)
        {
            int keyCode = event.getKeyCode();

            // Установка расстояния.
            int d = (event.isShiftDown()) ? LARGE_INCREMENT : SMALL_INCREMENT;

            // Добавление нового отрезка.
            if (keyCode == KeyEvent.VK_LEFT)
            {
                add(-d, 0);
            }
            else if (keyCode == KeyEvent.VK_RIGHT)
            {
                add(d, 0);
            }
            else if (keyCode == KeyEvent.VK_UP)
            {
                add(0, -d);
            }
            else if (keyCode == KeyEvent.VK_DOWN)
            {
                add(0, d);
            }
        }

        @Override
        public void keyReleased(KeyEvent event)
        {
        }

        @Override
        public void keyTyped(KeyEvent event)
        {
            char keyChar = event.getKeyChar();

            // Установка расстояния.
            int d;
            if (Character.isUpperCase(keyChar))
            {
                d = LARGE_INCREMENT;
                keyChar = Character.toLowerCase(keyChar);
            }
            else
            {
                d = SMALL_INCREMENT;
            }

            // Добавление нового отрезка
            if (keyChar == 'h')
            {
                add(-d, 0);
            }
            else if (keyChar == 'l')
            {
                add(d, 0);
            }
            else if (keyChar == 'k')
            {
                add(0, -d);
            }
            else if (keyChar == 'j')
            {
                add(0, d);
            }
        }
    }
}
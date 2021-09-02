
/**
 * Параметры штриха.
 * <p/>
 * @version 1.02 2004-08-24
 * @author Cay Horstmann
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Пример указания параметров штриха.
 */
public class StrokeTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new StrokeTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм с набором переключателей для выбора стиля окончания штриха, стиля пересечения штрихов и типа линии.
 */
class StrokeTestFrame extends JFrame
{
    private StrokePanel canvas;
    private JPanel buttonPanel;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;

    StrokeTestFrame()
    {
        setTitle("StrokeTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        canvas = new StrokePanel();
        add(canvas, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));
        add(buttonPanel, BorderLayout.NORTH);

        ButtonGroup group1 = new ButtonGroup();
        makeCapButton("Butt Cap", BasicStroke.CAP_BUTT, group1);
        makeCapButton("Round Cap", BasicStroke.CAP_ROUND, group1);
        makeCapButton("Square Cap", BasicStroke.CAP_SQUARE, group1);

        ButtonGroup group2 = new ButtonGroup();
        makeJoinButton("Miter Join", BasicStroke.JOIN_MITER, group2);
        makeJoinButton("Bevel Join", BasicStroke.JOIN_BEVEL, group2);
        makeJoinButton("Round Join", BasicStroke.JOIN_ROUND, group2);

        ButtonGroup group3 = new ButtonGroup();
        makeDashButton("Solid Line", false, group3);
        makeDashButton("Dashed Line", true, group3);
    }

    /**
     * Создание переключателей для выбора стиля окончания штриха.
     * <p/>
     * @param label Надпись рядом с переключателем
     * @param style Стиль окончания штриха
     * @param group Группа переключателей
     */
    private void makeCapButton(String label, final int style, ButtonGroup group)
    {
        // Выбор первого переключателя в группе.
        boolean selected = group.getButtonCount() == 0;
        JRadioButton button = new JRadioButton(label, selected);
        buttonPanel.add(button);
        group.add(button);
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                canvas.setCap(style);
            }
        });
    }

    /**
     * Создание переключателей для выбора стиля соединения линий.
     * <p/>
     * @param label Надпись рядом с переключателем
     * @param style Стиль соединения линий
     * @param group Группа переключателей
     */
    private void makeJoinButton(String label, final int style, ButtonGroup group)
    {
        // Выбор первого переключателя в группе.
        boolean selected = group.getButtonCount() == 0;
        JRadioButton button = new JRadioButton(label, selected);
        buttonPanel.add(button);
        group.add(button);
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                canvas.setJoin(style);
            }
        });
    }

    /**
     * Создание переключателей для выбора сплошной или пунктирной линии.
     * <p/>
     * @param label Надпись рядом с переключателем
     * @param style false для сплошной линии, true для пунктирной
     * @param group Группа переключателей
     */
    private void makeDashButton(String label, final boolean style, ButtonGroup group)
    {
        // Выбор первого переключателя в группе.
        boolean selected = group.getButtonCount() == 0;
        JRadioButton button = new JRadioButton(label, selected);
        buttonPanel.add(button);
        group.add(button);
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                canvas.setDash(style);
            }
        });
    }
}

/**
 * Панель, в которой отображаются две соединенные линии; при этом используются разные объекты штрихов. Предусмотрено
 * перетаскивание трех точек, определяющих расположение линий.
 */
class StrokePanel extends JPanel
{
    private Point2D[] points;
    private static int SIZE = 10;
    private int current;
    private float width;
    private int cap;
    private int join;
    private boolean dash;

    StrokePanel()
    {
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent event)
            {
                Point p = event.getPoint();
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
            public void mouseReleased(MouseEvent event)
            {
                current = -1;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseDragged(MouseEvent event)
            {
                if (current == -1)
                {
                    return;
                }
                points[current] = event.getPoint();
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
        super.paintComponent(g);
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
                10F, 10F, 10F, 10F, 10F, 10F,
                30F, 10F, 30F, 10F, 30F, 10F,
                10F, 10F, 10F, 10F, 10F, 30F
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
     * @param j Стиль соединения линий
     */
    public void setJoin(int j)
    {
        join = j;
        repaint();
    }

    /**
     * Установка стиля окончания линии.
     * <p/>
     * @param c Стиль окончания линии
     */
    public void setCap(int c)
    {
        cap = c;
        repaint();
    }

    /**
     * Переключение между сплошной и пунктирной линией.
     * <p/>
     * @param d false для сплошной, true для пунктирной линии
     */
    public void setDash(boolean d)
    {
        dash = d;
        repaint();
    }
}

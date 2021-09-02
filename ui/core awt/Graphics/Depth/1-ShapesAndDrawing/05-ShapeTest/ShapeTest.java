
/**
 * ��������� ���������� �����.
 * <p/>
 * @version 1.01 2004-08-24
 * @author Cay Horstmann
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Random;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * ������ ��������� ��������� ���������� �����.
 */
public class ShapeTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new ShapeTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * ���� ����� �������� �������������� ������ ��� ������ ���� ������.
 */
class ShapeTestFrame extends JFrame
{
    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 480;

    ShapeTestFrame()
    {
        setTitle("ShapeTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        final ShapePanel panel = new ShapePanel();
        add(panel, BorderLayout.CENTER);
        final JComboBox<ShapeMaker> comboBox = new JComboBox<>();
        comboBox.addItem(new LineMaker());
        comboBox.addItem(new RectangleMaker());
        comboBox.addItem(new RoundRectangleMaker());
        comboBox.addItem(new EllipseMaker());
        comboBox.addItem(new ArcMaker());
        comboBox.addItem(new PolygonMaker());
        comboBox.addItem(new QuadCurveMaker());
        comboBox.addItem(new CubicCurveMaker());
        comboBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                ShapeMaker shapeMaker = (ShapeMaker) comboBox.getSelectedItem();
                panel.setShapeMaker(shapeMaker);
            }
        });
        add(comboBox, BorderLayout.NORTH);
        panel.setShapeMaker(comboBox.getItemAt(0));
    }
}

/**
 * � ���� ������ �������� ������ � ������������ �������������� �� ����������� �����.
 */
class ShapePanel extends JPanel
{
    private Point2D[] points;
    private static Random generator = new Random();
    private static int SIZE = 10;
    private int current;
    private ShapeMaker shapeMaker;

    ShapePanel()
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
        current = -1;
    }

    /**
     * ��������� �������, ��������������� ������, � ������������� ��� ��� ������� ����� � ������������� ������������.
     * <p/>
     * @param aShapeMaker ������, ������������ ������ �� ������ ������ �����
     */
    public void setShapeMaker(ShapeMaker aShapeMaker)
    {
        shapeMaker = aShapeMaker;
        int n = shapeMaker.getPointCount();
        points = new Point2D[n];
        for (int i = 0; i < n; i++)
        {
            double x = generator.nextDouble() * getWidth();
            double y = generator.nextDouble() * getHeight();
            points[i] = new Point2D.Double(x, y);
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (points == null)
        {
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < points.length; i++)
        {
            double x = points[i].getX() - SIZE / 2;
            double y = points[i].getY() - SIZE / 2;
            g2.fill(new Rectangle2D.Double(x, y, SIZE, SIZE));
        }

        g2.draw(shapeMaker.makeShape(points));
    }
}

/**
 * ���������� �������, ������������ ������. ����� makeShape() ���������� ���������� ������.
 */
abstract class ShapeMaker
{
    private int pointCount;

    /**
     * ����������� ������ ������.
     * <p/>
     * @param aPointCount ����� �����, ����������� ��� ����������� ������
     */
    ShapeMaker(int aPointCount)
    {
        pointCount = aPointCount;
    }

    /**
     * ������ ����� ���������� ����� �����, ����������� ��� ����������� ������.
     * <p/>
     * @return ����� �����
     */
    public int getPointCount()
    {
        return pointCount;
    }

    /**
     * ������� ������ �� ������ ��������� ������ �����.
     * <p/>
     * @param p �����, ������������ ������
     * <p/>
     * @return ������, ������������ ����������� �����
     */
    public abstract Shape makeShape(Point2D[] p);

    @Override
    public String toString()
    {
        return getClass().getName();
    }
}

/**
 * �������� �����, ����������� ��� �����.
 */
class LineMaker extends ShapeMaker
{
    LineMaker()
    {
        super(2);
    }

    @Override
    public Shape makeShape(Point2D[] p)
    {
        return new Line2D.Double(p[0], p[1]);
    }
}

/**
 * �������� ��������������, ������������� �������� �������.
 */
class RectangleMaker extends ShapeMaker
{
    RectangleMaker()
    {
        super(2);
    }

    @Override
    public Shape makeShape(Point2D[] p)
    {
        Rectangle2D s = new Rectangle2D.Double();
        s.setFrameFromDiagonal(p[0], p[1]);
        return s;
    }
}

/**
 * �������� �������������� �� ������������ ������ �� ������ ���� ������� �����.
 */
class RoundRectangleMaker extends ShapeMaker
{
    RoundRectangleMaker()
    {
        super(2);
    }

    @Override
    public Shape makeShape(Point2D[] p)
    {
        RoundRectangle2D s = new RoundRectangle2D.Double(0, 0, 0, 0, 20, 20);
        s.setFrameFromDiagonal(p[0], p[1]);
        return s;
    }
}

/**
 * �������� �������, �������������� ������������� �������� �������� ����������� ���� ������� �����.
 */
class EllipseMaker extends ShapeMaker
{
    EllipseMaker()
    {
        super(2);
    }

    @Override
    public Shape makeShape(Point2D[] p)
    {
        Ellipse2D s = new Ellipse2D.Double();
        s.setFrameFromDiagonal(p[0], p[1]);
        return s;
    }
}

/**
 * �������� ����, �������������� ������������� ������� �������� �� ��������� ���� ������� �����. ��������� ���� � ����
 * ���� �������� � ������� ������, ����������� ����� ����� ��������������� �������������� � ��������������� ����������
 * �����. ��� ������������ ������������ �������� ���������� ���� ������������ �������������� ������������� � �����,
 * ������������ ����.
 */
class ArcMaker extends ShapeMaker
{
    ArcMaker()
    {
        super(4);
    }

    @Override
    public Shape makeShape(Point2D[] p)
    {
        double centerX = (p[0].getX() + p[1].getX()) / 2;
        double centerY = (p[0].getY() + p[1].getY()) / 2;
        double width = Math.abs(p[1].getX() - p[0].getX());
        double height = Math.abs(p[1].getY() - p[0].getY());

        double distortedStartAngle = Math.toDegrees(Math.atan2(-(p[2].getY() - centerY)
            * width, (p[2].getX() - centerX) * height));
        double distortedEndAngle = Math.toDegrees(Math.atan2(-(p[3].getY() - centerY)
            * width, (p[3].getX() - centerX) * height));
        double distortedAngleDifference = distortedEndAngle - distortedStartAngle;
        if (distortedStartAngle < 0)
        {
            distortedStartAngle += 360;
        }
        if (distortedAngleDifference < 0)
        {
            distortedAngleDifference += 360;
        }

        Arc2D s = new Arc2D.Double(0, 0, 0, 0, distortedStartAngle, distortedAngleDifference, Arc2D.OPEN);
        s.setFrameFromDiagonal(p[0], p[1]);

        GeneralPath g = new GeneralPath();
        g.append(s, false);
        Rectangle2D r = new Rectangle2D.Double();
        r.setFrameFromDiagonal(p[0], p[1]);
        g.append(r, false);
        Point2D center = new Point2D.Double(centerX, centerY);
        g.append(new Line2D.Double(center, p[2]), false);
        g.append(new Line2D.Double(center, p[3]), false);
        return g;
    }
}

/**
 * �������� ��������������, ������������� ������ �������� �������.
 */
class PolygonMaker extends ShapeMaker
{
    PolygonMaker()
    {
        super(6);
    }

    @Override
    public Shape makeShape(Point2D[] p)
    {
        GeneralPath s = new GeneralPath();
        s.moveTo((float) p[0].getX(), (float) p[0].getY());
        for (int i = 1; i < p.length; i++)
        {
            s.lineTo((float) p[i].getX(), (float) p[i].getY());
        }
        s.closePath();
        return s;
    }
}

/**
 * �������� ������ ������� �������, ������������ ����� ��������� ������� � ����������� ������.
 */
class QuadCurveMaker extends ShapeMaker
{
    QuadCurveMaker()
    {
        super(3);
    }

    @Override
    public Shape makeShape(Point2D[] p)
    {
        return new QuadCurve2D.Double(
            p[0].getX(), p[0].getY(),
            p[1].getX(), p[1].getY(),
            p[2].getX(), p[2].getY());
    }
}

/**
 * �������� ������ �������� �������, ������������ ����� ��������� � ����� ������������ �������.
 */
class CubicCurveMaker extends ShapeMaker
{
    CubicCurveMaker()
    {
        super(4);
    }

    @Override
    public Shape makeShape(Point2D[] p)
    {
        return new CubicCurve2D.Double(
            p[0].getX(), p[0].getY(),
            p[1].getX(), p[1].getY(),
            p[2].getX(), p[2].getY(),
            p[3].getX(), p[3].getY());
    }
}

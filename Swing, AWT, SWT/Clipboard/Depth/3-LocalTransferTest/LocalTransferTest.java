
/**
 * Локальный буфер обмена.
 * <p/>
 * @version 1.01 2004-08-25
 * @author Cay Horstmann
 */
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.*;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.swing.*;

/**
 * Пример передачи ссылок на объекты в рамках одной JVM.
 */
public class LocalTransferTest
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new LocalTransferFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм с левой панелью редактирования кривой третьего порядка и правой панелью для отображения произвольных фигур, в
 * также с кнопками копирования и вставки.
 */
class LocalTransferFrame extends JFrame
{
    private CubicCurvePanel curvePanel;
    private ShapePanel shapePanel;
    private Clipboard clipboard = new Clipboard("local");
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;

    LocalTransferFrame()
    {
        setTitle("LocalTransferTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        curvePanel = new CubicCurvePanel();
        curvePanel.setPreferredSize(new Dimension(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT));
        shapePanel = new ShapePanel();

        add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, curvePanel, shapePanel), BorderLayout.CENTER);
        JPanel panel = new JPanel();

        JButton copyButton = new JButton("Copy");
        panel.add(copyButton);
        copyButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                copy();
            }
        });

        JButton pasteButton = new JButton("Paste");
        panel.add(pasteButton);
        pasteButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                paste();
            }
        });

        add(panel, BorderLayout.SOUTH);
    }

    /**
     * Копирование кривой третьего порядка в локальный буфер обмена.
     */
    private void copy()
    {
        LocalSelection selection = new LocalSelection(curvePanel.getShape());
        clipboard.setContents(selection, null);
    }

    /**
     * Вставка фигуры из локального буфера обмена в правую панель.
     */
    private void paste()
    {
        try
        {
            DataFlavor flavor = new DataFlavor("application/x-java-jvm-local-objectref;class=java.awt.Shape");
            if (clipboard.isDataFlavorAvailable(flavor))
            {
                shapePanel.setShape((Shape) clipboard.getData(flavor));
            }
        }
        catch (ClassNotFoundException | UnsupportedFlavorException | IOException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
    }
}

/**
 * Панель для рисования кривой третьего порядка с возможностью перемещения ее маркеров.
 */
class CubicCurvePanel extends JPanel
{
    private Point2D[] p =
    {
        new Point2D.Double(10, 10),
        new Point2D.Double(10, 100),
        new Point2D.Double(100, 10),
        new Point2D.Double(100, 200)
    };
    private static int SIZE = 10;
    private int current;

    CubicCurvePanel()
    {
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent event)
            {
                for (int i = 0; i < p.length; i++)
                {
                    double x = p[i].getX() - SIZE / 2;
                    double y = p[i].getY() - SIZE / 2;
                    Rectangle2D r = new Rectangle2D.Double(x, y, SIZE, SIZE);
                    if (r.contains(event.getPoint()))
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
                p[current] = event.getPoint();
                repaint();
            }
        });

        current = -1;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < p.length; i++)
        {
            double x = p[i].getX() - SIZE / 2;
            double y = p[i].getY() - SIZE / 2;
            g2.fill(new Rectangle2D.Double(x, y, SIZE, SIZE));
        }
        g2.draw(getShape());
    }

    /**
     * Получение текущей кривой третьего порядка.
     * <p/>
     * @return Кривая третьего порядка
     */
    public Shape getShape()
    {
        return new CubicCurve2D.Double(
            p[0].getX(), p[0].getY(),
            p[1].getX(), p[1].getY(),
            p[2].getX(), p[2].getY(),
            p[3].getX(), p[3].getY());
    }
}

/**
 * На данной панели отображаются произвольные фигуры.
 */
class ShapePanel extends JPanel
{
    private Shape shape;

    /**
     * Указание фигуры для отображения на панели.
     * <p/>
     * @param aShape Произвольная фигура
     */
    public void setShape(Shape aShape)
    {
        shape = aShape;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (shape != null)
        {
            g2.draw(shape);
        }
    }
}

/**
 * Класс-оболочка для передачи ссылок на объекты в рамках одной JVM.
 */
class LocalSelection implements Transferable
{
    private Object obj;

    /**
     * Выбор объекта.
     * <p/>
     * @param o Произвольный объект
     */
    LocalSelection(Object o)
    {
        obj = o;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors()
    {
        DataFlavor[] flavors = new DataFlavor[1];
        Class<?> type = obj.getClass();
        String mimeType = "application/x-java-jvm-local-objectref;class=" + type.getName();
        try
        {
            flavors[0] = new DataFlavor(mimeType);
            return flavors;
        }
        catch (ClassNotFoundException e)
        {
            return new DataFlavor[0];
        }
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor)
    {
        return "application".equals(flavor.getPrimaryType())
            && "x-java-jvm-local-objectref".equals(flavor.getSubType())
            && flavor.getRepresentationClass().isAssignableFrom(obj.getClass());
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException
    {
        if (!isDataFlavorSupported(flavor))
        {
            throw new UnsupportedFlavorException(flavor);
        }
        return obj;
    }
}
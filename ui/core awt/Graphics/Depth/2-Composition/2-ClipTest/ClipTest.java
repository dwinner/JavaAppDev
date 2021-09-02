
/**
 * Фигуры отсечения.
 * <p/>
 * @version 1.02 2004-08-24
 * @author Cay Horstmann
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Пример использования фигуры отсечения.
 */
public class ClipTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new ClipTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм содержит флажок для установки режима отсечения, а также панель для рисования набора линий.
 */
class ClipTestFrame extends JFrame
{
    private JPanel panel;
    private Shape clipShape;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;

    ClipTestFrame()
    {
        setTitle("ClipTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        final JCheckBox checkBox = new JCheckBox("Clip");
        checkBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                panel.repaint();
            }
        });
        add(checkBox, BorderLayout.NORTH);

        panel = new JPanel()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;

                if (clipShape == null)
                {
                    clipShape = makeClipShape(g2);
                }

                g2.draw(clipShape);

                if (checkBox.isSelected())
                {
                    g2.clip(clipShape);
                }

                // Рисование линий.
                final int NLINES = 50;
                Point2D p = new Point2D.Double(0, 0);
                for (int i = 0; i < NLINES; i++)
                {
                    double x = (2 * getWidth() * i) / NLINES;
                    double y = (2 * getHeight() * (NLINES - 1 - i)) / NLINES;
                    Point2D q = new Point2D.Double(x, y);
                    g2.draw(new Line2D.Double(p, q));
                }
            }
        };
        add(panel, BorderLayout.CENTER);
    }

    /**
     * Создание фигуры отсечения.
     * <p/>
     * @param g2 Графический контекст
     * <p/>
     * @return Фигура отсечения
     */
    Shape makeClipShape(Graphics2D g2)
    {
        FontRenderContext context = g2.getFontRenderContext();
        Font f = new Font("Serif", Font.PLAIN, 100);
        GeneralPath lClipShape = new GeneralPath();

        TextLayout layout = new TextLayout("Hello", f, context);
        AffineTransform transform = AffineTransform.getTranslateInstance(0, 100);
        Shape outline = layout.getOutline(transform);
        lClipShape.append(outline, false);

        layout = new TextLayout("World", f, context);
        transform = AffineTransform.getTranslateInstance(0, 200);
        outline = layout.getOutline(transform);
        lClipShape.append(outline, false);
        return lClipShape;
    }
}

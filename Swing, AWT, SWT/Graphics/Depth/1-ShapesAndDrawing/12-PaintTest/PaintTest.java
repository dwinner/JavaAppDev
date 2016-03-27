
/**
 * Типы расцветок.
 * <p/>
 * @version 1.02 2004-08-24
 * @author Cay Horstmann
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Пример использования разных типов расцветки.
 */
public class PaintTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new PaintTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм с переключателями для выбора типа расцветки и панелью, в которой отображается круговая область с выбранной
 * расцветкой.
 */
class PaintTestFrame extends JFrame
{
    private PaintPanel canvas;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;

    PaintTestFrame()
    {
        setTitle("PaintTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        canvas = new PaintPanel();
        add(canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        ButtonGroup group = new ButtonGroup();

        JRadioButton colorButton = new JRadioButton("Color", true);
        buttonPanel.add(colorButton);
        group.add(colorButton);
        colorButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                canvas.setColor();
            }
        });

        JRadioButton gradientPaintButton = new JRadioButton("Gradient Paint", false);
        buttonPanel.add(gradientPaintButton);
        group.add(gradientPaintButton);
        gradientPaintButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                canvas.setGradientPaint();
            }
        });

        JRadioButton texturePaintButton = new JRadioButton("Texture Paint", false);
        buttonPanel.add(texturePaintButton);
        group.add(texturePaintButton);
        texturePaintButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                canvas.setTexturePaint();
            }
        });

        add(buttonPanel, BorderLayout.NORTH);
    }
}

/**
 * На данной панели отображается круговая область с выбранным типом расцветки.
 */
class PaintPanel extends JPanel
{
    private Paint paint;
    private BufferedImage bufferedImage;

    PaintPanel()
    {
        try
        {
            /*
             * Для JDK <= 1.4 следующий один оператор заменяется таким кодом:
             * Image image = Toolkit.getDefaultToolkit().getImage("blue-ball.gif");
             * MediaTracker tracker = new MediaTracker(this);
             * tracker.addImage(image, 0);
             * try { tracker.waitForID(0); } catch (InterruptedException e) { }
             * bufferedImage = new BufferedImage( image.getWidth(null), image.getHeight(null),
             * BufferedImage.TYPE_INT_ARGB );
             * Graphics2D g2 = bufferedImage.createGraphics(); g2.drawImage(image, 0, 0, null);
             */
            bufferedImage = ImageIO.read(new File("blue-ball.gif"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        setColor();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(paint);
        Ellipse2D circle = new Ellipse2D.Double(0, 0, getWidth(), getHeight());
        g2.fill(circle);
    }

    /**
     * Использование сплошного цвета.
     */
    public void setColor()
    {
        paint = Color.red;
        repaint();
    }

    /**
     * Градиентное заполнение.
     */
    public void setGradientPaint()
    {
        paint = new GradientPaint(
            0,
            0,
            Color.red,
            (float) getWidth(),
            (float) getHeight(),
            Color.blue);
        repaint();
    }

    /**
     * Мозаичное заполнение.
     */
    public void setTexturePaint()
    {
        Rectangle2D anchor = new Rectangle2D.Double(
            0,
            0,
            2 * bufferedImage.getWidth(),
            2 * bufferedImage.getHeight());
        paint = new TexturePaint(bufferedImage, anchor);
        repaint();
    }
}

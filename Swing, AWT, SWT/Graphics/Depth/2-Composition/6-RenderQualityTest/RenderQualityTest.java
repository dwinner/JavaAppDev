
/**
 * Действие параметров рисования
 * <p/>
 * @version 1.02 2004-08-24
 * @author Cay Horstmann
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Программа, демонстрирующая действие различных параметров рисования.
 */
public class RenderQualityTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new RenderQualityTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм, содержащий переключатели для указания параметров рисования и изображение, которое получается в результате их
 * применения.
 */
class RenderQualityTestFrame extends JFrame
{
    private RenderQualityPanel canvas;
    private JPanel buttonBox;
    private RenderingHints hints;
    private int r;
    private static final int DEFAULT_WIDTH = 750;
    private static final int DEFAULT_HEIGHT = 500;

    RenderQualityTestFrame()
    {
        setTitle("RenderQualityTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        buttonBox = new JPanel();
        buttonBox.setLayout(new GridBagLayout());
        hints = new RenderingHints(null);

        makeButtons("KEY_ANTIALIASING", "VALUE_ANTIALIAS_ON", "VALUE_ANTIALIAS_OFF");
        makeButtons("KEY_RENDERING", "VALUE_RENDER_QUALITY", "VALUE_RENDER_SPEED");
        makeButtons("KEY_DITHERING", "VALUE_DITHER_ENABLE", "VALUE_DITHER_DISABLE");
        makeButtons("KEY_TEXT_ANTIALIASING", "VALUE_TEXT_ANTIALIAS_ON", "VALUE_TEXT_ANTIALIAS_OFF");
        makeButtons("KEY_FRACTIONALMETRICS", "VALUE_FRACTIONALMETRICS_ON", "VALUE_FRACTIONALMETRICS_OFF");
        makeButtons("KEY_ALPHA_INTERPOLATION", "VALUE_ALPHA_INTERPOLATION_QUALITY", "VALUE_ALPHA_INTERPOLATION_SPEED");
        makeButtons("KEY_COLOR_RENDERING", "VALUE_COLOR_RENDER_QUALITY", "VALUE_COLOR_RENDER_SPEED");
        makeButtons("KEY_INTERPOLATION", "VALUE_INTERPOLATION_NEAREST_NEIGHBOR", "VALUE_INTERPOLATION_BICUBIC");
        makeButtons("KEY_STROKE_CONTROL", "VALUE_STROKE_NORMALIZE", "VALUE_STROKE_PURE");
        canvas = new RenderQualityPanel();
        canvas.setRenderingHints(hints);

        add(canvas, BorderLayout.CENTER);
        add(buttonBox, BorderLayout.NORTH);
    }

    /**
     * Создание набора кнопок для формирования ключа и значений.
     * <p/>
     * @param key    Имя ключа
     * @param value1 Имя первого значения ключа
     * @param value2 Имя второго значения ключа
     */
    void makeButtons(String key, String value1, String value2)
    {
        try
        {
            final RenderingHints.Key k = (RenderingHints.Key) RenderingHints.class.getField(key).get(null);
            final Object v1 = RenderingHints.class.getField(value1).get(null);
            final Object v2 = RenderingHints.class.getField(value2).get(null);
            JLabel label = new JLabel(key);

            buttonBox.add(label, new GBC(0, r).setAnchor(GBC.WEST));
            ButtonGroup group = new ButtonGroup();
            JRadioButton b1 = new JRadioButton(value1, true);

            buttonBox.add(b1, new GBC(1, r).setAnchor(GBC.WEST));
            group.add(b1);
            b1.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent event)
                {
                    hints.put(k, v1);
                    canvas.setRenderingHints(hints);
                }
            });
            JRadioButton b2 = new JRadioButton(value2, false);

            buttonBox.add(b2, new GBC(2, r).setAnchor(GBC.WEST));
            group.add(b2);
            b2.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent event)
                {
                    hints.put(k, v2);
                    canvas.setRenderingHints(hints);
                }
            });
            hints.put(k, v1);
            r++;
        }
        catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
}

/**
 * Панель с рисунком, который получается в результате применения параметров рисования.
 */
class RenderQualityPanel extends JPanel
{
    private RenderingHints hints = new RenderingHints(null);
    private Image image;

    RenderQualityPanel()
    {
        try
        {
            image = ImageIO.read(new File("clouds.jpg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHints(hints);

        g2.scale(1.05, 1.05);
        g2.rotate(Math.PI / 12);
        g2.translate(120, -30);
        g2.drawImage(image, 0, 0, null);

        g2.draw(new Ellipse2D.Double(0, 0, image.getWidth(null), image.getHeight(null)));
        g2.setFont(new Font("Serif", Font.ITALIC, 40));
        g2.drawString("Hello", 75, 75);
        g2.setPaint(Color.YELLOW);
        g2.translate(0, -80);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2.fill(new Rectangle2D.Double(100, 100, 200, 100));
    }

    /**
     * Установка параметров и перерисовка изображения.
     * <p/>
     * @param h Параметры рисования.
     */
    public void setRenderingHints(RenderingHints h)
    {
        hints = h;
        repaint();
    }
}

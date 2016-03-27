
/**
 * Преобразование координат.
 * <p/>
 * @version 1.02 2004-08-24
 * @author Cay Horstmann
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Пример использования различных преобразований координат.
 */
public class TransformTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new TransformTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм с набором переключателей для выбора типа преобразования и панель для отображения результатов.
 */
class TransformTestFrame extends JFrame
{
    private TransformPanel canvas;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;

    TransformTestFrame()
    {
        setTitle("TransformTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        canvas = new TransformPanel();
        add(canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        ButtonGroup group = new ButtonGroup();

        JRadioButton rotateButton = new JRadioButton("Rotate", true);
        buttonPanel.add(rotateButton);
        group.add(rotateButton);
        rotateButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                canvas.setRotate();
            }
        });

        JRadioButton translateButton = new JRadioButton("Translate", false);
        buttonPanel.add(translateButton);
        group.add(translateButton);
        translateButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                canvas.setTranslate();
            }
        });

        JRadioButton scaleButton = new JRadioButton("Scale", false);
        buttonPanel.add(scaleButton);
        group.add(scaleButton);
        scaleButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                canvas.setScale();
            }
        });

        JRadioButton shearButton = new JRadioButton("Shear", false);
        buttonPanel.add(shearButton);
        group.add(shearButton);
        shearButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                canvas.setShear();
            }
        });

        add(buttonPanel, BorderLayout.NORTH);
    }
}

/**
 * Панель с изображением исходного квадрата и его вида после применения выбранного преобразования.
 */
class TransformPanel extends JPanel
{
    private Rectangle2D square;
    private AffineTransform t;

    TransformPanel()
    {
        square = new Rectangle2D.Double(-50, -50, 100, 100);
        t = new AffineTransform();
        setRotate();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(getWidth() / 2, getHeight() / 2);
        g2.setPaint(Color.gray);
        g2.draw(square);
        g2.transform(t);
        // Здесь не используется метод setTransform, потому что
        // нам нужно комбинировать преобразования.
        g2.setPaint(Color.black);
        g2.draw(square);
    }

    /**
     * Установка преобразования-поворота.
     */
    public final void setRotate()
    {
        t.setToRotation(Math.toRadians(30));
        repaint();
    }

    /**
     * Установка преобразования-перемещения.
     */
    public void setTranslate()
    {
        t.setToTranslation(20, 15);
        repaint();
    }

    /**
     * Установка преобразования-масштабирования.
     */
    public void setScale()
    {
        t.setToScale(2.0, 1.5);
        repaint();
    }

    /**
     * Установка преобразования-сдвига.
     */
    public void setShear()
    {
        t.setToShear(-0.2, 0);
        repaint();
    }
}

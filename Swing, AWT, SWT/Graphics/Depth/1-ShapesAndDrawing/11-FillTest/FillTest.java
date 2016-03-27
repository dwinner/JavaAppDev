
/**
 * @version 1.32 2004-05-03
 * @author Cay Horstmann
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.SwingUtilities.invokeLater;

public class FillTest
{
    public static void main(String[] args)
    {
        invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                FillFrame frame = new FillFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм, содержащий панель для рисования.
 */
class FillFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 400;

    FillFrame()
    {
        setTitle("FillTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Добавление панели к фрейму.

        FillPanel panel = new FillPanel();
        add(panel);
    }
}

/**
 * Панель, на которой отображаются прямоугольник и эллипс.
 */
class FillPanel extends JPanel
{
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Рисование прямоугольника.

        double leftX = 100;
        double topY = 100;
        double width = 200;
        double height = 150;

        Rectangle2D rect = new Rectangle2D.Double(leftX, topY, width, height);
        g2.setPaint(Color.RED);
        g2.fill(rect);

        // Рисование эллипса, вписанного в прямоугольник.

        Ellipse2D ellipse = new Ellipse2D.Double();
        ellipse.setFrame(rect);
        // Бледный сине-зеленый цвет.
        g2.setPaint(new Color(0, 128, 128));
        g2.fill(ellipse);
    }
}
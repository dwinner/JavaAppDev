import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Компонент, отображающий мячи.
 * <p/>
 * @version 1.33 2007-05-17
 * @author Cay Horstmann
 */
public class BallComponent extends JPanel
{
    private ArrayList<Ball> balls = new ArrayList<>();

    /**
     * Добавить мяч к компоненту.
     * <p/>
     * @param b Добавляемый мяч
     */
    public void add(Ball b)
    {
        balls.add(b);
    }

    @Override public void paintComponent(Graphics g)
    {
        super.paintComponent(g);    // Очистка фона
        Graphics2D g2 = (Graphics2D) g;
        for (Ball aBall : balls)
        {
            g2.fill(aBall.getShape());
        }
    }
}

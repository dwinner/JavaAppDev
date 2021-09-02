import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Демонстрирует анимированный прыгающий мяч в отдельном потоке.
 * @version 1.33 2007-05-17
 * @author Cay Horstmann
 */
public class BounceThread
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                JFrame theFrame = new BounceFrame();
                theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                theFrame.setVisible(true);
            }
        });
    }
}

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Демонстрирует анимированный прыгающий мяч.
 * @author JavaFx
 */
public class Bounce
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

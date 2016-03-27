import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Эта программа демонстрирует API настольных приложений.
 * <p/>
 * @version 1.00 2007-09-22
 * @author Cay Horstmann
 */
public class DesktopAppTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new DesktopAppFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

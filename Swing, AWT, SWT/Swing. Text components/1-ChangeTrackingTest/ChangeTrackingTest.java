import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Отслеживание событий интерфейса Document.
 * @version 1.40 2007-08-05
 * @author Cay Horstmann
 */
public class ChangeTrackingTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                ColorFrame frame = new ColorFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

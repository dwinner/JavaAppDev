import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Демонстрация правил Портера-Даффа для объединения изображений.
 * <p/>
 * @version 1.03 2007-08-16
 * @author Cay Horstmann
 */
public class CompositeTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new CompositeTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Данная программа демонстрирует использование класса URLConnection для передачи запроса POST.
 * <p/>
 * @author Cay Horstmann
 * @version 1.20 2007-06-25
 */
public class PostTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new PostTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

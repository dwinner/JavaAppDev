import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Эта программа демонстрирует использование штрихов различных типов.
 * <p/>
 * @version 1.03 2007-08-16
 * @author Cay Horstmann
 */
public class StrokeTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new StrokeTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Пример печати двухмерной графики.
 * <p/>
 * @version 1.12 2007-08-16
 * @author Cay Horstmann
 */
public class PrintTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new PrintTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

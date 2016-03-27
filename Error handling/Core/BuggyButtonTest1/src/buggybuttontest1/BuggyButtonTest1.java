package buggybuttontest1;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * @version 1.0.0 2012-01-01
 * @author dwinner@inbox.ru
 */
public class BuggyButtonTest1
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                BuggyButtonFrame frame = new BuggyButtonFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

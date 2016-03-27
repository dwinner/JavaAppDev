package amazonapp;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Клиент для тестовой программы электронной коммерции Amazon.
 *
 * @version 1.10 2007-10-20
 * @author Cay Horstmann
 */
public class AmazonApp
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new AmazonTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * @version 1.32 2007-06-22
 * @author Cay Horstmann
 */
public class ZipTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                ZipTestFrame frame = new ZipTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Программа для обработки выражений XPath.
 * @version 1.01 2007-06-25
 * @author Cay Horstmann
 */
public class XPathTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new XPathFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

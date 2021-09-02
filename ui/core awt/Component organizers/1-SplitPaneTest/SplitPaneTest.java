import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Использование панели с границей раздела в качестве организатора компонентов.
 * <p/>
 * @version 1.03
 * @author Cay Horstmann
 */
public class SplitPaneTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new SplitPaneFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

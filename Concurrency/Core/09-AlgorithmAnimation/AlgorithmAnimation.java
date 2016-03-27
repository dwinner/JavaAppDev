import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Использование синхронизаторов.
 * @author dwinner@inbox.ru
 */
public class AlgorithmAnimation
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new AnimationFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

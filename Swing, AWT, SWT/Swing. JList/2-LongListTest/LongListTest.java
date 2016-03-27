import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Пример списка с динамической генерацией пунктов
 * <p/>
 * @version 1.23 2007-08-01
 * @author Cay Horstmann
 */
public class LongListTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new LongListFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

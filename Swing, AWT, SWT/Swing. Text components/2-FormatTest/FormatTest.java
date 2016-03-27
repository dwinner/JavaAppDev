import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Программа для проверки полей форматированного текста.
 * <p/>
 * @version 1.02 2007-06-12
 * @author Cay Horstmann
 */
public class FormatTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                FormatTestFrame frame = new FormatTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Пример отображения XML-документа в виде древовидной структуры
 * <p/>
 * @version 1.11 2007-06-24
 * @author Cay Horstmann
 */
public class DOMTreeTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new DOMTreeFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

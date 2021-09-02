import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Пример отображения HTML-документов в панели редактирования.
 * <p/>
 * @version 1.03 2007-08-01
 * @author Cay Horstmann
 */
public class EditorPaneTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new EditorPaneFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

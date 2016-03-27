import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Пример применения пользовательской модели дерева для отображения полей объекта.
 * <p/>
 * @version 1.23 2007-08-01
 * @author Cay Horstmann
 */
public class ObjectInspectorTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new ObjectInspectorFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

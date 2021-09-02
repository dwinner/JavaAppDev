import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Эта программа демонстрирует пример пользовательского загрузчика, умеющего расшифровывать файлы
 * классов.
 * <p/>
 * @version 1.22 2007-10-05
 * @author Cay Horstmann
 */
public class ClassLoaderTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new ClassLoaderFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

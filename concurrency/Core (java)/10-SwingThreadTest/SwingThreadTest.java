import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Эта программа демонстрирует, что поток, работающий параллельно
 * с потоком диспетчера событий, может вызвать ошибки в компонентах Swing.
 * @version 1.23 2007-05-17
 * @author Cay Horstmann
 */
public class SwingThreadTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                SwingThreadFrame frame = new SwingThreadFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

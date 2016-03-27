import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Эта программа демонстрирует осуществление передачи сериализуемых объектов между виртуальными
 * машинами.
 * <p/>
 * @version 1.02 2007-08-16
 * @author Cay Horstmann
 */
public class SerialTransferTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new SerialTransferFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

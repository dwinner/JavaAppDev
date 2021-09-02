import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Данная программа демонстрирует обмен изображениями между Java-приложением и системным буфером
 * обмена.
 * <p/>
 * @version 1.22 2007-08-16
 * @author Cay Horstmann
 */
public class ImageTransferTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new ImageTransferFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

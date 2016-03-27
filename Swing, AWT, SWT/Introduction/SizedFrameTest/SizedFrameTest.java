
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Позиционирование фрейма при помощи платформы.
 * @version 1.32 2007-04-14
 * @author Cay Horstmann
 */
public class SizedFrameTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                SizedFrame frame = new SizedFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }

        });
    }

}

class SizedFrame extends JFrame
{
    SizedFrame()
    {
        // Определение размеров экрана.

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        // Установка ширины и высоты фрейма и позиционирование с помощью платформы.

        setSize(screenWidth / 2, screenHeight / 2);
        setLocationByPlatform(true);

        // Установка пиктограммы и заголовка окна.

        Image img = kit.getImage("icon.gif");
        setIconImage(img);
        setTitle("SizedFrame");
    }

}

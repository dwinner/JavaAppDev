
/**
 * @version 1.31 2004-05-03
 * @author Cay Horstmann
 */
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class CenteredFrameTest
{
    public static void main(String[] args)
    {
        CenteredFrame frame = new CenteredFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}

class CenteredFrame extends JFrame
{
    CenteredFrame()
    {
        // Определение размеров экрана.

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        // Размещение фрейма по центру экрана.

        setSize(screenWidth / 2, screenHeight / 2);
        setLocation(screenWidth / 4, screenHeight / 4);

        // Установка пиктограммы и заголовка окна.

        Image img = kit.getImage("icon.gif");
        setIconImage(img);
        setTitle("CenteredFrame");
    }

}
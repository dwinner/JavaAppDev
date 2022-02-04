
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * @version 1.03 2007-06-12
 * @author Cay Horstmann
 */
public class RobotTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                // Создание фрейма, содержащего панель с кнопками

                ButtonFrame frame = new ButtonFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                // Связывание объекта Robot с экранным устройством

                GraphicsEnvironment environment =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
                GraphicsDevice screen = environment.getDefaultScreenDevice();

                try
                {
                    Robot robot = new Robot(screen);
                    runTest(robot);
                }
                catch (AWTException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Запускает процедуру тестирования.
     * @param robot Объект Robot, связанный с экранным устройством
     */
    public static void runTest(Robot robot)
    {
        // Имитация нажатия пробела
        robot.keyPress(' ');
        robot.keyRelease(' ');

        // Имитация нажатия клавиши <Tab> с последующим нажатием
        // клавиши пробела.
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyPress(' ');
        robot.keyRelease(' ');

        // Имитация щелчка левой кнопкой мыши.
        robot.delay(2000);
        robot.mouseMove(200, 50);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);

        // Захват содержимого экрана и вывод полученного изображения.
        robot.delay(2000);
        BufferedImage image = robot.createScreenCapture(new Rectangle(0, 0, 400, 300));

        ImageFrame frame = new ImageFrame(image);
        frame.setVisible(true);
    }
}

/**
 * Фрейм для вывода захваченного изображения.
 */
class ImageFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 450;
    public static final int DEFAULT_HEIGHT = 350;
    
    /**
     * @param image Изображение для вывода.
     */
    public ImageFrame(Image image)
    {
        setTitle("Capture");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JLabel label = new JLabel(new ImageIcon(image));
        add(label);
    }
}

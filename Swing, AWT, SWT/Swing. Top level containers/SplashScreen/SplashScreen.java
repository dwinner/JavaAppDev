// Создание заставки для приложения
import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JWindow;

public class SplashScreen extends JWindow
{
    // Необходимые нам изображения
    private Image capture;
    private Image splash = getToolkit().getImage("splash.jpg");

    public SplashScreen()
    {
        super();
        // Размер и положение окна на экране
        setLocation(200, 100);
        setSize(200, 600);

        try
        {   // "Снимаем" экранную копию
            Robot robot = new Robot();
            capture = robot.createScreenCapture(new Rectangle(0, 0, 800, 600));
        }
        catch (AWTException ex)
        {
            ex.printStackTrace();
        }

        // Добавляем компонент-заставку
        getContentPane().add(new Splash());

        // Выводим окно на экран
        setVisible(true);
        try
        {   // Заканчиваем работу по истечении некоторого времени
            Thread.sleep(10000);
        }
        catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }
        System.exit(0);
    }

    // Компонент, рисующий заставку
    private class Splash extends JComponent
    {
        @Override
        public void paintComponent(Graphics g)
        {
            // Рисуем копию экрана
            g.drawImage(splash, 0, 0, this);
        }
    }

    public static void main(String[] args)
    {
        new SplashScreen();
    }
}
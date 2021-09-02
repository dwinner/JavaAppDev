// �������� �������� ��� ����������
import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JWindow;

public class SplashScreen extends JWindow
{
    // ����������� ��� �����������
    private Image capture;
    private Image splash = getToolkit().getImage("splash.jpg");

    public SplashScreen()
    {
        super();
        // ������ � ��������� ���� �� ������
        setLocation(200, 100);
        setSize(200, 600);

        try
        {   // "�������" �������� �����
            Robot robot = new Robot();
            capture = robot.createScreenCapture(new Rectangle(0, 0, 800, 600));
        }
        catch (AWTException ex)
        {
            ex.printStackTrace();
        }

        // ��������� ���������-��������
        getContentPane().add(new Splash());

        // ������� ���� �� �����
        setVisible(true);
        try
        {   // ����������� ������ �� ��������� ���������� �������
            Thread.sleep(10000);
        }
        catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }
        System.exit(0);
    }

    // ���������, �������� ��������
    private class Splash extends JComponent
    {
        @Override
        public void paintComponent(Graphics g)
        {
            // ������ ����� ������
            g.drawImage(splash, 0, 0, this);
        }
    }

    public static void main(String[] args)
    {
        new SplashScreen();
    }
}
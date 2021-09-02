
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * ������� ������������ ������� ���������.
 * @version 1.32 2007-06-12
 * @author Cay Horstmann
 */
public class NotHelloWorld
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                NotHelloWorldFrame frame = new NotHelloWorldFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }

        });
    }

}

/**
 * �����, ���������� ������ � ����������
 */
class NotHelloWorldFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    NotHelloWorldFrame()
    {
        setTitle("NotHelloWorld");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // ���������� ������ � ������

        NotHelloWorldPanel panel = new NotHelloWorldPanel();
        add(panel);
    }

}

/**
 * ������, ������������ ���������.
 */
class NotHelloWorldPanel extends JPanel
{
    public static final int MESSAGE_X = 75;
    public static final int MESSAGE_Y = 100;

    @Override
    public void paintComponent(Graphics g)
    {
        g.drawString("Not a Hello, World program", MESSAGE_X, MESSAGE_Y);
    }

}

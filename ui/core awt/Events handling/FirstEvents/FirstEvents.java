
/**
 * FirstEvents.java. ������� - ������� ������ �� ����������.
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class FirstEvents extends JFrame
{
    public FirstEvents()
    {
        super("FirstEvents");
        // ��� �������� ���� - �����
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������������ ������ ���������
        addKeyListener(new KeyL());
        // ������� ���� �� �����
        setSize(200, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new FirstEvents();
    }

}

/**
 * ���� ����� ����� �������� ��������� � ��������
 */
class KeyL implements KeyListener
{
    // ������ �������
    @Override
    public void keyTyped(KeyEvent e)
    {
        System.out.println(e);
    }

    // ������� �������
    @Override
    public void keyPressed(KeyEvent e)
    {
        System.out.println(e);
    }

    // ���������� ������� �������
    @Override
    public void keyReleased(KeyEvent e)
    {
        System.out.println(e);
    }

}
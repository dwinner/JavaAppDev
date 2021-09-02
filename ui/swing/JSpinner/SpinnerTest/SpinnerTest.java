import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * ��������� ��� ������������ ���������.
 */
public class SpinnerTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                SpinnerFrame frame = new SpinnerFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

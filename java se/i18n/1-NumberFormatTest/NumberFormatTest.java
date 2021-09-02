import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Пример формматирования чисел с учетом различных региональных стандартов.
 * <p/>
 * @version 1.13 2007-07-25
 * @author Cay Horstmann
 */
public class NumberFormatTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new NumberFormatFrame();
                frame.setTitle("Number Format Sample");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

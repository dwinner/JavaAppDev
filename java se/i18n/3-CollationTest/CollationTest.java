import javax.swing.*;
import java.awt.*;

/**
 * Данная программа демонстрирует сортировку строк
 * с учетом различных региональных стандартов.
 * @version 1.13 2007-07-25
 * @author Cay Horstmann
 */
public class CollationTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new CollationFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

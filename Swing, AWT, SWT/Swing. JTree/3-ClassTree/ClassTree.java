import javax.swing.*;
import java.awt.*;

/**
 * Программа, иллюстрирующая процесс отображения узлов
 * дерева, представляющих классы и их суперклассы.
 * @version 1.03 2007-08-01
 * @author Cay Horstmann
 */
public class ClassTree
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new ClassTreeFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

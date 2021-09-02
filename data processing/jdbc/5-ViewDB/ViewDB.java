import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Эта программа демонстрирует использование метаданных для отображения таблиц базы данных.
 * <p/>
 * @version 1.31 2007-06-28
 * @author Cay Horstmann
 */
public class ViewDB
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new ViewDBFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

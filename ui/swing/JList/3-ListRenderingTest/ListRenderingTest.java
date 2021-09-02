import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Пример использования объекта отображения ячеек списка.
 * @author JavaFx
 */
public class ListRenderingTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new ListRenderingFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

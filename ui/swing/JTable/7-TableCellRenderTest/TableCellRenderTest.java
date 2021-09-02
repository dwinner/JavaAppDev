import javax.swing.*;
import java.awt.*;

/**
 * Пример перерисовки ячеек таблицы
 * и редактирования их значений.
 * @version 1.02 2007-08-01
 * @author Cay Horstmann
 */
public class TableCellRenderTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new TableCellRenderFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

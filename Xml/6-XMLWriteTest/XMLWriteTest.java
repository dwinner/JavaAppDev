import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Данная программа показывает пример написания XML-файла. Она сохраняет
 * файл формата SVG с описанием современных приемов рисования.
 * @version 1.10 2004-09-04
 * @author Cay Horstmann
 */
public class XMLWriteTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                XMLWriteFrame frame = new XMLWriteFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

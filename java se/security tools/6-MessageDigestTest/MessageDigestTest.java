import javax.swing.*;
import java.awt.*;

/**
 * Данная программа вычисляет дайджест сообщения для файла
 * или содержимого текстовой области.
 * @version 1.13 2007-10-06
 * @author Cay Horstmann
 */
public class MessageDigestTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new MessageDigestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

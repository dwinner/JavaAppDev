import javax.swing.*;
import java.awt.*;

/**
 * Программа аутентифицирует пользователя, используя специальный модуль
 * регистрации, а затем выполняет SysPropAction с привилегиями
 * зарегистрированного пользователя.
 * @version 1.0 2004-09-14
 * @author Cay Horstmann
 */
public class JAASTest
{
    public static void main(String[] args)
    {
        System.setSecurityManager(new SecurityManager());
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new JAASFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

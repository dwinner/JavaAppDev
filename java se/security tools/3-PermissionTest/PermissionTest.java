import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Пример применения пользовательского класса прав доступа WordCheckPermission.
 * <p/>
 * @version 1.03 2007-10-06
 * @author Cay Horstmann
 */
public class PermissionTest
{
    public static void main(String[] args)
    {
        System.setProperty("java.security.policy", "PermissionTest.policy");
        System.setSecurityManager(new SecurityManager());
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new PermissionTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

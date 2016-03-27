import javax.swing.JFrame;

/**
 * Этапы создания UI.
 * Тестирование диалогового окна входа в систему
 * @author dwinner@inbox.ru
 */
public class LoginDialogTest
{
    private LoginDialogTest() { }
    
    public static void main(String[] args)
    {
        new LoginDialog(new JFrame());
    }
}

import javax.security.auth.callback.*;
import java.io.IOException;

/**
 * Простой обработчик, представляющий пользовательское имя и пароль.
 * @version 1.0 2004-09-14
 * @author Cay Horstmann
 */
public class SimpleCallbackHandler implements CallbackHandler
{
    private String username;
    private char[] password;

    /**
     * Создание обработчика
     * @param username Пользовательское имя
     * @param password Символьный массив, содержащий пароль
     */
    public SimpleCallbackHandler(String username, char[] password)
    {
        this.username = username;
        this.password = password;
    }

    @Override
    public void handle(Callback[] callbacks)
       throws IOException, UnsupportedCallbackException
    {
        for (Callback callback : callbacks)
        {
            if (callback instanceof NameCallback)
            {
                ((NameCallback) callback).setName(username);
            }
            else if (callback instanceof PasswordCallback)
            {
                ((PasswordCallback) callback).setPassword(password);
            }
        }
    }
}

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.FileReader;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Регистрационный модуль, выполняющий аутентификацию пользователей
 * по регистрационным именам, паролям и ролям, прочитанным из
 * текстового файла.
 *
 * @author Cay Horstmann
 * @version 1.0 2004-09-14
 */
public class SimpleLoginModule implements LoginModule
{
    private Subject subject;
    private CallbackHandler callbackHandler;
    /* private Map<String, ?> sharedState; */
    private Map<String, ?> options;

    @Override
    public void initialize(Subject subject,
                           CallbackHandler callbackHandler,
                           Map<String, ?> sharedState,
                           Map<String, ?> options)
    {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        /* this.sharedState = sharedState; */
        this.options = options;
    }

    @Override
    public boolean login()
       throws LoginException
    {
        if (callbackHandler == null)
            throw new LoginException("no handler");
        NameCallback nameCall = new NameCallback("username: ");
        PasswordCallback passCall = new PasswordCallback("password: ", false);
        try
        {
            callbackHandler.handle(new Callback[]{nameCall, passCall});
        }
        catch (UnsupportedCallbackException | IOException ex)
        {
            String errorMessage = ex instanceof UnsupportedCallbackException
               ? "Unsupported callback"
               : "IO exception in callback";
            LoginException loginException = new LoginException(errorMessage);
            loginException.initCause(ex);
            throw loginException;
        }

        return checkLogin(nameCall.getName(), passCall.getPassword());
    }

    /**
     * Проверка корректности аутентификационной информации.
     * Если результаты проверки положительны, субъект получает
     * принципал, соответствующий пользовательскому имени и роли.
     *
     * @param userName Пользовательское имя
     * @param password Символьный массив, содержащий пароль
     * @return true если аутентификационная информация корректна
     */
    private boolean checkLogin(String userName, char[] password)
       throws LoginException
    {
        try (Scanner in = new Scanner(new FileReader("" + options.get("pwfile"))))
        {
            while (in.hasNextLine())
            {
                String[] inputs = in.nextLine().split("\\|");
                if (inputs[0].equals(userName) && Arrays.equals(inputs[1].toCharArray(), password))
                {
                    String role = inputs[2];
                    Set<Principal> principals = subject.getPrincipals();
                    principals.add(new SimplePrincipal("username", userName));
                    principals.add(new SimplePrincipal("role", role));

                    return true;
                }
            }
        }
        catch (IOException ioEx)
        {
            LoginException e2 = new LoginException("Can't open password file");
            e2.initCause(ioEx);
            throw e2;
        }
        return false;
    }

    @Override
    public boolean commit()
       throws LoginException
    {
        return true;
    }

    @Override
    public boolean abort()
       throws LoginException
    {
        return true;
    }

    @Override
    public boolean logout()
       throws LoginException
    {
        return true;
    }
}

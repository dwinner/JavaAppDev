import java.security.PrivilegedAction;

/**
 * Данное действие извлекает системное свойство.
 * @version 1.01 2007-10-06
 * @author Cay Horstmann
 */
public class SysPropAction implements PrivilegedAction<String>
{
    private String propertyName;

    /**
     * Создание действия для поиска требуемого свойства.
     * @param propertyName Имя свойства (например, "user.home")
     */
    public SysPropAction(String propertyName)
    {
        this.propertyName = propertyName;
    }

    @Override
    public String run()
    {
        return System.getProperty(propertyName);
    }
}

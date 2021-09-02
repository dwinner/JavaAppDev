import java.security.Permission;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Полномочие для выполнения проверки на наличие недопустимых слов.
 * <p/>
 * @version 1.0.0 1999-10-23
 * @author Cay Horstmann
 */
public class WordCheckPermission extends Permission
{
    private String action;

    public WordCheckPermission(String target, String anAction)
    {
        super(target);
        action = anAction;
    }

    @Override
    public String getActions()
    {
        return action;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == null)
        {
            return false;
        }
        if (!getClass().equals(other.getClass()))
        {
            return false;
        }
        WordCheckPermission b = (WordCheckPermission) other;
        if (!action.equals(b.action))
        {
            return false;
        }
        switch (action)
        {
            case "insert":
                return getName().equals(b.getName());
            case "avoid":
                return badWordSet().equals(b.badWordSet());
            default:
                return false;
        }
    }

    @Override
    public int hashCode()
    {
        return getName().hashCode() + action.hashCode();
    }

    @Override
    public boolean implies(Permission other)
    {
        if (!(other instanceof WordCheckPermission))
        {
            return false;
        }
        WordCheckPermission b = (WordCheckPermission) other;
        switch (action)
        {
            case "insert":
                return b.action.equals("insert") && getName().indexOf(b.getName()) >= 0;
            case "avoid":
                switch (b.action)
                {
                    case "avoid":
                        return b.badWordSet().containsAll(badWordSet());
                    case "insert":
                        for (String badWord : badWordSet())
                        {
                            if (b.getName().indexOf(badWord) >= 0)
                            {
                                return false;
                            }
                        }
                        return true;
                    default:
                        return false;
                }
            default:
                return false;
        }
    }

    /**
     * Возвращает слова, которые в данном праве доступа считаются недопустимыми.
     * <p/>
     * @return Множество недопустимых слов.
     */
    public Set<String> badWordSet()
    {
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(getName().split(",")));
        return set;
    }
}

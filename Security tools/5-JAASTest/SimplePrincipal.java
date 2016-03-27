import java.security.Principal;

/**
 * Принципал с именованным значением (например, "role=HR" или "username=harry").
 */
public class SimplePrincipal implements Principal
{
    private String desc;
    private String value;

    /**
     * Создание объекта SimplePrincipal, содержащего описание и значение
     *
     * @param desc  Описание
     * @param value Значение
     */
    public SimplePrincipal(String desc, String value)
    {
        this.desc = desc;
        this.value = value;
    }

    /**
     * Возвращает имя роли для данного принципала.
     *
     * @return Имя роли
     */
    @Override
    public String getName()
    {
        return desc + "=" + value;
    }

    @Override
    public boolean equals(Object otherObject)
    {
        if (this == otherObject)
            return true;
        if (otherObject == null)
            return false;
        if (getClass() != otherObject.getClass())
            return false;
        SimplePrincipal other = (SimplePrincipal) otherObject;
        return getName().equals(other.getName());
    }

    @Override
    public int hashCode()
    {
        return getName().hashCode();
    }
}

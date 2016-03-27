import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * Переменная с указанием типа, имени и значения.
 * <p/>
 * @author JavaFx
 */
public class Variable
{
    private Class<?> type;
    private String name;
    private Object value;
    private ArrayList<Field> fields;

    /**
     * Формирование переменной.
     * <p/>
     * @param aType  Тип.
     * @param aName  Имя.
     * @param aValue Значений.
     */
    public Variable(Class<?> aType, String aName, Object aValue)
    {
        type = aType;
        name = aName;
        value = aValue;
        fields = new ArrayList<>(0x8F);

        // Поиск всех полей класса, за исключнием строк и значений null.
        if (!type.isPrimitive() && !type.isArray() && !type.equals(String.class) && value != null)
        {
            // Поиск всех полей класса и всех суперклассов.
            for (Class<?> c = value.getClass(); c != null; c = c.getSuperclass())
            {
                Field[] fs = c.getDeclaredFields();
                AccessibleObject.setAccessible(fs, true);

                // Получение всех нестатических полей
                for (Field f : fs)
                {
                    if ((f.getModifiers() & Modifier.STATIC) == 0)
                    {
                        fields.add(f);
                    }
                }
            }
        }
    }

    /**
     * Получение значения переменной.
     * <p/>
     * @return Значение
     */
    public Object getValue()
    {
        return value;
    }

    /**
     * Получение всех нестатических полей для переменной.
     * <p/>
     * @return Списочный массив для полей.
     */
    public ArrayList<Field> getFields()
    {
        return fields;
    }

    @Override
    public String toString()
    {
        String r = type + " " + name;
        if (type.isPrimitive())
        {
            r += "=" + value;
        }
        else if (type.equals(String.class))
        {
            r += "=" + value;
        }
        else if (value == null)
        {
            r += "=null";
        }
        return r;
    }
}

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * ���������� � ��������� ����, ����� � ��������.
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
     * ������������ ����������.
     * <p/>
     * @param aType  ���.
     * @param aName  ���.
     * @param aValue ��������.
     */
    public Variable(Class<?> aType, String aName, Object aValue)
    {
        type = aType;
        name = aName;
        value = aValue;
        fields = new ArrayList<>(0x8F);

        // ����� ���� ����� ������, �� ���������� ����� � �������� null.
        if (!type.isPrimitive() && !type.isArray() && !type.equals(String.class) && value != null)
        {
            // ����� ���� ����� ������ � ���� ������������.
            for (Class<?> c = value.getClass(); c != null; c = c.getSuperclass())
            {
                Field[] fs = c.getDeclaredFields();
                AccessibleObject.setAccessible(fs, true);

                // ��������� ���� ������������� �����
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
     * ��������� �������� ����������.
     * <p/>
     * @return ��������
     */
    public Object getValue()
    {
        return value;
    }

    /**
     * ��������� ���� ������������� ����� ��� ����������.
     * <p/>
     * @return ��������� ������ ��� �����.
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

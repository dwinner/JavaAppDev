import java.lang.reflect.*;
import java.util.*;

/**
 * Эта программа демонстрирует использование рефлексии
 * для слежения за объектами.
 * @version 1.11 2004-02-21
 * @author Cay Horstmann
 */
public class ObjectAnalyzerTest
{
    public static void main(String[] args)
    {
        ArrayList<Integer> squares = new ArrayList<Integer>();
        for (int i = 1; i <= 5; i++)
            squares.add(i * i);
        System.out.println(new ObjectAnalyzer().toString(squares));
    }
}

class ObjectAnalyzer
{
    private ArrayList<Object> visited = new ArrayList<Object>();
   
    /**
     * Преобразует объект в строку, в которой перечислены все поля.
     * @param obj Объект
     * @return Строка, в которой указаны имя объекта, имена всех полей и их значения
     * values
     */
    public String toString(Object obj)
    {
        if (obj == null)
            return "null";
        if (visited.contains(obj))
            return "...";
        visited.add(obj);
        Class cl = obj.getClass();
        if (cl == String.class)
            return (String) obj;
        if (cl.isArray())
        {
            String r = cl.getComponentType() + "[]{";
            for (int i = 0; i < Array.getLength(obj); i++)
            {
                if (i > 0)
                    r += ",";
                Object val = Array.get(obj, i);
                r += (cl.getComponentType().isPrimitive()) ? val : toString(val);
            }
            return r + "}";
        }

        String r = cl.getName();
        // Проверка полей класса и всех его суперклассов.
        do
        {
            r += "[";
            Field[] fields = cl.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            // Определение имен и значений всех полей
            for (Field f : fields)
            {
                if (!Modifier.isStatic(f.getModifiers()))
                {
                    if (!r.endsWith("["))
                        r += ",";
                    r += f.getName() + "=";
                    try
                    {
                        Class t = f.getType();
                        Object val = f.get(obj);
                        r += (t.isPrimitive()) ? val : toString(val);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            r += "]";
            cl = cl.getSuperclass();
        }
        while (cl != null);

        return r;
    }
}

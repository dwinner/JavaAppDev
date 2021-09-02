import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JComboBox;

/**
 * Раскрывающийся список, позволяющий пользователям выбирать значения статических полей.
 * <p/>
 * @version 1.13 2007-07-25
 * @author Cay Horstmann
 */
public class EnumCombo extends JComboBox<String>
{
    private Map<String, Integer> table = new TreeMap<>();

    /**
     * Конструктор EnumCombo.
     * <p/>
     * @param cl     Класс
     * @param labels Массив имен статических полей класса cl
     */
    public EnumCombo(Class<?> cl, String[] labels)
    {
        for (int i = 0; i < labels.length; i++)
        {
            String label = labels[i];
            String name = label.toUpperCase().replace(' ', '_');

            int value = 0;
            try
            {
                Field f = cl.getField(name);
                value = f.getInt(cl);
            }
            catch (NoSuchFieldException | SecurityException | IllegalArgumentException |
                   IllegalAccessException multiEx)
            {
                label = "(" + label + ")";
            }
            table.put(label, value);
            addItem(label);
        }
        
        setSelectedItem(labels[0]);
    }

    /**
     * Данный метод возвращает значение поля, выбранного пользователем.
     * <p/>
     * @return Значение статического поля
     */
    public int getValue()
    {
        return table.get(getSelectedItem());
    }
}

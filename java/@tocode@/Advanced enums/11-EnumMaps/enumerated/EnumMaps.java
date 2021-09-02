//: enumerated/EnumMaps.java
// Простое использование EnumMaps.
package enumerated;

import java.util.*;
import static enumerated.AlarmPoints.*;

interface Command { void action(); }

public class EnumMaps
{
    public static void main(String[] args)
    {
        EnumMap<AlarmPoints,Command> em = new EnumMap<AlarmPoints,Command>(AlarmPoints.class);
        em.put(KITCHEN, new Command() { public void action() { System.out.println("Kitchen fire!"); }});
        em.put(BATHROOM, new Command() { public void action() { System.out.println("Bathroom alert!"); }});
        for (Map.Entry<AlarmPoints,Command> e : em.entrySet())
        {
            System.out.println(e.getKey() + ": ");
            e.getValue().action();
        }
        try
        { // Если нет значения для конкретного ключа:
            em.get(UTILITY).action();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
// ---------------------------------------------------------------------------------------
/* Output:
BATHROOM: Bathroom alert!
KITCHEN: Kitchen fire!
java.lang.NullPointerException
*/

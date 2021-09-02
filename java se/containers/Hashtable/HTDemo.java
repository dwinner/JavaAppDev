// Класс Hashtable.
import java.util.*;

public class HTDemo
{
    public static void main(String args[])
    {
        Hashtable<String,Double> balance = new Hashtable<>();
        Enumeration<String> names;
        String str;
        double bal;

        balance.put("John Doe", 3434.34);
        balance.put("Tom Smith", 123.22);
        balance.put("Jane Baker", 1378.00);
        balance.put("Tod Hall", 99.22);
        balance.put("Ralph Smith", -19.08);

        // показать все балансы хэш-таблицы
        names = balance.keys();
        while (names.hasMoreElements())
        {
            str = names.nextElement();
            System.out.println(str + ": " + balance.get(str));
        }

        System.out.println();

        // перечислить 1000 на депозитный счет Джона До (John Doe)
        bal = balance.get("John Doe").doubleValue();
        balance.put("John Doe", new Double(bal + 1000));
        System.out.println("New balance of John Doe: " + balance.get("John Doe"));
    }
}

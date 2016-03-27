// Использование компаратора для сортировки.
import java.util.*;

// сравнить два последних полных слова в двух строках
class TComp implements Comparator<String>
{
    @Override public int compare(String a, String b)
    {
        int i, j, k;
        String aStr, bStr;

        aStr = a;
        bStr = b;

        // найти индекс начала фамилии
        i = aStr.lastIndexOf(' ');
        j = bStr.lastIndexOf(' ');

        k = aStr.substring(i).compareTo(bStr.substring(j));
        return (k == 0) ? aStr.compareTo(bStr) : k;
    }
}

public class TreeMapDemo2
{
    public static void main(String args[])
    {
        // создать древовидную карту
        TreeMap<String,Double> tm = new TreeMap<>(new TComp());

        // поместить элементы в карту
        tm.put("John Doe", 3434.34);
        tm.put("Tom Smith", 123.22);
        tm.put("Jane Baker", 1378.00);
        tm.put("Tod Hall", 99.22);
        tm.put("Ralph Smith", -19.08);

        // получить набор входов
        Set<Map.Entry<String,Double>> set = tm.entrySet();

        // получить итератор
        Iterator<Map.Entry<String,Double>> itr = set.iterator();

        // показать элементы на экране
        while (itr.hasNext())
        {
            Map.Entry<String,Double> me = itr.next();
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }
        System.out.println();

        // Перечислить 1000 на депозитный счет Джона До (John Doe)
        double balance = ((Double) tm.get("John Doe")).doubleValue();
        tm.put("John Doe", new Double(balance + 1000));
        System.out.println("New balance of John Doe: " + tm.get("John Doe"));
    }
}

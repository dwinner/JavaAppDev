// Пример использования заказного компаратора
import java.util.*;

// Обратный компаратор для строк.
class MyComp implements Comparator<String>
{
    @Override public int compare(String a, String b)
    {
        String aStr, bStr;

        aStr = a;
        bStr = b;

        // реверсировать сравнение
        return bStr.compareTo(aStr);
    }
    // нет необходимости переопределять метод equals
}

public class CompDemo
{
    public static void main(String args[])
    {
        // создать древовидный набор
        TreeSet<String> ts = new TreeSet<>(new MyComp());

        // добавить элементы в древовидный набор
        ts.add("C");
        ts.add("A");
        ts.add("B");
        ts.add("E");
        ts.add("F");
        ts.add("D");

        // получить итератор
        Iterator<String> i = ts.iterator();

        // показать элементы на экране
        while (i.hasNext())
        {
            Object element = i.next();
            System.out.print(element + " ");
        }
        System.out.println();
    }
}

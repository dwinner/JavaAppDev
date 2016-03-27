import java.util.*;

/**
 * Программа демонстрирует операции со связными списками.
 * @version 1.10 2004-08-02
 * @author Cay Horstmann
 */
public class LinkedListTest
{
    public static void main(String[] args)
    {
        List<String> a = new LinkedList<String>();
        a.add("Amy");
        a.add("Carl");
        a.add("Erica");

        List<String> b = new LinkedList<String>();
        b.add("Bob");
        b.add("Doug");
        b.add("Frances");
        b.add("Gloria");

        // объединить слова из b в a

        ListIterator<String> aIter = a.listIterator();
        Iterator<String> bIter = b.iterator();

        while (bIter.hasNext())
        {
            if (aIter.hasNext())
                aIter.next();
            aIter.add(bIter.next());
        }

        System.out.println(a);

        // Удалить каждое второе слово из b

        bIter = b.iterator();
        while (bIter.hasNext())
        {
            bIter.next(); // Пропустить один элемент
            if (bIter.hasNext())
            {
                bIter.next(); // Пропускаем следующий элемент
                bIter.remove(); // Удалить этот элемент
            }
        }

        System.out.println(b);

        // Групповая операция: удаление всех слов b из a

        a.removeAll(b);

        System.out.println(a);
    }
}

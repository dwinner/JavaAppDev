
import java.util.*;

/**
 * ��� ��������� ������������� ���������� ������� � ������������.
 *
 * @version 1.00 2004-08-03
 * @author Cay Horstmann
 */
public class PriorityQueueTest
{
    public static void main(String[] args)
    {
        PriorityQueue<GregorianCalendar> pq = new PriorityQueue<>();
        pq.add(new GregorianCalendar(1906, Calendar.DECEMBER, 9)); // G. Hopper
        pq.add(new GregorianCalendar(1815, Calendar.DECEMBER, 10)); // A. Lovelace
        pq.add(new GregorianCalendar(1903, Calendar.DECEMBER, 3)); // J. von Neumann
        pq.add(new GregorianCalendar(1910, Calendar.JUNE, 22)); // K. Zuse

        System.out.println("Iterating over elements...");
        for (GregorianCalendar date : pq)
        {
            System.out.println(date.get(Calendar.YEAR));
        }
        System.out.println("Removing elements...");
        while (!pq.isEmpty())
        {
            System.out.println(pq.remove().get(Calendar.YEAR));
        }
    }
}

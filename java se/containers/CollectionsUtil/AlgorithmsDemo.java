// ��������� ���������.
import java.util.*;

public class AlgorithmsDemo
{
    public static void main(String args[])
    {
        // ������� � ���������������� ��������� ������
        LinkedList<Integer> ll = new LinkedList<>();
        ll.add(-8);
        ll.add(20);
        ll.add(-20);
        ll.add(8);

        // ������� � ������������� ����������
        Comparator<Integer> r = Collections.reverseOrder();

        // ����������� ������, ��������� ����������
        Collections.sort(ll, r);

        // �������� ��������
        Iterator<Integer> li = ll.iterator();

        System.out.print("List, sorted by reverse order: ");
        while (li.hasNext())
        {
            System.out.print(li.next() + " ");
        }
        System.out.println();

        Collections.shuffle(ll);

        // �������� �� ������ ����������������� ������
        li = ll.iterator();
        System.out.print("Randomized list: ");
        while (li.hasNext())
        {
            System.out.print(li.next() + " ");
        }
        System.out.println();

        System.out.println("Minimum: " + Collections.min(ll));
        System.out.println("Maximum: " + Collections.max(ll));
    }
}

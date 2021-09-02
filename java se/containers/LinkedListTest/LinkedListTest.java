import java.util.*;

/**
 * ��������� ������������� �������� �� �������� ��������.
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

        // ���������� ����� �� b � a

        ListIterator<String> aIter = a.listIterator();
        Iterator<String> bIter = b.iterator();

        while (bIter.hasNext())
        {
            if (aIter.hasNext())
                aIter.next();
            aIter.add(bIter.next());
        }

        System.out.println(a);

        // ������� ������ ������ ����� �� b

        bIter = b.iterator();
        while (bIter.hasNext())
        {
            bIter.next(); // ���������� ���� �������
            if (bIter.hasNext())
            {
                bIter.next(); // ���������� ��������� �������
                bIter.remove(); // ������� ���� �������
            }
        }

        System.out.println(b);

        // ��������� ��������: �������� ���� ���� b �� a

        a.removeAll(b);

        System.out.println(a);
    }
}

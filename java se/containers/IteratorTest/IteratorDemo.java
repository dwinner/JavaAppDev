// ���������.
import java.util.*;

public class IteratorDemo
{
    public static void main(String args[])
    {
        // ������� ������ �������
        List<String> al = new ArrayList<>();

        // �������� �������� � ������ �������
        al.add("C");
        al.add("A");
        al.add("E");
        al.add("B");
        al.add("D");
        al.add("F");

        // ������������ �������� ��� ������ ����������� ������� al
        System.out.print("Source contents al: ");
        Iterator<String> itr = al.iterator();
        while (itr.hasNext())
        {
            String element = itr.next();
            System.out.print(element + " ");
        }
        System.out.println();

        // �������������� ����������� �������
        ListIterator<String> litr = al.listIterator();
        while (litr.hasNext())
        {
            Object element = litr.next();
            litr.set(element + "+");
        }

        System.out.print("Modified content of al: ");
        itr = al.iterator();
        while (itr.hasNext())
        {
            Object element = itr.next();
            System.out.print(element + " ");
        }
        System.out.println();

        // ������ �������� ������ � �������� �������
        System.out.print("Modified (back) list: ");
        while (litr.hasPrevious())
        {
            Object element = litr.previous();
            System.out.print(element + " ");
        }
        System.out.println();
    }
}

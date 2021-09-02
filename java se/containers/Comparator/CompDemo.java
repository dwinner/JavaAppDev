// ������ ������������� ��������� �����������
import java.util.*;

// �������� ���������� ��� �����.
class MyComp implements Comparator<String>
{
    @Override public int compare(String a, String b)
    {
        String aStr, bStr;

        aStr = a;
        bStr = b;

        // ������������� ���������
        return bStr.compareTo(aStr);
    }
    // ��� ������������� �������������� ����� equals
}

public class CompDemo
{
    public static void main(String args[])
    {
        // ������� ����������� �����
        TreeSet<String> ts = new TreeSet<>(new MyComp());

        // �������� �������� � ����������� �����
        ts.add("C");
        ts.add("A");
        ts.add("B");
        ts.add("E");
        ts.add("F");
        ts.add("D");

        // �������� ��������
        Iterator<String> i = ts.iterator();

        // �������� �������� �� ������
        while (i.hasNext())
        {
            Object element = i.next();
            System.out.print(element + " ");
        }
        System.out.println();
    }
}

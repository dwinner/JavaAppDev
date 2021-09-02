// ������������� ����������� ��� ����������.
import java.util.*;

// �������� ��� ��������� ������ ����� � ���� �������
class TComp implements Comparator<String>
{
    @Override public int compare(String a, String b)
    {
        int i, j, k;
        String aStr, bStr;

        aStr = a;
        bStr = b;

        // ����� ������ ������ �������
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
        // ������� ����������� �����
        TreeMap<String,Double> tm = new TreeMap<>(new TComp());

        // ��������� �������� � �����
        tm.put("John Doe", 3434.34);
        tm.put("Tom Smith", 123.22);
        tm.put("Jane Baker", 1378.00);
        tm.put("Tod Hall", 99.22);
        tm.put("Ralph Smith", -19.08);

        // �������� ����� ������
        Set<Map.Entry<String,Double>> set = tm.entrySet();

        // �������� ��������
        Iterator<Map.Entry<String,Double>> itr = set.iterator();

        // �������� �������� �� ������
        while (itr.hasNext())
        {
            Map.Entry<String,Double> me = itr.next();
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }
        System.out.println();

        // ����������� 1000 �� ���������� ���� ����� �� (John Doe)
        double balance = ((Double) tm.get("John Doe")).doubleValue();
        tm.put("John Doe", new Double(balance + 1000));
        System.out.println("New balance of John Doe: " + tm.get("John Doe"));
    }
}

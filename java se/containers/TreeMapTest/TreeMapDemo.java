// ����������� �����.
import java.util.*;

public class TreeMapDemo
{
    public static void main(String args[])
    {
        // ������� ����������� �����
        TreeMap<String,Double> tm = new TreeMap<>();

        // ��������� �������� � �����
        tm.put("John Doe", 3434.34);
        tm.put("Tom Smith", 123.22);
        tm.put("Jane Baker", 1378.00);
        tm.put("Tod Hail", 99.22);
        tm.put("Ralph Smith", -19.08);

        // �������� ����� ������
        Set<Map.Entry<String,Double>> set = tm.entrySet();

        // �������� ��������
        Iterator<Map.Entry<String,Double>> i = set.iterator();

        // �������� �������� �� ������
        while (i.hasNext())
        {
            Map.Entry<String,Double> me = i.next();
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }
        System.out.println();

        // ����������� 1000 �� ���������� ���� ����� �� (John Doe)
        double balance = tm.get("John Doe");
        tm.put("John Doe", new Double(balance + 1000));
        System.out.println("New balance of John Doe: " + tm.get("John Doe"));
    }
}

// ���������� ���� � �����, ��������� ������ Date-������.
import java.util.Date;

public class DateDemo
{
    public static void main(String args[])
    {
        // ������� ������ ���� Date.
        Date date = new Date();

        // �������� ���� � ����� � ������� toString().
        System.out.println(date);

        // �������� ����� ����������� � �������� 1 ������ 1970 �. �� GMT
        long msec = date.getTime();
        System.out.println("Milliseconds since Jan. 1, 1970 GMT = " + msec);
    }
}

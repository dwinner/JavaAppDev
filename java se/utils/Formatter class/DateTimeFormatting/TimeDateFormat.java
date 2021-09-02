// �������������� ������� � ����.
import java.util.*;

public class TimeDateFormat
{
    public static void main(String args[])
    {
        Formatter fmt = new Formatter();
        Calendar cal = Calendar.getInstance();

        // ���������� ����������� 12-������� ������.
        fmt.format("%tr", cal);
        System.out.println(fmt);
 
        // ���������� ������ ���������� � ���� � �������.
        fmt = new Formatter();
        fmt.format("%tc", cal);
        System.out.println(fmt);

        // ���������� ������ ���� � ������.
        fmt = new Formatter();
        fmt.format("%tl:%tM", cal, cal);
        System.out.println(fmt);

        // ���������� �������� � ����� ������.
        fmt = new Formatter();
        fmt.format("%tB %tb %tm", cal, cal, cal);
        System.out.println(fmt);
    }
}
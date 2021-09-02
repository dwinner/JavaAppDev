// ������������ ���������� ������������ ��������.
import java.util.*;

class PrecisionDemo
{
    public static void main(String args[])
    {
        Formatter fmt = new Formatter();
        
        // ������ � 4 ����������� ���������.
        fmt.format("%.4f", 123.1234567);
        System.out.println(fmt);
        
        // ������ � 2 ����������� ��������� � 16-���������� ����
        fmt = new Formatter();
        fmt.format("%16.2e", 123.1234567);
        System.out.println(fmt);
        
        // ���������� �������� 15 �������� ������.
        fmt = new Formatter();
        fmt.format("%.15s", "Formatting with Java is now easy.");
        System.out.println(fmt);
    }
}
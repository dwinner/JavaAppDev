// ������������/�������������� ���������� � ��������
// ���������� � ������������� ����������.

public class AutoBox2
{
    // ������� �������� Integer � ������� �������� int

    static int m(Integer v)
    {
        return v;	// �������������� int
    }

    public static void main(String args[])
    {
        // �������� int ������ m() � ���������� ������������� ��������
        // ������� Integer. ����� �������� 100 ������������� �������������
        // � Integer. ������������ �������� ����� ������������� � Integer.
        Integer iOb = m(100);
        System.out.println(iOb);
    }
}

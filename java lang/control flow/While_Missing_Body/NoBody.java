// ���� ����� ����� ���� ������.
public class NoBody
{
    public static void main(String args[])
    {
        int i, j;

        i = 100;
        j = 200;

        // ����� ������� ����� ����� i � j
        while (++i < --j) ;	// � ���� ����� ��� ����

        System.out.println("Average comma is " + i);
    }
}

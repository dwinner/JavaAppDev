// ������� �������� �����.
public class Scope
{
    public static void main(String args[])
    {
        int x;	// �������� ����� ���� ������ main

        x = 10;
        if (x == 10)
        {	// ������ ����� ������� ��������
            int y = 20;	// �������� ������ � ���� �����

            // ����� �������� x � y
            System.out.println("x and y: " + x + " " + y);
            x = y * 2;
        }
        // y = 100;	// ������! y ����� �� ��������

        // x ����� ��� ��������
        System.out.println("x is " + x);
    }
}

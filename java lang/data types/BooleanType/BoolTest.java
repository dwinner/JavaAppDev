// ������ ��������
public class BoolTest
{
    public static void main(String args[])
    {
        boolean b;

        b = false;
        System.out.println("b is " + b);
        b = true;
        System.out.println("b is " + b);

        // ������ �������� ����� ��������� if-����������
        if (b)
        {
            System.out.println("Comparing is executed");
        }

        // ����� �������� ��������� ���� ������ ��������
        System.out.println("10 > 9 is " + (10 > 9));
    }
}

// �������������� �������������� ����� ��� ����������.
class OverloadDemo
{
    void test()
    {
        System.out.println("Parameters are empty");
    }

    // ������������� test � ����� int-�����������.
    void test(int a, int b)
    {
        System.out.println("a and b: " + a + " " + b);
    }

    // ������������� test � double-���������� � ������������ �����.
    void test(double a)
    {
        System.out.println("In test(double) a: " + a);
    }
}

public class Overload
{
    public static void main(String args[])
    {
        OverloadDemo ob = new OverloadDemo();
        int i = 88;

        ob.test();
        ob.test(10, 20);

        ob.test(i);		// ����� ����� ������ test(double)
        ob.test(123.2);	// � ����� ����� ������ test(double)
    }
}

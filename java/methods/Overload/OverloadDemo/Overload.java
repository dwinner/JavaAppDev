// ������������� ������.
class OverloadDemo
{
    void test()
    {
        System.out.println("Parameters are empty");
    }

    // ������������� ����� test � ����� int-����������.
    void test(int a)
    {
        System.out.println("a: " + a);
    }

    // ������������� test � ����� int-�����������.
    void test(int a, int b)
    {
        System.out.println("a and b: " + a + " " + b);
    }

    // ������������� ����� test c double-����������.
    double test(double a)
    {
        System.out.println("Real double precision a: " + a);
        return a * a;
    }
}

class Overload
{
    public static void main(String args[])
    {
        OverloadDemo ob = new OverloadDemo();
        double result;

        // ������� ��� ������ test()
        ob.test();
        ob.test(10);
        ob.test(10, 20);
        result = ob.test(123.2);
        System.out.println("Result of ob.test(123.2): " + result);
    }
}

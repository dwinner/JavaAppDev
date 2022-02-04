// ���������� ������ ��������� � ����������� �����.
public class Bisection
{
    static double f(double x)
    {
        return x * x * x - 3 * x * x + 3;	// ��� ���-�� ������
    }

    public static void main(String args[])
    {
        double a = 0.0, b = 1.5, c, y, eps = 1e-8;
        do
        {
            c = 0.5 * (a + b);
            y = f(c);
            if (Math.abs(y) < eps)
            {
                break;
            }
            // ������ ������. ������� �� �����. ���� �� ������ �������
            // [a;c] ������� ����� ������ �����:
            if (f(a) * y < 0.0)
            {
                b = c;	// ������ ������ �����. ��������� ����� b � ����� �
            }
            else // � ��������� ������
            {
                a = c;
            }
            // ��������� ����� a � ����� �. ����������, ���� ������� [a; b] �� ������ ���
        }
        while (Math.abs(b - a) >= eps);
        System.out.println("x = " + c + ", f(" + c + ") = " + y);
    }
}

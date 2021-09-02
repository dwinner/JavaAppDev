// ���������� ������ ��������� � ���-�����.
public class Bisection2
{
    private static final double EPS = 1e-8;	// ���������
    private double a = 0.0, b = 1.5, root;	// �������� ����

    public double getRoot()
    {	// ����� ������� � �����
        return root;
    }

    private double f(double x)
    {
        return x * x * x - 3 * x * x + 3;	// ��� ���-�� ������
    }

    private void bisect()
    {	// ���������� ���, ����� �������� � ������ ����������
        double y = 0.0;	// ��������� ���������� - �� ����
        do
        {
            root = 0.5 * (a + b);
            y = f(root);
            if (Math.abs(y) < EPS)
            {
                break;
            }
            // ������ ������. ������� �� �����. ���� �� ������ ������� [a; root]
            // ������� ����� ������ �����
            if (f(a) * y < 0.0)
            {
                b = root;
            } // ������ ������ �����. ��������� ����� b � ����� root
            // � ��������� ������
            else
            {
                a = root;
            }
            // ��������� ����� a � ����� root
            // ����������, ���� [a; b] �� ������ ���
        }
        while (Math.abs(b - a) >= EPS);
    }

    public static void main(String args[])
    {
        Bisection2 b2 = new Bisection2();
        b2.bisect();
        System.out.println("x = " + b2.getRoot() + ", f() = " + b2.f(b2.getRoot()));
    }
}

import java.lang.reflect.*;

/**
 * ��� ��������� ������������� ������������� ��������� ��� ������ �������.
 * @version 1.1 2004-02-21
 * @author Cay Horstmann
 */
public class MethodPointerTest
{
    public static void main(String[] args) throws Exception
    {
        // ��������� ���������� �� ������ square � sqrt.
        Method square = MethodPointerTest.class.getMethod("square", double.class);
        Method sqrt = Math.class.getMethod("sqrt", double.class);

        // ����� �������� x � y � ���� �������.

        printTable(1, 10, 10, square);
        printTable(1, 10, 10, sqrt);
    }

    /**
     * ���������� ������� �����
     * @param x �����
     * @return x ������� �����
     */
    public static double square(double x) { return x * x; }

    /**
     * ������� ������� �������� x � y ������.
     * @param from ������ ������� �������� x
     * @param to ������� ������� �������� x
     * @param n ���������� ����� � �������
     * @param f �����, ���������� � ������������ �������� ���� double
     */
    public static void printTable(double from, double to, int n, Method f)
    {
        // ����� �������� ������ � �������� ��������� �������
        System.out.println(f);

        double dx = (to - from) / (n - 1);

        for (double x = from; x <= to; x += dx)
        {
            try
            {
                double y = (Double) f.invoke(null, x);
                System.out.printf("%10.4f | %10.4f%n", x, y);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

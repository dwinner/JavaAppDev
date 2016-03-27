// Нахождение корней уравнения в процедурном стиле.
public class Bisection
{
    static double f(double x)
    {
        return x * x * x - 3 * x * x + 3;	// или что-то другое
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
            // Корень найден. Выходим из цикла. Если на концах отрезка
            // [a;c] функция имеет разные знаки:
            if (f(a) * y < 0.0)
            {
                b = c;	// значит корень здесь. Переносим точку b в точку с
            }
            else // в противном случае
            {
                a = c;
            }
            // Переносим точку a в точку с. Продолжаем, пока отрезок [a; b] не станет мал
        }
        while (Math.abs(b - a) >= eps);
        System.out.println("x = " + c + ", f(" + c + ") = " + y);
    }
}

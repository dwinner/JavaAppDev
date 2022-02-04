// Вычисление корней уравнения в ООП-стиле.
public class Bisection2
{
    private static final double EPS = 1e-8;	// Константа
    private double a = 0.0, b = 1.5, root;	// закрытые поля

    public double getRoot()
    {	// Метод доступа к корню
        return root;
    }

    private double f(double x)
    {
        return x * x * x - 3 * x * x + 3;	// Или что-то другое
    }

    private void bisect()
    {	// Параметров нет, метод работает с полями экземпляра
        double y = 0.0;	// локальная переменная - не поле
        do
        {
            root = 0.5 * (a + b);
            y = f(root);
            if (Math.abs(y) < EPS)
            {
                break;
            }
            // Корень найден. Выходим из цикла. Если на концах отрезка [a; root]
            // функция имеет разные знаки
            if (f(a) * y < 0.0)
            {
                b = root;
            } // Значит корень здесь. Переносим точку b в точку root
            // в противном случае
            else
            {
                a = root;
            }
            // Переносим точку a в точку root
            // Продолжаем, пока [a; b] не станет мал
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

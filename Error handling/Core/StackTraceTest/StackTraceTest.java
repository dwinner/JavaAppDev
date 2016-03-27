import java.util.*;

/**
 * Программа, отображающая результаты трассировки стека для рекурсивного метода.
 * @version 1.01 2004-05-10
 * @author Cay Horstmann
 */
public class StackTraceTest
{
    /**
     * Вычисление факториала
     * @param n Неотрицательное число
     * @return n! = 1 * 2 * . . . * n
     */
    public static int factorial(int n)
    {
        System.out.println("factorial(" + n + "):");
        Throwable t = new Throwable();
        StackTraceElement[] frames = t.getStackTrace();
        for (StackTraceElement f : frames)
            System.out.println(f);
        int r = (n <= 1) ? 1 : n * factorial(n - 1);
        System.out.println("return " + r);
        return r;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = in.nextInt();
        factorial(n);
    }
}

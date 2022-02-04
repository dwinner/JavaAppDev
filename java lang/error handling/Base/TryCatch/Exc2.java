// Контролируемый блок кода.
public class Exc2
{
    public static void main(String args[])
    {
        int d, a;

        try
        {	// контролировать блок кода
            d = 0;
            a = 42 / d;
            System.out.println("Это не будет напечатано.");
        }
        catch (ArithmeticException e)
        {	// перехватить ошибку деления на нуль
            System.out.println("Division by zero.");
        }
        System.out.println("After catch-operator.");
    }
}

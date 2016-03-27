// Динамическая инициализация типов.
public class DynInit
{
    public static void main(String args[])
    {
        double a = 3.0, b = 4.0;

        // переменная с динамически инициализирована
        double c = Math.sqrt(a * a + b * b);

        System.out.println("Gypotenuse is " + c);
    }
}

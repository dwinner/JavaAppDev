// ѕример вложенных try-операторов.
public class NestTry
{
    public static void main(String args[])
    {
        try
        {
            int a = args.length;

            /* ≈сли нет аргументов командной строки,
            следующий оператор будет генерировать
            исключение делени€ на нуль. */
            int b = 42 / a;

            System.out.println("a = " + a);

            try
            {	// вложенный try-блок
                /* ≈сли используетс€ один аргумент командной строки,
                то следующий код будет генерировать
                исключение делени€ на нуль. */
                if (a == 1)
                {
                    a = a / (a - a);	// деление на нуль
                }
                /* ≈сли используетс€ один аргумент командной строки,
                то генерируетс€ исключение выхода за границу массива. */
                if (a == 2)
                {
                    int c[] = {1};
                    c[42] = 99;	// √енерировать исключение выхода за границу массива
                }
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Index is out of array-bounds: " + e);
            }
        }
        catch (ArithmeticException e)
        {
            System.out.println("Division by zero: " + e);
        }
    }
}

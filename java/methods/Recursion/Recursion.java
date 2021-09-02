// Простой пример рекурсии
class Factorial
{
    // это рекурсивная функция

    int fact(int n)
    {
        int result;

        if (n == 1)
        {
            return 1;
        }
        result = fact(n - 1) * n;
        return result;
    }
}

// Другой пример использования рекурсии
class RecTest
{
    int values[];

    RecTest(int i)
    {
        values = new int[i];
    }

    // отобразить массив рекурсивно
    void printArray(int i)
    {
        if (i == 0)
        {
            return;
        }
        else
        {
            printArray(i - 1);
        }
        System.out.println("[" + (i - 1) + "] " + values[i - 1]);
    }
}

public class Recursion
{
    public static void main(String args[])
    {
        Factorial f = new Factorial();

        System.out.println("Factorial of 5 is " + f.fact(5));

        RecTest ob = new RecTest(10);
        int i;

        for (i = 0; i < 10; i++)
        {
            ob.values[i] = i;
        }

        ob.printArray(10);
    }
}

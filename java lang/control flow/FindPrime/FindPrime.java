// Проверка на принадлежность к категории простых чисел.
public class FindPrime
{
    public static void main(String args[])
    {
        int num;
        boolean isPrime = true;
        num = 14;
        String finalResult;
        for (int i = 2; i < num / 2; i++)
        {
            if ((num % 2) == 0)
            {
                isPrime = false;
                break;
            }
        }
        finalResult = (isPrime) ? "Simple number" : "Not a simple number";
        System.out.println(finalResult);
    }
}

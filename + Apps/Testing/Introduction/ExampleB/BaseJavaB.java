import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Фильтрация простых чисел
 * @author dwinner@inbox.ru
 */
public class BaseJavaB
{
    private static final Logger LOG = Logger.getLogger(BaseJavaB.class.getName());
    private static final int DEFAULT_DIMENSION = 10;
    private static final int UPPER_INT = 100;
    
    private static int[] inputArray;
    private static Scanner scanIn;
    
    static
    {
        scanIn = new Scanner(System.in);
    }
    
    private BaseJavaB() {}
    
    /**
     * Точка запуска
     * @param args the command line arguments
     */
    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    public static void main(String[] args)
    {
        // Получить размер массива
        System.out.print("Enter a number of array dimension: ");
        int arDim;
        try
        {
            arDim = scanIn.nextInt();
        }
        catch (InputMismatchException imEx)
        {
            System.out.println("Your enter is not an integer value.");
            System.out.println("We will use default dimension: " + DEFAULT_DIMENSION);
            arDim = DEFAULT_DIMENSION;
        }
        
        // Выделяем память для массива
        inputArray = new int[arDim];
        
        // Заполняем массив
        System.out.println("Enter array elements");
        for (int i = 0, nextInt; i < inputArray.length; i++)
        {
            System.out.print("Element at " + i + " :\t");
            scanIn = new Scanner(System.in);
            try
            {
                nextInt = scanIn.nextInt();
            }
            catch (InputMismatchException imEx)
            {
                nextInt = (int) (UPPER_INT * Math.random());
                System.out.println("So we will use a random integer number: " + nextInt);
            }
            inputArray[i] = nextInt;
        }
        
        // Выводим массив
        System.out.print("You will enter: ");
        for (int aValue : inputArray)
        {
            System.out.print(aValue + " ");
        }
        System.out.println();
        
        // Выводим только простые числа
        System.out.println("Now, we put to the output stream only prime numbers: ");
        for (int aValue : inputArray)
        {
            if (isPrime(aValue))
            {
                System.out.print(aValue + " ");
            }
        }
    }
    
    /**
     * Метод проверки, является ли число простым
     * @param aValue Число для проверки
     * @return true, если число является простым, false в противном случае
     */
    private static boolean isPrime(int aValue)
    {
        for (int j = 2; j < aValue; j++)
        {
            if ((aValue % j) != 0)
                continue;
            else
                return false;
        }
        return true;
    }

}

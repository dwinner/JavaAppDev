import java.util.logging.Logger;

/**
 * Утильный класс для некоторых операций с массивами
 * @author dwinner@inbox.ru
 */
public class EvalValues
{    
    private static final Logger LOG = Logger.getLogger(EvalValues.class.getName());
    
    private EvalValues() {}
    
    /**
     * Вычисление суммы элементов массива целых
     * @param anArray Source Array
     * @return Сумма элементов
     */
    public static int sum(int[] anArray)
    {
        int total = 0;
        for (int i = 0; i < anArray.length; i++)
		{
            total += anArray[i];
        }
        return total;
    }
    
    /**
     * Вычисление произведения элементов массива целых
     * @param anArray Source Array
     * @return Произведение элементов
     */
    public static int mul(int[] anArray)
    {
        int total = 1;
        for (int i = 0; i < anArray.length; i++)
        {
            total *= anArray[i];
        }
        return total;
    }
    
}

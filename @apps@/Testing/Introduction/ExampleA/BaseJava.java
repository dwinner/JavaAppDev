import java.io.IOException;
import java.util.logging.Logger;

/**
 * Подсчет суммы и произведения чисел в аргументах командной строки
 * @author dwinner@inbox.ru
 */
public class BaseJava
{    
    private static int[] intArgs;
    private static final Logger LOG = Logger.getLogger(BaseJava.class.getName());
    
    private BaseJava(){}

    /**
     * @param args the command line arguments
     * @throws IOException 
     */
    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    public static void main(String[] args) throws IOException
    {
        // Проверяем, переданы ли аргументы командной строки
        if (args.length == 0)
        {
            System.out.println("Usage: java intNumber1 intNumber2,... intNumberN");
            System.out.print("Press Enter key to continue...");
            System.in.read();
            System.exit(0);
        }
        
        // Отлично, аргументы переданы, теперь можно выделить память для массива
        // ... и инициализировать его
        intArgs = new int[args.length];
        for (int i = 0; i < args.length; i++)
        {
            try
            {
                intArgs[i] = Integer.parseInt(args[i]);
            }
            catch (NumberFormatException nfe)
            {
                intArgs[i] = 0;
            }
        }
        
        // Подсчитываем сумму и произведение
        System.out.print("Source array of cmd args: ");
        for (int anArg : intArgs)
        {
            System.out.print(anArg + " ");
        }
        System.out.println();
        
        System.out.println("Summary of elements: " + EvalValues.sum(intArgs));
        System.out.println("Multiply of elements: " + EvalValues.mul(intArgs));
    }
    
}

// Ошибка недостижимости кода:
// В jdk1.5+ у Вас даже скомпилировать не получится.
public class SuperSubCatch
{
    public static void main(String args[])
    {
        try
        {
            int a = 0;
            int b = 42 / a;
        }
        catch (Exception e)
        {
            System.out.println("Generating catch-exception.");
        }
        /* Этот catch никогда не будет достигнут из-за того, что
        ArithmeticException является подклассом Exception. */
        catch (ArithmeticException e)
        {	// ОШИБКА. Оператор недостижим
            System.out.println("Unattainabled operator.");
        }
    }
}

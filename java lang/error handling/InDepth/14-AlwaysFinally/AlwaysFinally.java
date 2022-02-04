// finally выполняется всегда

class FourException extends Exception { }

public class AlwaysFinally
{
    public static void main(String[] args)
    {
        System.out.println("Enter the first checked block");
        try
        {
            System.out.println("Enter the second checked block");
            try
            {
                throw new FourException();
            }
            finally
            {
                System.out.println("finally in second checked block");
            }
        }
        catch (FourException e)
        {
            System.out.println("Catched FourException in first checked block");
        }
        finally
        {
            System.out.println("finally in first checked block");
        }
    }
}
// Блок finally выполняется всегда

class ThreeException extends Exception { }

public class FinallyWorks
{
    static int count = 0;
    
    public static void main(String[] args)
    {
        while (true)
        {
            try
            {
                // Операция постфиксного приращения, в первый раз 0:
                if (count++ == 0)
                {
                    throw new ThreeException();
                }
                System.out.println("No exception");
            }
            catch (Exception e)
            {
                System.out.println("ThreeException");
            }
            finally
            {
                System.out.println("In block finally");
                if (count == 2)
                {   //  Вне цикла "while"
                    break;
                }
            }
        }
    }
}
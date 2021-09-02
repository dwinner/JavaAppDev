// Повторное возбуждение объекта, отличающегося от первоначального

class OneException extends Exception
{
    OneException(String s)
    {
        super(s);
    }   
}

class TwoException extends Exception
{
    TwoException(String s)
    {
        super(s);
    }   
}

public class RethrowNew
{
    public static void f()
        throws OneException
    {
        System.out.println("Creating exception in f()");
        throw new OneException("From f()");
    }
    
    public static void main(String[] args)
    {
        try
        {
            try
            {
                f();
            }
            catch (OneException e)
            {
                System.out.println("In inner checked block try: e.printStackTrace(): ");
                e.printStackTrace(System.out);
                throw new TwoException("From checked inner block try");
            }
        }
        catch (TwoException e)
        {
            System.out.println("In outer checked block try: e.printStackTrace(): ");
            e.printStackTrace(System.out);
        }
    }
}
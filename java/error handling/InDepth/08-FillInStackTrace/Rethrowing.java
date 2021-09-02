// Демонстрация метода fillInStackTrace()

public class Rethrowing
{
    public static void f()
        throws Exception
    {
        System.out.println("Creating exception in f()");
        throw new Exception("Raised from f()");
    }
    
    public static void g()
        throws Exception
    {
        try
        {
            f();
        }
        catch (Exception e)
        {
            System.out.println("In method g(), e.printStackTrace()");
            e.printStackTrace(System.out);
            throw e;
        }
    }
    
    public static void h()
        throws Exception
    {
        try
        {
            f();
        }
        catch (Exception e)
        {
            System.out.println("In method h(), e.printStackTrace()");
            e.printStackTrace();
            throw (Exception) e.fillInStackTrace();
        }
    }
    
    public static void main(String[] args)
    {
        try
        {
            g();
        }
        catch (Exception e)
        {
            System.out.println("main: printStackTrace()");
            e.printStackTrace(System.out);
        }
        try
        {
            h();
        }
        catch (Exception e)
        {
            System.out.println("main: printStackTrace()");
            e.printStackTrace(System.out);
        }
    }
}
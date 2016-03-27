// Создание собственного исключения

class SimpleException extends Exception { }

public class InheritingExceptions
{
    public void f()
        throws SimpleException
    {
        System.out.println("Raising SimpleException from f()");
        throw new SimpleException();
    }
    
    public static void main(String[] args)
    {
        InheritingExceptions sed = new InheritingExceptions();
        try
        {
            sed.f();
        }
        catch (SimpleException e)
        {
            System.out.println("Catched!");
        }
    }
}

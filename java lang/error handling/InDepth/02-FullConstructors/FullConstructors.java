// ���������� � ����� �������������� : FullConstructors.java

class MyException extends Exception
{
    public MyException() { }
    public MyException(String msg) { super(msg); }
}

public class FullConstructors
{
    public static void f()
        throws MyException
    {
        System.out.println("Raising exception from f()");
        throw new MyException();
    }
    
    public static void g()
        throws MyException
    {
        System.out.println("Raising MyException from g()");
        throw new MyException("Created in g()");
    }
    
    public static void main(String[] args)
    {
        try
        {
            f();
        }
        catch (MyException e)
        {
           e.printStackTrace(System.err);
        }
        try
        {
            g();
        }
        catch (MyException e)
        {
            e.printStackTrace(System.err);
        }
    }
}
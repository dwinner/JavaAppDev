// Дальнейшее расширение классов исключений.

class MyException2 extends Exception
{
    private int x;
    
    public MyException2() { }
    public MyException2(String msg) { super(msg); }
    public MyException2(String msg, int x)
    {
        super(msg);
        this.x = x;
    }
    
    public int val() { return x; }
    
    @Override public String getMessage()
    {
        return "Detailed message: " + x + " " + super.getMessage();
    }
}

public class ExtraFeatures
{
    public static void f()
        throws MyException2
    {
        System.out.println("MyException2 in f()");
        throw new MyException2();
    }
    
    public static void g()
        throws MyException2
    {
        System.out.println("MyException2 in g()");
        throw new MyException2("Raised in g()");
    }
    
    public static void h()
        throws MyException2
    {
        System.out.println("MyException2 in h()");
        throw new MyException2("Raised in h()", 47);
    }
    
    public static void main(String[] args)
    {
        try { f(); }
        catch (MyException2 e) { e.printStackTrace(System.out); }
        try { g(); }
        catch (MyException2 e) { e.printStackTrace(System.out); }
        
        try
        {
            h();
        }
        catch (MyException2 e)
        {
            e.printStackTrace(System.out);
            System.out.print("e.val() = " + e.val());
        }
    }
}
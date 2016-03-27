// finally гарантирует выполнение завершающего кода.

class Switch
{
    private boolean state = false;
    
    public boolean read()
    {
        return state;
    }
    
    public void on()
    {
        state = true;
        System.out.println(this);
    }
    
    public void off()
    {
        state = false;
        System.out.println(this);
    }
    
    @Override public String toString()
    {
        return state ? "on" : "off";
    }
}

class OnOffException1 extends Exception { }
class OnOffException2 extends Exception { }

public class OnOffSwitch
{
    static Switch sw = new Switch();
    
    static void f()
        throws OnOffException1, OnOffException2 { }
    
    public static void main(String[] args)
    {
        try
        {
            sw.on();
            //  од, способный возбуждать исключени€.
            OnOffSwitch.f();
        }
        catch (OnOffException1 e)
        {
            System.out.println("OnOffException1");
        }
        catch (OnOffException2 e)
        {
            System.out.println("OnOffException2");
        }
        finally
        {
            sw.off();
        }
    }
}
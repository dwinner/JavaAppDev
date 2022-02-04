// Замыкания и обратные вызовы.

interface Incrementable
{
    void increment();
}

// Простая реализация интерфейса.
class Callee1 implements Incrementable
{
    private int i = 0;

    public void increment()
    {
        i++;
        System.out.println(i);
    }
}

class MyIncrement
{
    protected void increment()
    {
        System.out.println("Other operation");
    }

    public static void f(MyIncrement mi)
    {
        mi.increment();
    }
}

// Если класс должен вызывать метод increment()
// по-другому, необходимо использовать внутренний класс.
class Callee2 extends MyIncrement
{
    private int i = 0;

    @Override public void increment()
    {
        super.increment();
        i++;
        System.out.println(i);
    }

    private class Closure implements Incrementable
    {
        public void increment()
        {
            // Указывается метод внешнего класса,
            // в противном случае возникает бесконечная рекурсия.
            Callee2.this.increment();
        }
    }

    Incrementable getCallbackReference()
    {
        return new Closure();
    }
}

class Caller
{
    private Incrementable callbackReference;

    Caller(Incrementable callbackReference)
    {
        this.callbackReference = callbackReference;
    }

    void go()
    {
        callbackReference.increment();
    }
}

public class Callbacks
{
    public static void main(String[] args)
    {
        Callee1 c1 = new Callee1();
        Callee2 c2 = new Callee2();
        MyIncrement.f(c2);
        Caller caller1 = new Caller(c1);
        Caller caller2 = new Caller(c2.getCallbackReference());
        caller1.go();
        caller2.go();
        caller2.go();
        caller2.go();
    }
}

// Пример взаимной блокировки.
class A
{
    synchronized void foo(B b)
    {
        String name = Thread.currentThread().getName();

        System.out.println(name + " entered in A.foo");

        try
        {
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            System.out.println("A is interrupted");
        }

        System.out.println(name + " try to call B.last()");
        b.last();
    }

    synchronized void last()
    {
        System.out.println("In method A.last");
    }
}

class B
{
    synchronized void bar(A a)
    {
        String name = Thread.currentThread().getName();

        System.out.println(name + " entered in B.bar");

        try
        {
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            System.out.println("B is interrupted");
        }

        System.out.println(name + " try to call A.last()");
        a.last();
    }

    synchronized void last()
    {
        System.out.println("In method A.last");
    }
}

class Deadlock implements Runnable
{
    A a = new A();
    B b = new B();

    Deadlock()
    {
        Thread.currentThread().setName("MainThread");
        Thread t = new Thread(this, "RacingThread");
        t.start();

        a.foo(b);	// получить блокировку на a в этом потоке
        System.out.println("Return to main thread");
    }

    public void run()
    {
        b.bar(a);	// получить блокировку на b в другом потоке
        System.out.println("Return to other thread");
    }

    public static void main(String args[])
    {
        new Deadlock();
    }
}

// Динамическая диспетчеризация методов.
class A
{
    void callme() { System.out.println("In method A"); }
}

class B extends A
{
    @Override void callme()
    {   // переопределить callme()
        System.out.println("In method B");
    }
}

class C extends A
{
    @Override void callme()
    { // переопределить callme()
        System.out.println("In method C");
    }
}

public class Dispatch
{
    public static void main(String args[])
    {
        A a = new A();	// объект типа A
        B b = new B();	// объект типа B
        C c = new C();	// объект типа C
        A r;			// определить ссылку типа A

        r = a;			// r на A-объект
        r.callme();		// вызывает A-версию типа callme

        r = b;			// r на B-объект
        r.callme();		// вызывает B-версию типа callme

        r = c;			// r на C-объект
        r.callme();		// вызывает C-версию типа callme
    }
}

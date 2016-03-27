// Демонстрирует операцию instanceof.
class A { int i, j; }

class B { int i, j; }

class C extends A { int k; }

class D extends A { int k; }

public class InstanceOf
{
    public static void main(String args[])
    {
        A a = new A();
        B b = new B();
        C c = new C();
        D d = new D();

        if (a instanceof A)
        {
            System.out.println("a is an object of class A");
        }
        if (b instanceof B)
        {
            System.out.println("b is an object of class B");
        }
        if (c instanceof C)
        {
            System.out.println("c is an object of class C");
        }
        if (c instanceof A)
        {
            System.out.println("c may be casted to A in run time");
        }

        if (a instanceof C) // false!
        {
            System.out.println("a may be casted to C in run time");
        }

        System.out.println();

        // сравнить производные типы
        A ob;

        ob = d;	// ссылка на d
        System.out.println("ob may be referenced to d");
        if (ob instanceof D)
        {
            System.out.println("ob now references to D-class object");
        }

        System.out.println();

        ob = c;	// ссылка на c
        System.out.println("ob now references to c-object");

        if (ob instanceof D) // false
        {
            System.out.println("type ob may be casted to D-class type");
        }
        else
        {
            System.out.println("type ob may not be casted to D-class type");
        }

        if (ob instanceof A)
        {
            System.out.println("type ob may be casted to A-class type");
        }

        System.out.println();

        // все объекты можно привести к типу Object
        if (a instanceof Object)
        {
            System.out.println("type a may be casted to Object-type");
        }
        if (b instanceof Object)
        {
            System.out.println("type b may be casted to Object-type");
        }
        if (c instanceof Object)
        {
            System.out.println("type c may be casted to Object-type");
        }
        if (d instanceof Object)
        {
            System.out.println("type d may be casted to Object-type");
        }
    }
}

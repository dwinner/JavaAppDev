// Создание конструктора для безымянного внутреннего класса.

abstract class Base
{
    Base(int i)
    {
        System.out.println("Base constructor, i = " + i);
    }

    public abstract void f();
}

public class AnonymousConstructor
{
    public static Base getBase(int i)
    {
        return new Base(i)
        {
            {	// Блок инициализации
                System.out.println("Object init");
            }

            public void f()
            {
                System.out.println("Noname f()");
            }
        };
    }

    public static void main(String[] args)
    {
        Base base = getBase(47);
        base.f();
    }
}
/*
 * Output:
 * Base constructor, i = 47
 * Object init
 * Noname f()
 */
// Передача объектов в метод.
class Test
{
    int a, b;

    Test(int i, int j)
    {
        a = i;
        b = j;
    }

    // Передать объект
    void meth(Test o)
    {
        o.a *= 2;
        o.b /= 2;
    }
}

public class CallByRef
{
    public static void main(String args[])
    {
        Test ob = new Test(15, 20);

        System.out.println("ob.a and ob.b before calling: " + ob.a + " " + ob.b);

        ob.meth(ob);

        System.out.println("ob.a and ob.b after calling: " + ob.a + " " + ob.b);
    }
}

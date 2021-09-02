// Перегруженные методы.
class OverloadDemo
{
    void test()
    {
        System.out.println("Parameters are empty");
    }

    // Перегруженный метод test с одним int-параметром.
    void test(int a)
    {
        System.out.println("a: " + a);
    }

    // Перегруженный test с двумя int-параметрами.
    void test(int a, int b)
    {
        System.out.println("a and b: " + a + " " + b);
    }

    // Перегруженный метод test c double-параметром.
    double test(double a)
    {
        System.out.println("Real double precision a: " + a);
        return a * a;
    }
}

class Overload
{
    public static void main(String args[])
    {
        OverloadDemo ob = new OverloadDemo();
        double result;

        // Вызвать все версии test()
        ob.test();
        ob.test(10);
        ob.test(10, 20);
        result = ob.test(123.2);
        System.out.println("Result of ob.test(123.2): " + result);
    }
}

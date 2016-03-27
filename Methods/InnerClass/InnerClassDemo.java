// Внутренний класс.
class Outer
{
    int outer_x = 100;

    void test()
    {
        Inner inner = new Inner();
        inner.display();
    }

    // это внутренний класс
    class Inner
    {
        void display()
        {
            System.out.println("In method display: outer_x = " + outer_x);
        }
    }
}

public class InnerClassDemo
{
    public static void main(String args[])
    {
        Outer outer = new Outer();
        outer.test();
    }
}

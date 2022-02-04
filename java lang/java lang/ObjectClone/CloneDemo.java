// Демонстрирует метод clone().
class TestClone implements Cloneable
{
    protected int a;
    protected double b;

    // Этот метод вызывает метод clone() класса Object.
    TestClone cloneTest()
    {
        try
        {
            // вызвать клон класса Object.
            return (TestClone) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            System.out.println("Object cloning is not supported.");
            return this;
        }
    }
}

public class CloneDemo
{
    public static void main(String args[])
    {
        TestClone x1 = new TestClone();
        TestClone x2;

        x1.a = 10;
        x1.b = 20.98;

        x2 = x1.cloneTest();	// clone x1

        System.out.println("x1: " + x1.a + " " + x1.b);
        System.out.println("x2: " + x2.a + " " + x2.b);
    }
}

// ��������������� ������ clone().
class TestClone implements Cloneable
{
    protected int a;
    protected double b;

    // clone() ������������ ��� public.
    @Override public Object clone()
    {
        try
        {
            // ����� ������ clone() ������ Object.
            return super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            System.out.println("Object clonning is not supported.");
            return this;
        }
    }
}

public class CloneDemo2
{
    public static void main(String args[])
    {
        TestClone x1 = new TestClone();
        TestClone x2;

        x1.a = 10;
        x1.b = 20.98;

        // ����� clone() ���������� ����� (�� �� ������������� ������)
        x2 = (TestClone) x1.clone();

        System.out.println("x1: " + x1.a + " " + x1.b);
        System.out.println("x2: " + x2.a + " " + x2.b);
    }
}

// ������������� ���������� ������� ����������.
class X
{
    int a;
    float b;
}

class Y extends X
{
    double c;
}

public class RTTI
{
    public static void main(String args[])
    {
        X x = new X();
        Y y = new Y();
        Class clObj;

        clObj = x.getClass();	// �������� ������ �� Class-������
        System.out.println("x is an object of type: " + clObj.getName());
        clObj = y.getClass();	// �������� ������ �� Class-������
        System.out.println("y is an object of type: " + clObj.getName());
        clObj = clObj.getSuperclass();
        System.out.println("Superclass for y is: " + clObj.getName());
    }
}

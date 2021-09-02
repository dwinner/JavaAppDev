// ������������ ��������������� �������.
class A
{
    void callme() { System.out.println("In method A"); }
}

class B extends A
{
    @Override void callme()
    {   // �������������� callme()
        System.out.println("In method B");
    }
}

class C extends A
{
    @Override void callme()
    { // �������������� callme()
        System.out.println("In method C");
    }
}

public class Dispatch
{
    public static void main(String args[])
    {
        A a = new A();	// ������ ���� A
        B b = new B();	// ������ ���� B
        C c = new C();	// ������ ���� C
        A r;			// ���������� ������ ���� A

        r = a;			// r �� A-������
        r.callme();		// �������� A-������ ���� callme

        r = b;			// r �� B-������
        r.callme();		// �������� B-������ ���� callme

        r = c;			// r �� C-������
        r.callme();		// �������� C-������ ���� callme
    }
}

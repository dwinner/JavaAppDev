// ���������� �����������.
interface A
{
    void meth1();
    void meth2();
}

// B ������ �������� meth1() � meth2(), � ��� �� ��������� meth3().
interface B extends A
{
    void meth3();
}

// ���� ����� ������ ����������� ��� ������ �� A � B.
class MyClass implements B
{  
    public void meth1() { System.out.println("meth1() realization."); }
  
    public void meth2() { System.out.println("meth2() realization."); }
  
    public void meth3() { System.out.println("meth3() realization."); }
}

public class IFExtend
{
    public static void main(String args[])
    {
        MyClass ob = new MyClass();
  	
        ob.meth1();
        ob.meth2();
        ob.meth3();
    }
}

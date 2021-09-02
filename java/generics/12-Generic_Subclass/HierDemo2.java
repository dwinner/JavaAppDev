// ������������ ����� ����� ���� ������������
// ��� ����������� ���������. ������������ �����.
class NonGen
{
    private int num;
    NonGen(int i) { num = i; }
    int getnum() { return num; }
}

// ���������� ��������.
class Gen<T> extends NonGen
{
    private T ob; // ���������� ������� ���� T

    Gen(T o, int i)
    {   // �������� ������������ ������ ���� T.
        super(i);
        ob = o;
    }

    T getob() { return ob; }    // ���������� ob.
}

// ������� ������ Gen.
public class HierDemo2
{
    public static void main(String args[])
    {
        // ������� ������ Gen ��� String.
        Gen<String> w = new Gen<>("Hello", 47);
        System.out.print(w.getob() + " ");
        System.out.println(w.getnum());
    }
}
// ������� ��������� ����� � ����� ������-�����������: T � V.
class TwoGen<T, V>
{
    private T ob1;
    private V ob2;
    
    // �������� ������������ ������ �� ������ ���� T � ������ ���� V.
    TwoGen(T o1, V o2)
    {
        ob1 = o1;
        ob2 = o2;
    }
    // �������� ���� T � V.

    void showTypes()
    {
        System.out.println("Type T: " + ob1.getClass().getName());
        System.out.println("Type V: " + ob2.getClass().getName());
    }

    T getob1() { return ob1; }
    V getob2() { return ob2; }
}

// ������������ TwoGen.
public class SimpGen
{
    public static void main(String args[])
    {
        TwoGen<Integer, String> tgObj = new TwoGen<>(88, "Gererics");
        // �������� ����
        tgObj.showTypes();
        // �������� � �������� ��������.
        int v = tgObj.getob1();
        System.out.println("Value: " + v);
        String str = tgObj.getob2();
        System.out.println("Value: " + str);
    }
}

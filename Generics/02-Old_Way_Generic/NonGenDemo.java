// NonGen - �������������� ���������� Gen, �� ������������ ���������.
class NonGen
{
    private Object ob;	// ob ������ ����� ��� Object
    // �������� ������������ ������ �� ������ ���� Object

    NonGen(Object o) { ob = o; }
    // ������� ��� Object.

    Object getob() { return ob; }
    // �������� ��� ob.

    void showType() { System.out.println("Type ob is " + ob.getClass().getName()); }
}
// ������������ ������������� ������.
public class NonGenDemo
{
    public static void main(String args[])
    {
        NonGen iOb;
        // ������� ������ NonGen � ��������� Integer � ���. ������������ ������������
        iOb = new NonGen(88);
        // �������� ��� ������, ������������ iOb.
        iOb.showType();
        // �������� �������� iOb. �� ���� ��� ���������� ����������.
        int v = (Integer) iOb.getob();
        System.out.println("Value: " + v);
        System.out.println();
        // ������� ������ ������ NonGen � ��������� � ��� String.
        NonGen strOb = new NonGen("Test without generics");
        // �������� ��� ������, ������������ strOb.
        strOb.showType();
        // �������� �������� strOb. ����� �� - ���������� ����������.
        String str = (String) strOb.getob();
        System.out.println("Value: " + str);
        // ��� �������������, �� ������������� �������!
        iOb = strOb;
        v = (Integer) iOb.getob();	// ������ ������� ����������!
    }
}

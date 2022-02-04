// ������������ raw-����.
class Gen<T>
{
    final T ob; // ���������� ���� T

    // �������� ������������ ������ �� ������ ���� T.
    Gen(T o) { ob = o; }

    // ������� ob.
    T getob() { return ob; }
}

// ������������ raw-����.
public class RawDemo
{
    @SuppressWarnings("unchecked")
    public static void main(String args[])
    {
        // ������� ������ Gen ��� Integer.
        Gen<Integer> iOb = new Gen<>(88);

        // ������� ������ Gen ��� String.
        Gen<String> strOb = new Gen<>("Generics Test");

        // ������� ������ raw-���� Gen � ���� ��� �������� Double.
        @SuppressWarnings("unchecked")
        Gen raw = new Gen(new Double(98.6));

        // ���������� ����������, ��������� ��� ����������.
        double d = (Double) raw.getob();
        System.out.println("value: " + d);

        // ������������� raw-����� ����� ������� ����������
        // ������� ����������. ���� ������������ ��������� �������.

        // ��������� ���������� ������� ������ ������� ����������!
        //    int i = (Integer) raw.getob(); // ������ ������� ����������.

        // ��� ������������ �������� ������������ �����.
        strOb = raw; // OK, �� ������������ �������
        //    String str = strOb.getob(); // ������ ������� ����������.

        // ��� ������������ ����� �������� ������������ �����.
        raw = iOb; // OK, �� ������������ �������
        //    d = (Double) raw.getob(); // ������ ������� ����������.
    }
}
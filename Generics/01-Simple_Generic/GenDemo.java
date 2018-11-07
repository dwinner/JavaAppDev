/* ������� ���������� �����.
 ����� T - ��� �������� ����,
 ������� ����� ������� �������� �����
 ��� �������� ������� ���� Gen. */

class Gen<T>
{
    private T ob; // ���������� ���� T

    // �������� ������������ ������ �� ������ ���� T.
    Gen(T o) { ob = o; }

    // ������� ob.
    T getob() { return ob; }

    // �������� ��� T.
    void showType() { System.out.println("Type of T is " + ob.getClass().getName()); }
}

// ������������ ����������� ������.
public class GenDemo
{
    public static void main(String args[])
    {
        // ������� Gen-������ ��� Integers.
        Gen<Integer> iOb;

        /*
         * ������� ������ Gen<Integer> � ��������� ������ �� iOb. �������� ����������
         * ������������ ��� ������������ �������� 88 � ������ Integer.
         */
        iOb = new Gen<>(88);

        // �������� ��� ������, ������������ iOb.
        iOb.showType();

        /*
         * �������� �������� iOb. �������� ��������, ��� �������� ���������� �� �����.
         */
        int v = iOb.getob();
        System.out.println("value: " + v);
        System.out.println();

        // ������� ������ Gen ��� String.
        Gen<String> strOb = new Gen<>("Generics Test");

        // �������� ��� ������, ������������ strOb.
        strOb.showType();

        /*
         * �������� �������� strOb. ����� �� ���������� �� ���������
         */
        String str = strOb.getob();
        System.out.println("value: " + str);
    }
}
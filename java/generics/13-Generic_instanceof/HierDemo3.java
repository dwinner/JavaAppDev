// ������������� �������� instanceof � ��������� ��������� �������.
class Gen<T>
{
    private T ob;
    Gen(T o) { ob = o; }
    T getob() { return ob; }    // ���������� ob.
}

// �������� Gen.
class Gen2<T> extends Gen<T>
{
    Gen2(T o) { super(o); }
}

/*
 * ������������ ����������� �������������� ���� � �������� ��������� �������.
 */
public class HierDemo3
{
    public static void main(String args[])
    {
        // ������� ������ Gen ��� Integer.
        Gen<Integer> iOb = new Gen<>(88);

        // ������� ������ Gen2 ��� Integer
        Gen2<Integer> iOb2 = new Gen2<>(99);

        // ������� ������ Gen2 ��� String.
        Gen2<String> strOb2 = new Gen2<>("Generics Test");

        // ���������, �������� �� iOb2 �����-�� �� ���� Gen2.
        if (iOb2 instanceof Gen2<?>)
        {
            System.out.println("iOb2 is instance of Gen2");
        }

        // ���������, �������� �� iOb2 �����-�� �� ���� Gen
        if (iOb2 instanceof Gen<?>)
        {
            System.out.println("iOb2 is instance of Gen");
        }

        System.out.println();

        // ���������, �������� �� strOb2 �������� Gen2.
        if (strOb2 instanceof Gen2<?>)
        {
            System.out.println("strOb is instance of Gen2");
        }

        // ���������, �������� �� strOb2 �������� Gen
        if (strOb2 instanceof Gen<?>)
        {
            System.out.println("strOb is instance of Gen");
        }

        System.out.println();

        // ���������, �������� �� iOb ����������� Gen2, ��� �� ���
        if (iOb instanceof Gen2<?>)
        {
            System.out.println("iOb is instance of Gen2");
        }

        // ���������, �������� �� iOb ����������� Gen, ��� ��� � ����.
        if (iOb instanceof Gen<?>)
        {
            System.out.println("iOb is instance of Gen");
        }

        // ��������� �� ��������������, ������ ��� ����������
        // �� ���������� ���� �� ����� ���������� �����������
		/*
         * if (iOb2 instanceof Gen2<Integer>) System.out.println("iOb2 is instance of
         * Gen2<Integer>");
         */
    }
}
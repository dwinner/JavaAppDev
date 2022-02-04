// ��������������� ����������� ������ � ���������� ������.
class Gen<T>
{
    private T ob;
    Gen(T o) { ob = o; }

    T getob()
    {
        System.out.print("Gen's getob(): ");
        return ob;
    }
}

// �������� Gen, ���������������� getob().
class Gen2<T> extends Gen<T>
{
    Gen2(T o) { super(o); }

    // ��������������� getob().
    @Override T getob()
    {
        System.out.print("Gen2's getob(): ");
        return super.getob();
    }
}

// ������������ ��������������� ���������� �������.
public class OverrideDemo
{
    public static void main(String args[])
    {
        // �������� ������� Gen ��� Integer.
        Gen<Integer> iOb = new Gen<>(88);

        // �������� ������� Gen2 ��� Integer.
        Gen2<Integer> iOb2 = new Gen2<>(99);

        // �������� ������� Gen2 ��� String.
        Gen2<String> strOb2 = new Gen2<>("Generics Test");

        System.out.println(iOb.getob());
        System.out.println(iOb2.getob());
        System.out.println(strOb2.getob());
    }
}
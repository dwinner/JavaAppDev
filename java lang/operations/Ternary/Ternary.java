// ��������� ���������� ��������.
public class Ternary
{
    public static void main(String args[])
    {
        int i, k;

        i = 10;
        k = i < 0 ? -i : i;	// �������� ���������� �������� i
        System.out.print("Absolute value ");
        System.out.println(i + " is " + k);

        i = -10;
        k = i < 0 ? -i : i;	// �������� ���������� �������� i
        System.out.print("Absolute value ");
        System.out.println(i + " is " + k);
    }
}

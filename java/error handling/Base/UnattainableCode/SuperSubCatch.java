// ������ �������������� ����:
// � jdk1.5+ � ��� ���� �������������� �� ���������.
public class SuperSubCatch
{
    public static void main(String args[])
    {
        try
        {
            int a = 0;
            int b = 42 / a;
        }
        catch (Exception e)
        {
            System.out.println("Generating catch-exception.");
        }
        /* ���� catch ������� �� ����� ��������� ��-�� ����, ���
        ArithmeticException �������� ���������� Exception. */
        catch (ArithmeticException e)
        {	// ������. �������� ����������
            System.out.println("Unattainabled operator.");
        }
    }
}

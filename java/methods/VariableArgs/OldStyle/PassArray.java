// ������������� ������� ��� �������� ������ ����������� ���������� ����������.
// ��� ������ ����� ������� � ��������� ���������� ���������� �����.
class PassArray
{
    static void vaTest(int v[])
    {
        System.out.print("Number of args: " + v.length + " Contents: ");
        for (int x : v)
        {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public static void main(String args[])
    {
        // �������� �������� �� ������ �������� �������
        // ��� �������� ����������.
        int n1[] = {10};
        int n2[] = {1, 2, 3};
        int n3[] = {};

        vaTest(n1); // 1 arg
        vaTest(n2); // 3 args
        vaTest(n3); // no args
    }
}

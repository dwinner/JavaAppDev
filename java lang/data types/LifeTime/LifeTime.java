// ����� ����� ����������
public class LifeTime
{
    public static void main(String args[])
    {
        int x;

        for (x = 0; x < 3; x++)
        {
            int y = -1;	// y ���������������� ������ ��� ��� ����� � ����
            System.out.println("y is: " + y);	// ������ ������� -1
            y = 100;
            System.out.println("y now is: " + y);
        }
    }
}

// ��� ������ long
public class LongType
{
    public static void main(String args[])
    {
        int lightspeed;
        long days;
        long seconds;
        long distance;

        // ������������� �������� ����� � ���� � �������
        lightspeed = 186000;
        days = 1000;	// ������ ���������� ����
        seconds = days * 24 * 60 * 60;	// ������������� � �������
        distance = lightspeed * seconds;	// ��������� ����������

        System.out.print("For " + days);
        System.out.print(" days light signal will go about ");
        System.out.print(distance + " miles.");
    }
}

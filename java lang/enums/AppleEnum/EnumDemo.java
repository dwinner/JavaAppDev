// ������������ ������ �����.
enum Apple
{
    Jonathan,
    GoldenDel,
    RedDel,
    Winesap,
    Cortland
}

public class EnumDemo
{
    public static void main(String args[])
    {
        Apple ap;
        ap = Apple.RedDel;
        // ����� �������� enum.
        System.out.println("Value of ap: " + ap);
        System.out.println();
        ap = Apple.GoldenDel;
        // ��������� ���� ������������ ��������.
        if (ap == Apple.GoldenDel)
        {
            System.out.println("ap contents GoldenDel.\n");
        }
        // ���������� enum ��� ���������� ���������� switch.
        switch (ap)
        {
            case Jonathan:
                System.out.println("Jonathan red.");
                break;
            case GoldenDel:
                System.out.println("Golden Delicious yellow.");
                break;
            case RedDel:
                System.out.println("Red Delicious red.");
                break;
            case Winesap:
                System.out.println("Winesap red.");
                break;
            case Cortland:
                System.out.println("Cortland red");
                break;
        }
    }
}

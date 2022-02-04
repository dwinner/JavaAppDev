// ������������� ������������ enum, ���������� ���������� � ������
enum Apple
{
    Jonathan(10),
    GoldenDel(9),
    RedDel(12),
    Winesap(15),
    Cortland(8);
    private int price;	// ���� ������� ������

    // ����������� (����� ���� private)
    Apple(int p)
    {
        price = p;
    }

    // ������������� �����������
    Apple()
    {
        price = -1;
    }

    int getPrice()
    {
        return price;
    }
}

public class EnumDemo3
{
    public static void main(String args[])
    {
        Apple ap;
        // ���������� ���� Winesap.
        System.out.println("Winesap costs " + Apple.Winesap.getPrice() + " cents.\n");
        // ���������� ���� ���� ������ �����.
        System.out.println("All apples cost: ");
        for (Apple a : Apple.values())
        {
            System.out.println(a + " costs " + a.getPrice() + " cents.");
        }
    }
}

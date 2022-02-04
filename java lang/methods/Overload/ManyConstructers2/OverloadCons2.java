// �������� �������� � ������.
class Box
{
    double width;
    double height;
    double depth;

    // ����������� ��� ������������� ���� ��������
    Box(double w, double h, double d)
    {
        width = w;
        height = h;
        depth = d;
    }

    // ����������, �������� ���� �������
    Box(Box ob)
    {	// ��������� ����� ������������
        width = ob.width;
        height = ob.height;
        depth = ob.depth;
    }

    // ����������� ��� ������������� ��� �������� ��������
    Box()
    {
        width = -1;		// ������������ -1 ��� ��������
        height = -1;	// �� �������������������
        depth = -1;		// �����
    }

    // ����������� ��� �������� ����
    Box(double len)
    {
        width = height = depth = len;
    }

    // ��������� � ���������� �����
    double volume()
    {
        return width * height * depth;
    }

    // ���������� true, ���� o ����� ����������� �������
    boolean equals(Box o)
    {
        if (o.width == width && o.height == height && o.depth == depth)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

public class OverloadCons2
{
    public static void main(String args[])
    {
        // ������� ����� � �������������� ��������� �������������
        Box mybox1 = new Box(10, 20, 15);
        Box mybox2 = new Box();
        Box mycube = new Box(7);

        Box myclone = new Box(mybox1);

        double vol;

        // �������� ����� ������� �����
        vol = mybox1.volume();
        System.out.println("Object mybox1 is " + vol);

        // �������� ����� ������� �����
        vol = mybox2.volume();
        System.out.println("Object mybox2 is " + vol);

        // �������� ����� ����
        vol = mycube.volume();
        System.out.println("Object mycube is " + vol);

        // �������� ����� �����
        vol = myclone.volume();
        System.out.println("Object clone is " + vol);
    }
}

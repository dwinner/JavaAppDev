// ��������������� toString() ��� Box-�������.
class Box
{
    private double width;
    private double height;
    private double depth;

    Box(double w, double h, double d)
    {
        width = w;
        height = h;
        depth = d;
    }

    public String toString()
    {
        return "Dimensions of the Box-objects: " + width + " x "
            + depth + " x " + height + ".";
    }
}

public class toStringDemo
{
    public static void main(String args[])
    {
        Box b = new Box(10, 12, 14);
        String s = "Box b: " + b;	// ������������ Box-�������

        System.out.println(b);	// �������������� Box-������� � ������
        System.out.println(s);
    }
}

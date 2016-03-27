// Переопределение toString() для Box-классов.
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
        String s = "Box b: " + b;	// конкатенация Box-объекта

        System.out.println(b);	// Преобразование Box-объекта в строку
        System.out.println(s);
    }
}

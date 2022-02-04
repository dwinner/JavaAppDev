// ����������� ������ � ������.
abstract class Figure
{
    double dim1;
    double dim2;

    Figure(double a, double b)
    {
        dim1 = a;
        dim2 = b;
    }

    abstract double area();
}

class Rectangle extends Figure
{
    Rectangle(double a, double b)
    {
        super(a, b);
    }

    // �������������� area ��� ��������������
    double area()
    {
        System.out.println("In Area for Rectangle.");
        return dim1 * dim2;
    }
}

class Triangle extends Figure
{
    Triangle(double a, double b)
    {
        super(a, b);
    }

    // �������������� area ��� �������������� ������������
    double area()
    {
        System.out.println("In Area for Triangle.");
        return dim1 * dim2 / 2;
    }
}

class AbstractAreas
{
    public static void main(String args[])
    {
        // Figure f = new Figure(10, 10);
        Rectangle r = new Rectangle(9, 5);
        Triangle t = new Triangle(10, 8);

        Figure figref;	// OK, ������ �� ��������

        figref = r;
        System.out.println("Square is " + figref.area());

        figref = t;
        System.out.println("Square is " + figref.area());
    }
}

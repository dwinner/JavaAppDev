
import java.util.Arrays;

// Ограниченные шаблонные аргументы.

// Двумерные координаты.

// Трехмерные координаты.
class TwoD
{
    private int x, y;

    TwoD(int a, int b)
    {
        x = a;
        y = b;
    }

    protected int getX() { return x; }
    protected void setX(int x) { this.x = x; }

    protected int getY() { return y; }
    protected void setY(int y) { this.y = y; }
}

// Трехмерные координаты.

// Четырехмерные координаты.
class ThreeD extends TwoD
{
    private int z;

    ThreeD(int a, int b, int c)
    {
        super(a, b);
        z = c;
    }

    protected int getZ() { return z; }
    protected void setZ(int z) { this.z = z; }
}

// Четырехмерные координаты.

// Этот класс хранит массив координатных объектов.
class FourD extends ThreeD
{
    private int t;

    FourD(int a, int b, int c, int d)
    {
        super(a, b, c);
        t = d;
    }

    protected int getT() { return t; }
    protected void setT(int t) { this.t = t; }
}

// Этот класс хранит массив координатных объектов.

// Демонстрация ограниченных шаблонов.
class Coords<T extends TwoD>
{
    private T[] coords;
    Coords(T[] o) { coords = o; }

    protected T[] getCoords() { return Arrays.copyOf(coords, coords.length); }
}

// Демонстрация ограниченных шаблонов.
public class BoundedWildcard
{
    static void showXY(Coords<?> c)
    {
        System.out.println("X Y Coordinates:");
        for (int i = 0; i < c.getCoords().length; i++)
        {
            System.out.println(c.getCoords()[i].getX() + " " + c.getCoords()[i].getY());
        }
        System.out.println();
    }

    static void showXYZ(Coords<? extends ThreeD> c)
    {
        System.out.println("X Y Z Coordinates:");
        for (int i = 0; i < c.getCoords().length; i++)
        {
            System.out.println(c.getCoords()[i].getX()
                + " " + c.getCoords()[i].getY()
                + " " + c.getCoords()[i].getZ()
            );
        }
        System.out.println();
    }

    static void showAll(Coords<? extends FourD> c)
    {
        System.out.println("X Y Z T Coordinates:");
        for (int i = 0; i < c.getCoords().length; i++)
        {
            System.out.println(c.getCoords()[i].getX() + " "
                + c.getCoords()[i].getY() + " "
                + c.getCoords()[i].getZ() + " "
                + c.getCoords()[i].getT()
            );
        }
        System.out.println();
    }

    public static void main(String args[])
    {
        TwoD td[] =
        {
            new TwoD(0, 0),
            new TwoD(7, 9),
            new TwoD(18, 4),
            new TwoD(-1, -23)
        };
        Coords<TwoD> tdlocs = new Coords<>(td);

        System.out.println("Contents of tdlocs.");
        showXY(tdlocs); // OK, is a TwoD
        //  showXYZ(tdlocs); // Error, not a ThreeD
        //  showAll(tdlocs); // Error, not a FourD

        // Теперь, создадим несколько объектов FourD.
        FourD fd[] =
        {
            new FourD(1, 2, 3, 4),
            new FourD(6, 8, 14, 8),
            new FourD(22, 9, 4, 9),
            new FourD(3, -2, -23, 17)
        };

        Coords<FourD> fdlocs = new Coords<FourD>(fd);

        System.out.println("Contents of fdlocs.");
        // Здесь всё OK.
        showXY(fdlocs);
        showXYZ(fdlocs);
        showAll(fdlocs);
    }
}
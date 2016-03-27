// Вызов конструктора базового класса.

class Wrapping
{
    private int i;

    Wrapping(int x) { i = x; }

    public int value() { return i; }
}

public class Parcel8
{
    public Wrapping wrapping(int x)
    {
        // Вызов конструктора базового класса.
        return new Wrapping(x)
        {	// аргумент конструктора
            @Override public int value()
            {
                return super.value() * 47;
            }
        };
    }

    public static void main(String[] args)
    {
        Parcel8 p = new Parcel8();
        Wrapping w = p.wrapping(10);
    }
}
// Автоупаковка/автораспаковка происходит с методами
// параметров и возвращаемыми значениями.

public class AutoBox2
{
    // принять параметр Integer и вернуть значение int

    static int m(Integer v)
    {
        return v;	// автораспаковка int
    }

    public static void main(String args[])
    {
        // Передача int методу m() и присвоение возвращаемого значения
        // объекту Integer. Здесь аргумент 100 автоматически упаковывается
        // в Integer. Возвращаемое значение также упаковывается в Integer.
        Integer iOb = m(100);
        System.out.println(iOb);
    }
}

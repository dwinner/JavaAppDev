// Демонстрация блока кода
public class BlockTest
{
    public static void main(String args[])
    {
        int x, y;
        y = 20;

        // телом этого цикла является блок
        for (x = 0; x < 10; x++)
        {
            System.out.println("Value of x: " + x);
            System.out.println("Value of y: " + y);
            y = y - 2;
        }
    }
}

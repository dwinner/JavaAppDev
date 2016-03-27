// Использование встроенных методов перечислений.
// Перечисление сортов яблок.
enum Apple
{
    Jonathan,
    GoldenDel,
    RedDel,
    Winesap,
    Cortland
}

public class EnumDemo2
{
    public static void main(String args[])
    {
        Apple ap;
        System.out.println("Apple constants");
        // Применение values()
        Apple allapples[] = Apple.values();
        for (Apple a : allapples)
        {
            System.out.println(a);
        }
        System.out.println();
        // Применение valueOf()
        ap = Apple.valueOf("Winesap");
        System.out.println("ap contents " + ap);
    }
}

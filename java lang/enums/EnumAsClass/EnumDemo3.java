// Использование конструктора enum, переменной экземпляра и метода
enum Apple
{
    Jonathan(10),
    GoldenDel(9),
    RedDel(12),
    Winesap(15),
    Cortland(8);
    private int price;	// Цена каждого яблока

    // Конструктор (Может быть private)
    Apple(int p)
    {
        price = p;
    }

    // Перегруженный конструктор
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
        // Отобразить цену Winesap.
        System.out.println("Winesap costs " + Apple.Winesap.getPrice() + " cents.\n");
        // Отобразить цены всех сортов яблок.
        System.out.println("All apples cost: ");
        for (Apple a : Apple.values())
        {
            System.out.println(a + " costs " + a.getPrice() + " cents.");
        }
    }
}

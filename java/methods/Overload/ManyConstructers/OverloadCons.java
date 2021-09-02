// Перегрузка конструкторов.
class Box
{
    double width;
    double height;
    double depth;

    // конструктор для инициализации всех размеров
    Box(double w, double h, double d)
    {
        width = w;
        height = h;
        depth = d;
    }

    // конструктор для инициализации без указания размеров
    Box()
    {
        width = -1;		// использовать -1 для указания
        height = -1;	// не инициализированного
        depth = -1;		// блока
    }

    // конструктор для создания куба
    Box(double len)
    {
        width = height = depth = len;
    }

    // вычислить и возвратить объём
    double volume()
    {
        return width * height * depth;
    }
}

public class OverloadCons
{
    public static void main(String args[])
    {
        // Создать блоки, используя различные конструкторы
        Box mybox1 = new Box(10, 20, 15);
        Box mybox2 = new Box();
        Box mycube = new Box(7);

        double vol;

        // Получить объём первого блока
        vol = mybox1.volume();
        System.out.println("Capacity of mybox1 is " + vol);
        // Получить объём второго блока
        vol = mybox2.volume();
        System.out.println("Capacity of mybox2 is " + vol);

        // Получить объём куба
        vol = mycube.volume();
        System.out.println("Capacity of mycube is " + vol);
    }
}

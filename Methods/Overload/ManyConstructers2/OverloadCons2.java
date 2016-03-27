// Передача объектов в методы.
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

    // конструтор, строящий клон объекта
    Box(Box ob)
    {	// переслать объём конструктора
        width = ob.width;
        height = ob.height;
        depth = ob.depth;
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

    // Возвратить true, если o равно вызывающему объекту
    boolean equals(Box o)
    {
        if (o.width == width && o.height == height && o.depth == depth)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

public class OverloadCons2
{
    public static void main(String args[])
    {
        // создать блоки с использованием различных конструкторов
        Box mybox1 = new Box(10, 20, 15);
        Box mybox2 = new Box();
        Box mycube = new Box(7);

        Box myclone = new Box(mybox1);

        double vol;

        // получить объём первого блока
        vol = mybox1.volume();
        System.out.println("Object mybox1 is " + vol);

        // получить объём второго блока
        vol = mybox2.volume();
        System.out.println("Object mybox2 is " + vol);

        // получить объём куба
        vol = mycube.volume();
        System.out.println("Object mycube is " + vol);

        // получить объём клона
        vol = myclone.volume();
        System.out.println("Object clone is " + vol);
    }
}

// Перечисление сортов яблок.
enum Apple
{
    Jonathan,
    GoldenDel,
    RedDel,
    Winesap,
    Cortland
}

public class EnumDemo
{
    public static void main(String args[])
    {
        Apple ap;
        ap = Apple.RedDel;
        // Вывод значения enum.
        System.out.println("Value of ap: " + ap);
        System.out.println();
        ap = Apple.GoldenDel;
        // Сравнение двух перечислимых значений.
        if (ap == Apple.GoldenDel)
        {
            System.out.println("ap contents GoldenDel.\n");
        }
        // Применение enum для управления оператором switch.
        switch (ap)
        {
            case Jonathan:
                System.out.println("Jonathan red.");
                break;
            case GoldenDel:
                System.out.println("Golden Delicious yellow.");
                break;
            case RedDel:
                System.out.println("Red Delicious red.");
                break;
            case Winesap:
                System.out.println("Winesap red.");
                break;
            case Cortland:
                System.out.println("Cortland red");
                break;
        }
    }
}

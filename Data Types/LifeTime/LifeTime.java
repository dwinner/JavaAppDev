// Время жизни переменной
public class LifeTime
{
    public static void main(String args[])
    {
        int x;

        for (x = 0; x < 3; x++)
        {
            int y = -1;	// y инициализируется каждый раз при входе в блок
            System.out.println("y is: " + y);	// всегда выводит -1
            y = 100;
            System.out.println("y now is: " + y);
        }
    }
}

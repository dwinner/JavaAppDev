// “ип данных long
public class LongType
{
    public static void main(String args[])
    {
        int lightspeed;
        long days;
        long seconds;
        long distance;

        // аппроксимаци€ скорости света в мили в секунду
        lightspeed = 186000;
        days = 1000;	// задать количество дней
        seconds = days * 24 * 60 * 60;	// ѕреобразовать в секунды
        distance = lightspeed * seconds;	// вычислить рассто€ние

        System.out.print("For " + days);
        System.out.print(" days light signal will go about ");
        System.out.print(distance + " miles.");
    }
}

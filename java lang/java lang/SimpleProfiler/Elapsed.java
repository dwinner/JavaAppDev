// ¬ычисл€ет врем€ выполнени€ программы.
public class Elapsed
{
    public static void main(String args[])
    {
        long start, end;

        System.out.println("For cycle with step from 0 to 1,000,000,000");
        start = System.currentTimeMillis();	// получить врем€ начала
        for (int i = 0; i < 1000000000; i++) ;
        end = System.currentTimeMillis();	// получить врем€ конца

        System.out.println("Executing time: " + (end - start));
    }
}

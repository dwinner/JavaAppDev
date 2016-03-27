
import java.util.*;

/**
 * Эта программа демонстрирует работу цикла <code>while</code>.
 * @version 1.20 2004-02-10
 * @author Cay Horstmann
 */
public class Retirement
{
    public static void main(String[] args)
    {
        // Цтение входных данных
        Scanner in = new Scanner(System.in);

        System.out.print("How much money do you need to retire? ");
        double goal = in.nextDouble();

        System.out.print("How much money will you contribute every year? ");
        double payment = in.nextDouble();

        System.out.print("Interest rate in %: ");
        double interestRate = in.nextDouble();

        double balance = 0;
        int years = 0;

        // Баланс обновляется до тех пор, пока не будет достигнута требуемая сумма.
        while (balance < goal)
        {
            // Добавление ежегодного взноса и процентов
            balance += payment;
            double interest = balance * interestRate / 100;
            balance += interest;
            years++;
        }

        System.out.println("You can retire in " + years + " years.");
    }
}

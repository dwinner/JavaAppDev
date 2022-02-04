
import java.util.*;

/**
 * Эта программа демонстрирует работу цикла <code>do/while</code>.
 * @version 1.20 2004-02-10
 * @author Cay Horstmann
 */
public class Retirement2
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        System.out.print("How much money will you contribute every year? ");
        double payment = in.nextDouble();

        System.out.print("Interest rate in %: ");
        double interestRate = in.nextDouble();

        double balance = 0;
        int year = 0;

        String input;

        // Обновлять баланс, пока пользователь вводит N
        do
        {
            // Добавление ежегодного взноса и процентов
            balance += payment;
            double interest = balance * interestRate / 100;
            balance += interest;

            year++;

            // Вывод текущего баланса
            System.out.printf("After year %d, your balance is %,.2f%n", year, balance);

            // Запрос, готов ли пользователь прекратить работу
            System.out.print("Ready to retire? (Y/N) ");
            input = in.next();
        }
        while (input.equals("N"));
    }
}

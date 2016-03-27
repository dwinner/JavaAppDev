
import java.util.*;

/**
 * Эта программа демонстрирует консольный ввод.
 * @version 1.10 2004-02-10
 * @author Cay Horstmann
 */
public class InputTest
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        // Получение первой строки входных данных
        System.out.print("What is your name? ");
        String name = in.nextLine();

        // Получение целочисленного значения
        System.out.print("How old are you? ");
        int age = in.nextInt();

        // Отображение информации в консольном окне
        System.out.println("Hello, " + name + ". Next year, you'll be " + (age + 1));
    }
}

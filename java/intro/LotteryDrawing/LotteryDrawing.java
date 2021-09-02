
import java.util.*;

/**
 * ��� ��������� ������������� ����������� � ���������.
 * @version 1.20 2004-02-10
 * @author Cay Horstmann
 */
public class LotteryDrawing
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        System.out.print("How many numbers do you need to draw? ");
        int k = in.nextInt();

        System.out.print("What is the highest number you can draw? ");
        int n = in.nextInt();

        // ���������� ������� ������� 1 2 3 . . . n
        int[] numbers = new int[n];
        for (int i = 0; i < numbers.length; i++)
        {
            numbers[i] = i + 1;
        }

        // ����� k ������� � ������ �� � ������ ������
        int[] result = new int[k];
        for (int i = 0; i < result.length; i++)
        {
            // ��������� ���������� ������� ����� 0 � n - 1
            int r = (int) (Math.random() * n);

            // ����� ���������� ��������
            result[i] = numbers[r];

            // ����������� ���������� ��������
            numbers[r] = numbers[n - 1];
            n--;
        }

        // ����� �������������� �������
        Arrays.sort(result);
        System.out.println("Bet the following combination. It'll make you rich!");
        for (int r : result)
        {
            System.out.println(r);
        }
    }
}

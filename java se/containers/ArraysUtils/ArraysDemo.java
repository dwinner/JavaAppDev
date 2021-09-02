// ����� Arrays.
import java.util.*;

public class ArraysDemo
{
    public static void main(String args[])
    {
        // ������������ � ������ � ���������������� ������
        int array[] = new int[10];
        for (int i = 0; i < 10; i++)
        {
            array[i] = -3 * i;
        }

        // ��������, �����������, ��������
        System.out.print("Source content: ");
        display(array);
        Arrays.sort(array);
        System.out.print("Sorted content: ");
        display(array);
        // ��������� � ��������
        Arrays.fill(array, 2, 6, -1);
        System.out.print("After fill(): ");
        display(array);

        // ����������� � ��������
        Arrays.sort(array);
        System.out.print("Again after sorting: ");
        display(array);

        // �������� ����� �������� -9
        System.out.print("Value -9 is placed in position ");
        int index = Arrays.binarySearch(array, -9);
        System.out.println(index);
    }

    static void display(int array[])
    {
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println("");
    }
}

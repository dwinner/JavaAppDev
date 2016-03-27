// Класс Arrays.
import java.util.*;

public class ArraysDemo
{
    public static void main(String args[])
    {
        // распределить в памяти и инициализировать массив
        int array[] = new int[10];
        for (int i = 0; i < 10; i++)
        {
            array[i] = -3 * i;
        }

        // показать, сортировать, показать
        System.out.print("Source content: ");
        display(array);
        Arrays.sort(array);
        System.out.print("Sorted content: ");
        display(array);
        // заполнить и показать
        Arrays.fill(array, 2, 6, -1);
        System.out.print("After fill(): ");
        display(array);

        // сортировать и показать
        Arrays.sort(array);
        System.out.print("Again after sorting: ");
        display(array);

        // Двоичный поиск значения -9
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

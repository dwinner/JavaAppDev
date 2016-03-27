// Преобразование объекта типа ArrayList в массив.
import java.util.*;

public class ArrayListToArray
{
    public static void main(String args[])
    {
        // создать объект типа ArrayList
        List<Integer> al = new ArrayList<>();

        // добавить элементы в список массива
        al.add(1);
        al.add(2);
        al.add(3);
        al.add(4);

        System.out.println("Contents of object al: " + al);
        
        // получить массив
        Object ia[] = al.toArray();
        int sum = 0;

        // суммировать элементы массива
        for (int i = 0; i < ia.length; i++)
        {
            sum += ((Integer) ia[i]).intValue();
        }

        System.out.println("Summary is: " + sum);
    }
}

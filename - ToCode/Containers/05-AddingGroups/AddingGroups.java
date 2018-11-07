//: AddingGroups.java
// Добавление групп элементов в объекты Collection.
import java.util.*;

public class AddingGroups
{
    public static void main(String[] args)
    {
        Collection<Integer> collection =
            new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        Integer[] moreInts = { 6, 7, 8, 9, 10 };
        collection.addAll(Arrays.asList(moreInts));
        // Работает намного быстрее, но таким способом
        // невозможно сконструировать Collection:
        Collections.addAll(collection, 11, 12, 13, 14, 15);
        Collections.addAll(collection, moreInts);
        // Создает список на основе массива:
        List<Integer> list = Arrays.asList(16, 17, 18, 19, 20);
        list.set(1, 99); // OK -- изменение элемента
        // list.add(21); // Ошибка времени выполнения - нижележащий
                         // массив не должен изменяться в размерах.
    }
} ///:~

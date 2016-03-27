//: ListFeatures.java
import typeinfo.pets.*;
import java.util.*;

public class ListFeatures
{
    public static void main(String[] args)
    {
        Random rand = new Random(47);
        List<Pet> pets = Pets.arrayList(7);
        System.out.println("1: " + pets);
        Hamster h = new Hamster();
        pets.add(h); // Автоматическое изменение размера
        System.out.println("2: " + pets);
        System.out.println("3: " + pets.contains(h));
        pets.remove(h); // Удаление объекта
        Pet p = pets.get(2);
        System.out.println("4: " +  p + " " + pets.indexOf(p));
        Pet cymric = new Cymric();
        System.out.println("5: " + pets.indexOf(cymric));
        System.out.println("6: " + pets.remove(cymric));
        // Точно заданный объект:
        System.out.println("7: " + pets.remove(p));
        System.out.println("8: " + pets);
        pets.add(3, new Mouse()); // Вставка по индексу
        System.out.println("9: " + pets);
        List<Pet> sub = pets.subList(1, 4);
        System.out.println("subList: " + sub);
        System.out.println("10: " + pets.containsAll(sub));
        Collections.sort(sub); // Сортировка на месте
        System.out.println("sorted subList: " + sub);
        // Для containsAll() порядок не важен:
        System.out.println("11: " + pets.containsAll(sub));
        Collections.shuffle(sub, rand); // Случайная перестановка
        System.out.println("shuffled subList: " + sub);
        System.out.println("12: " + pets.containsAll(sub));
        List<Pet> copy = new ArrayList<Pet>(pets);
        sub = Arrays.asList(pets.get(1), pets.get(4));
        System.out.println("sub: " + sub);
        copy.retainAll(sub);
        System.out.println("13: " + copy);
        copy = new ArrayList<Pet>(pets); // Получение новой копии
        copy.remove(2); // Удаление по индексу
        System.out.println("14: " + copy);
        copy.removeAll(sub); // Удаление заданных элементов
        System.out.println("15: " + copy);
        copy.set(1, new Mouse()); // Замена элемента
        System.out.println("16: " + copy);
        copy.addAll(2, sub); // Вставка в середину списка
        System.out.println("17: " + copy);
        System.out.println("18: " + pets.isEmpty());
        pets.clear(); // Удаление всех элементов
        System.out.println("19: " + pets);
        System.out.println("20: " + pets.isEmpty());
        pets.addAll(Pets.arrayList(4));
        System.out.println("21: " + pets);
        Object[] o = pets.toArray();
        System.out.println("22: " + o[3]);
        Pet[] pa = pets.toArray(new Pet[0]);
        System.out.println("23: " + pa[3].id());
    }
}
// ---------------------------------------------------------------------------------------
/* Output:
1: [Rat, Manx, Cymric, Mutt, Pug, Cymric, Pug]
2: [Rat, Manx, Cymric, Mutt, Pug, Cymric, Pug, Hamster]
3: true
4: Cymric 2
5: -1
6: false
7: true
8: [Rat, Manx, Mutt, Pug, Cymric, Pug]
9: [Rat, Manx, Mutt, Mouse, Pug, Cymric, Pug]
subList: [Manx, Mutt, Mouse]
10: true
sorted subList: [Manx, Mouse, Mutt]
11: true
shuffled subList: [Mouse, Manx, Mutt]
12: true
sub: [Mouse, Pug]
13: [Mouse, Pug]
14: [Rat, Mouse, Mutt, Pug, Cymric, Pug]
15: [Rat, Mutt, Cymric, Pug]
16: [Rat, Mouse, Cymric, Pug]
17: [Rat, Mouse, Mouse, Pug, Cymric, Pug]
18: false
19: []
20: true
21: [Manx, Cymric, Rat, EgyptianMau]
22: EgyptianMau
23: 14
*///:~

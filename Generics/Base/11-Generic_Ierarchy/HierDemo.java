// Подкласс может добавлять свои собственные параметры типа.
class Gen<T>
{
    private T ob; // Объявление объекта типа T
    Gen(T o) { ob = o; }
    T getob() { return ob; }
}

// Подкласс Gen, который определяет второй параметр типа по имени V.
class Gen2<T, V> extends Gen<T>
{
    private V ob2;

    Gen2(T o, V o2)
    {
        super(o);
        ob2 = o2;
    }

    V getob2() { return ob2; }
}

// Создание объекта типа Gen2.
public class HierDemo
{
    public static void main(String args[])
    {
        // Создание объектов Gen2 для String и Integer.
        Gen2<String, Integer> x = new Gen2<>("Value is: ", 99);
        System.out.print(x.getob());
        System.out.println(x.getob2());
    }
}
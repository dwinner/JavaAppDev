// Необобщенный класс может быть суперклассом
// для обобщенного подкласса. Необобщенный класс.
class NonGen
{
    private int num;
    NonGen(int i) { num = i; }
    int getnum() { return num; }
}

// Обобщенный подкласс.
class Gen<T> extends NonGen
{
    private T ob; // Объявление объекта типа T

    Gen(T o, int i)
    {   // Передать конструктору объект типа T.
        super(i);
        ob = o;
    }

    T getob() { return ob; }    // Возвращает ob.
}

// Создать объект Gen.
public class HierDemo2
{
    public static void main(String args[])
    {
        // Создать объект Gen для String.
        Gen<String> w = new Gen<>("Hello", 47);
        System.out.print(w.getob() + " ");
        System.out.println(w.getnum());
    }
}
// Простой обощенный класс с двумя типами-параметрами: T и V.
class TwoGen<T, V>
{
    private T ob1;
    private V ob2;
    
    // Передать конструктору ссылки на объект типа T и объект типа V.
    TwoGen(T o1, V o2)
    {
        ob1 = o1;
        ob2 = o2;
    }
    // Показать типы T и V.

    void showTypes()
    {
        System.out.println("Type T: " + ob1.getClass().getName());
        System.out.println("Type V: " + ob2.getClass().getName());
    }

    T getob1() { return ob1; }
    V getob2() { return ob2; }
}

// Демонстрация TwoGen.
public class SimpGen
{
    public static void main(String args[])
    {
        TwoGen<Integer, String> tgObj = new TwoGen<>(88, "Gererics");
        // Показать типы
        tgObj.showTypes();
        // Получить и показать значения.
        int v = tgObj.getob1();
        System.out.println("Value: " + v);
        String str = tgObj.getob2();
        System.out.println("Value: " + str);
    }
}

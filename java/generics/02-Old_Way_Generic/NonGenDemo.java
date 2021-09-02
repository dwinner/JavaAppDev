// NonGen - функциональный эквивалент Gen, не использующий обобщений.
class NonGen
{
    private Object ob;	// ob теперь имеет тип Object
    // Передать конструктору ссылку на объект типа Object

    NonGen(Object o) { ob = o; }
    // Вернуть тип Object.

    Object getob() { return ob; }
    // Показать тип ob.

    void showType() { System.out.println("Type ob is " + ob.getClass().getName()); }
}
// Демонстрация необобщенного класса.
public class NonGenDemo
{
    public static void main(String args[])
    {
        NonGen iOb;
        // Создать объект NonGen и сохранить Integer в нем. Используется автоупаковка
        iOb = new NonGen(88);
        // Показать тип данных, используемый iOb.
        iOb.showType();
        // Получить значение iOb. На этот раз приведение необходимо.
        int v = (Integer) iOb.getob();
        System.out.println("Value: " + v);
        System.out.println();
        // Создать другой объект NonGen и сохранить в нем String.
        NonGen strOb = new NonGen("Test without generics");
        // Показать тип данных, используемый strOb.
        strOb.showType();
        // Получить значение strOb. Опять же - привидение необходимо.
        String str = (String) strOb.getob();
        System.out.println("Value: " + str);
        // Это компилируется, но концептуально неверно!
        iOb = strOb;
        v = (Integer) iOb.getob();	// ошибка времени выполнения!
    }
}

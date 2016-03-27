// Использование операции instanceof с иерархией обощенных классов.
class Gen<T>
{
    private T ob;
    Gen(T o) { ob = o; }
    T getob() { return ob; }    // Возвращает ob.
}

// Подкласс Gen.
class Gen2<T> extends Gen<T>
{
    Gen2(T o) { super(o); }
}

/*
 * Демонстрация определения идентификатора типа в иерархии обощенных классов.
 */
public class HierDemo3
{
    public static void main(String args[])
    {
        // Создать объект Gen для Integer.
        Gen<Integer> iOb = new Gen<>(88);

        // Создать объект Gen2 для Integer
        Gen2<Integer> iOb2 = new Gen2<>(99);

        // Создать объект Gen2 для String.
        Gen2<String> strOb2 = new Gen2<>("Generics Test");

        // Проверить, является ли iOb2 какой-то из форм Gen2.
        if (iOb2 instanceof Gen2<?>)
        {
            System.out.println("iOb2 is instance of Gen2");
        }

        // Проверить, является ли iOb2 какой-то из форм Gen
        if (iOb2 instanceof Gen<?>)
        {
            System.out.println("iOb2 is instance of Gen");
        }

        System.out.println();

        // Проверить, является ли strOb2 объектом Gen2.
        if (strOb2 instanceof Gen2<?>)
        {
            System.out.println("strOb is instance of Gen2");
        }

        // Проверить, является ли strOb2 объектом Gen
        if (strOb2 instanceof Gen<?>)
        {
            System.out.println("strOb is instance of Gen");
        }

        System.out.println();

        // Проверить, является ли iOb экземпляром Gen2, что не так
        if (iOb instanceof Gen2<?>)
        {
            System.out.println("iOb is instance of Gen2");
        }

        // Проверить, является ли iOb экземпляром Gen, что так и есть.
        if (iOb instanceof Gen<?>)
        {
            System.out.println("iOb is instance of Gen");
        }

        // Следующее не скомпилируется, потому что информация
        // об обобщенном типе во время выполнения отсутствует
		/*
         * if (iOb2 instanceof Gen2<Integer>) System.out.println("iOb2 is instance of
         * Gen2<Integer>");
         */
    }
}
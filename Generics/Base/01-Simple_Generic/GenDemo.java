/* Простой обобщенный класс.
 Здесь T - это параметр типа,
 который будет заменен реальным типом
 при создании объекта типа Gen. */

class Gen<T>
{
    private T ob; // Объявление типа T

    // Передать конструктору ссылку на объект типа T.
    Gen(T o) { ob = o; }

    // Вернуть ob.
    T getob() { return ob; }

    // Показать тип T.
    void showType() { System.out.println("Type of T is " + ob.getClass().getName()); }
}

// Демонстрация обобщенного класса.
public class GenDemo
{
    public static void main(String args[])
    {
        // Создать Gen-ссылку для Integers.
        Gen<Integer> iOb;

        /*
         * Создать объект Gen<Integer> и присвоить ссылку на iOb. Отметьте применение
         * автоупаковки для инкапсуляции значения 88 в объект Integer.
         */
        iOb = new Gen<>(88);

        // Показать тип данных, используемый iOb.
        iOb.showType();

        /*
         * Получить значение iOb. Обратите внимание, что никакого приведения не нужно.
         */
        int v = iOb.getob();
        System.out.println("value: " + v);
        System.out.println();

        // Создать объект Gen для String.
        Gen<String> strOb = new Gen<>("Generics Test");

        // Показать тип данных, используемый strOb.
        strOb.showType();

        /*
         * Получить значение strOb. Опять же приведение не требуется
         */
        String str = strOb.getob();
        System.out.println("value: " + str);
    }
}
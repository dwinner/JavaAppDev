// Демонстрация raw-типа.
class Gen<T>
{
    final T ob; // Объявление типа T

    // Передача конструктору ссылки на объект типа T.
    Gen(T o) { ob = o; }

    // Возврат ob.
    T getob() { return ob; }
}

// Демонстрация raw-типа.
public class RawDemo
{
    @SuppressWarnings("unchecked")
    public static void main(String args[])
    {
        // Создать объект Gen для Integer.
        Gen<Integer> iOb = new Gen<>(88);

        // Создать объект Gen для String.
        Gen<String> strOb = new Gen<>("Generics Test");

        // Создать объект raw-типа Gen и дать ему значение Double.
        @SuppressWarnings("unchecked")
        Gen raw = new Gen(new Double(98.6));

        // Приведение необходимо, поскольку тип неизвестен.
        double d = (Double) raw.getob();
        System.out.println("value: " + d);

        // Использование raw-типов может вызвать исключения
        // времени выполнения. Ниже представлены некоторые примеры.

        // Следующее приведение вызовет ошибку времени выполнения!
        //    int i = (Integer) raw.getob(); // ошибка времени выполнения.

        // Это присваивание нарушает безопасность типов.
        strOb = raw; // OK, но потенциально неверно
        //    String str = strOb.getob(); // ошибка времени выполнения.

        // Это присваивание также нарушает безопасность типов.
        raw = iOb; // OK, но потенциально неверно
        //    d = (Double) raw.getob(); // ошибка времени выполнения.
    }
}
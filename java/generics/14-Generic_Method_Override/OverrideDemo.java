// Переопределение обобщенного метода в обобщенном классе.
class Gen<T>
{
    private T ob;
    Gen(T o) { ob = o; }

    T getob()
    {
        System.out.print("Gen's getob(): ");
        return ob;
    }
}

// Подкласс Gen, переопределяющий getob().
class Gen2<T> extends Gen<T>
{
    Gen2(T o) { super(o); }

    // Переопределение getob().
    @Override T getob()
    {
        System.out.print("Gen2's getob(): ");
        return super.getob();
    }
}

// Демонстрация переопределения обобщенных методов.
public class OverrideDemo
{
    public static void main(String args[])
    {
        // Создание объекта Gen для Integer.
        Gen<Integer> iOb = new Gen<>(88);

        // Создание объекта Gen2 для Integer.
        Gen2<Integer> iOb2 = new Gen2<>(99);

        // Создание объекта Gen2 для String.
        Gen2<String> strOb2 = new Gen2<>("Generics Test");

        System.out.println(iOb.getob());
        System.out.println(iOb2.getob());
        System.out.println(strOb2.getob());
    }
}
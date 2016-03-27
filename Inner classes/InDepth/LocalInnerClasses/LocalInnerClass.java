// Локальные внутренние классы.

interface Counter
{
    int next();
}

public class LocalInnerClass
{
    private int count = 0;

    Counter getCounter(final String name)
    {
        // Локальный внутренний класс
        class LocalCounter implements Counter
        {
            public LocalCounter()
            {
                // У локального внутреннего класса может быть собственный конструктор
                System.out.println("LocalCounter");
            }

            public int next()
            {
                System.out.println(name);	// неизменный аргумент
                return count++;
            }
        }
        return new LocalCounter();
    }

    // То же самое с безымянным внутренним классом
    Counter getCounter2(final String name)
    {
        return new Counter()
        {
            // У безымянного внутреннего класса не может быть
            // именованного конструктора, "легальна" только
            // инициализация экземпляром
            {
                System.out.println("Noname Counter()");
            }

            public int next()
            {
                System.out.println(name);	// неизменный аргумент
                return count++;
            }
        };
    }

    public static void main(String[] args)
    {
        LocalInnerClass lic = new LocalInnerClass();
        Counter
            c1 = lic.getCounter("local"),
            c2 = lic.getCounter2("noname");

        for (int i = 0; i < 5; i++)
        {
            System.out.println(c1.next());
        }

        for (int i = 0; i < 5; i++)
        {
            System.out.println(c2.next());
        }
    }
}
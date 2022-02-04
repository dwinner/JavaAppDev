// Методы класса Runtime
// Демонстрирует использование totalMemory(), freeMemory() и gc().
public class MemoryDemo
{
    public static void main(String args[])
    {
        Runtime r = Runtime.getRuntime();
        long mem1, mem2;
        Integer someints[] = new Integer[1000];

        System.out.println("Full memory capacity: " + r.totalMemory());

        mem1 = r.freeMemory();
        System.out.println("Starting free memory: " + mem1);
        r.gc();
        mem1 = r.freeMemory();
        System.out.println("Free memory after garbage collecting: " + mem1);

        for (int i = 0; i < 1000; i++)
        {
            someints[i] = new Integer(i);	// выделение int-памяти
        }
        mem2 = r.freeMemory();
        System.out.println("Free memory after allocating: " + mem2);
        System.out.println("Allocated memory: " + (mem1 - mem2));

        // сбросить указатели Integer-элементов.
        for (int i = 0; i < 1000; i++)
        {
            someints[i] = null;
        }

        r.gc();	// вызов сборщика мусора
        mem2 = r.freeMemory();
        System.out.println("Free memory after garbage collecting: " + mem2);
    }
}

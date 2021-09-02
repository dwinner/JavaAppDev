// »спользование аргумента переменной длины совместно
// со стандартными аргументами. 
class VarArgs2
{
    // ¬ этом примере msg - обычный параметр, 
    // а v - параметр varargs. 

    static void vaTest(String msg, int... v)
    {
        System.out.print(msg + v.length + " Contents: ");
        for (int x : v)
        {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public static void main(String args[])
    {
        vaTest("One vararg: ", 10);
        vaTest("Three varargs: ", 1, 2, 3);
        vaTest("No varargs: ");
    }
}

// Демонстрация использования аргументов переменной длины. 
class VarArgs
{
    // теперь vaTest() использует аргументы переменной длины

    static void vaTest(int... v)
    {
        System.out.print("Number of args: " + v.length + " Contents: ");
        for (int x : v)
        {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public static void main(String args[])
    {
        // Обратите внимание на возможные способы вызова
        // vaTest() с переменным числом аргументов.
        vaTest(10);      // 1 arg
        vaTest(1, 2, 3); // 3 args
        vaTest();        // no args
    }
}

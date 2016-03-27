// Демонстрация finally.
public class FinallyDemo
{
    // Выход из метода через исключение.

    static void procA()
    {
        try
        {
            System.out.println("In procA");
            throw new RuntimeException("demo");
        }
        finally
        {
            System.out.println("finally for procA ");
        }
    }

    // Возврат изнутри try-блока.
    static void procB()
    {
        try
        {
            System.out.println("In procB");
            return;
        }
        finally
        {
            System.out.println("finally for procB ");
        }
    }

    // Нормальное выполнение try-блока.
    static void procC()
    {
        try
        {
            System.out.println("In procC");
        }
        finally
        {
            System.out.println("finally for procC");
        }
    }

    public static void main(String args[])
    {
        try
        {
            procA();
        }
        catch (Exception e)
        {
            System.out.println("Exception has been catched");
        }
        procB();
        procC();
    }
}

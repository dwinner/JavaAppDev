// ������������ finally.
public class FinallyDemo
{
    // ����� �� ������ ����� ����������.

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

    // ������� ������� try-�����.
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

    // ���������� ���������� try-�����.
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

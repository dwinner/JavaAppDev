// �������� ������������� �������.
class NewThread implements Runnable
{
    private String name;	// ��� ������
    private Thread t;

    NewThread(String threadname)
    {
        name = threadname;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
        t.start();	// ����� ������
    }

    // ��� ����� ����� ��� ������.
    @Override
    public void run()
    {
        try
        {
            for (int i = 5; i > 0; i--)
            {
                System.out.println(name + ": " + i);
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println(name + " is interrupted.");
        }
        System.out.println(name + " is finished.");
    }
}

public class MultiThreadDemo
{
    public static void main(String args[])
    {
        new NewThread("First");	// ����� ������
        new NewThread("Second");
        new NewThread("Third");

        try
        {
            // ����� ���������� ������ �������
            Thread.sleep(10000);
        }
        catch (InterruptedException e)
        {
            System.out.println("Interrupting of main thread.");
        }

        System.out.println("Finishing of main thread.");
    }
}

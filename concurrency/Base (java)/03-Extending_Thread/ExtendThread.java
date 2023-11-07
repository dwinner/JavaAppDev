// Создаёт второй поток расширением класса Thread.
class NewThread extends Thread
{
    NewThread()
    {
        // Создать новый, второй поток
        super("Demo Thread");
        System.out.println("Child thread: " + this);
        start();	// стартовать поток
    }

    // Это точка входа для второго потока.
    @Override
    public void run()
    {
        try
        {
            for (int i = 5; i > 0; i--)
            {
                System.out.println("Child thread: " + i);
                Thread.sleep(500);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("Interrupting of child thread.");
        }
        System.out.println("Finishing of child thread.");
    }
}

public class ExtendThread
{
    public static void main(String args[])
    {
        new NewThread();	// создать новый поток

        try
        {
            for (int i = 5; i > 0; i--)
            {
                System.out.println("Main thread: " + i);
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("Interrupting of main thread.");
        }
        System.out.println("Finishing of main thread.");
    }
}

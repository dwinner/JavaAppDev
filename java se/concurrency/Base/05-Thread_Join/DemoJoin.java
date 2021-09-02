// Использование join() для ожидания окончания потока.
class NewThread implements Runnable
{
    public String name;	// имя потока
    public Thread t;

    NewThread(String threadname)
    {
        name = threadname;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
        t.start();	// старт потока
    }

    // Это точка входа потока.
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

public class DemoJoin
{
    public static void main(String args[])
    {
        NewThread ob1 = new NewThread("First");
        NewThread ob2 = new NewThread("Second");
        NewThread ob3 = new NewThread("Third");

        System.out.println("First thread is executing: " + ob1.t.isAlive());
        System.out.println("Second thread is executing: " + ob2.t.isAlive());
        System.out.println("Third thread is executing: " + ob3.t.isAlive());
        // ждать завершения потоков
        try
        {
            System.out.println("Wait for finishing threads...");
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
        }
        catch (InterruptedException e)
        {
            System.out.println("Interrupting of main thread");
        }

        System.out.println("First thread is executing: " + ob1.t.isAlive());
        System.out.println("Second thread is executing: " + ob2.t.isAlive());
        System.out.println("Third thread is executing: " + ob3.t.isAlive());

        System.out.println("Finishing of main thread.");
    }
}

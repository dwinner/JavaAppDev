// Демонстрирует приоритеты потоков.
class clicker implements Runnable
{
    long click = 0;
    Thread t;
    private volatile boolean running = true;

    clicker(int p)
    {
        t = new Thread(this);
        t.setPriority(p);
    }

    public void run()
    {
        while (running)
        {
            click++;
        }
    }

    public void stop()
    {
        running = false;
    }

    public void start()
    {
        t.start();
    }
}

public class HiLoPri
{
    public static void main(String args[])
    {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        clicker hi = new clicker(Thread.NORM_PRIORITY + 2);
        clicker lo = new clicker(Thread.NORM_PRIORITY - 2);

        lo.start();
        hi.start();
        try
        {
            Thread.sleep(10000);
        }
        catch (InterruptedException e)
        {
            System.out.println("Interrupting of main thread.");
        }

        lo.stop();
        hi.stop();

        // Ждать завершения дочерних потоков.
        try
        {
            hi.t.join();
            lo.t.join();
        }
        catch (InterruptedException e)
        {
            System.out.println("Interrupting on finish.");
        }

        System.out.println("Thread with lower priority: " + lo.click);
        System.out.println("Thread with upper priority: " + hi.click);
    }
}

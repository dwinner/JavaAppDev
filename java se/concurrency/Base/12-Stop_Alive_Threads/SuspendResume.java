// Приостанавливает и возобновляет поток для Java2
class NewThread implements Runnable
{
    String name;	// имя потока
    Thread t;
    boolean suspendFlag;

    NewThread(String threadname)
    {
        name = threadname;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
        suspendFlag = false;
        t.start();	// старт потока
    }

    // Это точка входа для потока
    public void run()
    {
        try
        {
            for (int i = 15; i > 0; i--)
            {
                System.out.println(name + ": " + i);
                Thread.sleep(200);
                synchronized (this)
                {
                    while (suspendFlag)
                    {
                        wait();
                    }
                }
            }
        }
        catch (InterruptedException e)
        {
            System.out.println(name + " is interrupted.");
        }
        System.out.println(name + " is finished.");
    }

    void mysuspend()
    {
        suspendFlag = true;
    }

    synchronized void myresume()
    {
        suspendFlag = false;
        notify();
    }
}

public class SuspendResume
{
    public static void main(String args[])
    {
        NewThread ob1 = new NewThread("First");
        NewThread ob2 = new NewThread("Second");

        try
        {
            Thread.sleep(1000);
            ob1.mysuspend();
            System.out.println("Time-Stop for the first thread");
            Thread.sleep(1000);
            ob1.myresume();
            System.out.println("Re-Start for the first thread");
            ob2.mysuspend();
            System.out.println("Time-Stop for the second thread");
            Thread.sleep(1000);
            ob2.myresume();
            System.out.println("Re-Start for the second thread");
        }
        catch (InterruptedException e)
        {
            System.out.println("Interrupting of main thread");
        }

        // ждать завершения потока
        try
        {
            System.out.println("Wait for finishing threads.");
            ob1.t.join();
            ob2.t.join();
        }
        catch (InterruptedException e)
        {
            System.out.println("Interrupting of main thread");
        }

        System.out.println("Finishing of the main thread.");
    }
}

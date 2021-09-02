// Демонстрация поточных групп.
class NewThread extends Thread
{
    boolean suspendFlag;

    NewThread(String threadname, ThreadGroup tgOb)
    {
        super(tgOb, threadname);
        System.out.println("New thread: " + this);
        suspendFlag = false;	// запустить поток
        start();
    }

    // это точка входа для потока.
    public void run()
    {
        try
        {
            for (int i = 5; i > 0; i--)
            {
                System.out.println(getName() + ": " + i);
                Thread.sleep(1000);
                synchronized (this)
                {
                    while (suspendFlag)
                    {
                        wait();
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception in " + getName());
        }
        System.out.println(getName() + " is finished.");
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

public class ThreadGroupDemo
{
    public static void main(String args[])
    {
        ThreadGroup groupA = new ThreadGroup("Group A");
        ThreadGroup groupB = new ThreadGroup("Group B");

        NewThread ob1 = new NewThread("One", groupA);
        NewThread ob2 = new NewThread("Two", groupA);
        NewThread ob3 = new NewThread("Three", groupB);
        NewThread ob4 = new NewThread("Four", groupB);

        System.out.println("\nOutput of method list():");
        groupA.list();
        groupB.list();
        System.out.println();

        System.out.println("Time-stopping for group A");
        Thread tga[] = new Thread[groupA.activeCount()];
        groupA.enumerate(tga);	// получить массив потоков группы
        for (int i = 0; i < tga.length; i++)
        {
            ((NewThread) tga[i]).mysuspend();	// приостановить каждый поток
        }
        try
        {
            Thread.sleep(4000);
        }
        catch (InterruptedException e)
        {
            System.out.println("Main thread is interrupted.");
        }

        System.out.println("Continue executing of the threads of a group A");
        for (int i = 0; i < tga.length; i++)
        {
            ((NewThread) tga[i]).myresume();	// продолжить потоки группы
        }

        // ждать завершения потоков
        try
        {
            System.out.println("Wait for exitting of child threads.");
            ob1.join();
            ob2.join();
            ob3.join();
            ob4.join();
        }
        catch (Exception e)
        {
            System.out.println("Exception in main thread");
        }

        System.out.println("Main thread is finished.");
    }
}

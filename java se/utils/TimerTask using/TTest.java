// ������������ ���������� Timer � TimerTask.
import java.util.*;

class MyTimerTask extends TimerTask
{
    public void run()
    {
        System.out.println("Timer task executed.");
    }
}

public class TTest
{
    public static void main(String args[])
    {
        TimerTask myTask = new MyTimerTask();
        Timer myTimer = new Timer();

        /* ������������� ��������� ����� � 1 �������,
        ����� ����������� ������ ����������. */
        myTimer.schedule(myTask, 1000, 500);

        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException exc)
        {
        }

        myTimer.cancel();
    }
}

// � ���� ������ ��� ����������� �������, ���������� � ������� ������� �����������,
// ������������ ��������� �����.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ThreadStopWatch
{
    private JLabel jlab; // ����������� �������, ���������� � ������� ������� �����������.
    private JButton jbtnStart; // ������ �����������.
    private JButton jbtnStop;  // ��������� �����������.
    private long start; // ����� ������� �����������, ���������� � �������������.
    private boolean running = false; // �������� true, ���� ���������� �������.
    // ������ �� �����, ������������� �����, ��������� � ������� ������� �����������.
    private Thread thrd;

    public ThreadStopWatch()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Thread-based Stopwatch");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(230, 90);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� �����, ������������ �����, ��������� � ������� ������� �����������.
        jlab = new JLabel("Press Start to begin timing.");

        // �������� ����� Start � Stop.
        jbtnStart = new JButton("Start");
        jbtnStop = new JButton("Stop");

        // ������������� ������ � ������ Stop ��������.
        jbtnStop.setEnabled(false);

        // �������� ���������� ������ Runnable, �������������� � ��������� ������.
        Runnable myThread = new Runnable()
        {
            // ������ ����� ����������� � ��������� ������.
            @Override
            public void run()
            {
                try
                {
                    // ����������� ������� ������ ��� � �������.
                    for (;;)
                    {
                        // ����� ������������� � ���� ������� �������.
                        Thread.sleep(100);
                        // ����� ������ updateTime() � ������ ��������� �������.
                        SwingUtilities.invokeLater(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                updateTime();
                            }

                        });
                    }
                }
                catch (InterruptedException exc)
                {
                    System.out.println("Call to sleep was interrupted.");
                    System.exit(1);
                }
            }

        };

        // �������� ������ ������.
        thrd = new Thread(myThread);

        // ������ ������.
        thrd.start();

        // ���������� ������������ ������� � �������� Start � Stop.
        jbtnStart.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // ������ ������� ������� �������.
                start = Calendar.getInstance().getTimeInMillis();
                // ��������� ��������� ������ �� ��������.
                jbtnStop.setEnabled(true);
                jbtnStart.setEnabled(false);
                // ������ �����������.
                running = true;
            }

        });

        jbtnStop.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                long stop = Calendar.getInstance().getTimeInMillis();
                // ���������� �������, ���������� � ������� �������.
                jlab.setText("Elapsed time is " + (double) (stop - start) / 1000);
                // ��������� ��������� ������ �� ��������.
                jbtnStart.setEnabled(true);
                jbtnStop.setEnabled(false);
                // ��������� �����������.
                running = false;
            }

        });

        // ��������� ������ � ����� � ������ ������ �����������.
        jfrm.getContentPane().add(jbtnStart);
        jfrm.getContentPane().add(jbtnStop);
        jfrm.getContentPane().add(jlab);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    // ���������� �������� �������, ���������� � ������� ������� �����������.
    // ������ ����� ����������� � ������ ��������� �������.
    private void updateTime()
    {
        if ( ! running)
        {
            return;
        }
        long temp = Calendar.getInstance().getTimeInMillis();
        jlab.setText("Elapsed time is " + (double) (temp - start) / 1000);
    }

    public static void main(String args[])
    {
        // ����� ��������� � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new ThreadStopWatch();
            }

        });
    }

}
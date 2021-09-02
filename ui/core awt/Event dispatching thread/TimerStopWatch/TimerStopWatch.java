// ������ ���������-�����������, ������������ ����� Timer.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class TimerStopWatch
{
    private JLabel jlab; // ����������� �������, ���������� � ������� ������� �����������.
    private JButton jbtnStart; // ������ �����������.
    private JButton jbtnStop;  // ��������� �����������.
    private long start; // ����� ������� �����������, ���������� � �������������.
    private Timer swTimer; // ��� ���������� �������� ������� ������������ ����� Timer.

    public TimerStopWatch()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Timer-based Stopwatch");

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
        jbtnStop.setEnabled(false);

        // ����������, ���������� ����������� � �������, ��������������� ��������.
        ActionListener timerAL = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                updateTime();
            }

        };

        // �������� �������, ������������� ������� ������ ��� � �������. ��������� �������
        // �������������� ������������, ��������� � ������� ��������� timerAL.
        swTimer = new Timer(100, timerAL);

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
                swTimer.start();
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
                swTimer.stop();
            }

        });

        // ��������� ������ � ����� � ������ ������ �����������.
        jfrm.getContentPane().add(jbtnStart);
        jfrm.getContentPane().add(jbtnStop);
        jfrm.getContentPane().add(jlab);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    // ���������� ������������� �������� �������. �������� �������� �� ��,
    // ��� ���������� running ������ �� �����.
    private void updateTime()
    {
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
                new TimerStopWatch();
            }

        });
    }

}
// ������� ����������.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class StopWatch implements ActionListener
{
    private JLabel jlab;
    private long start;	// �������� ����� ������� � �������������.

    public StopWatch()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("A Simple Stopwatch");

        // ��������� ���������� ���������� FlowLayout
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� ������� ������
        jfrm.setSize(230, 90);

        // ���������� ��������� ��� �������� ������������� ����.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ���� ������
        JButton jbtnStart = new JButton("Start");
        JButton jbtnStop = new JButton("Stop");

        // ���������� � �������� ������������ �������.
        jbtnStart.addActionListener(this);
        jbtnStop.addActionListener(this);

        // ��������� ������ � ������ ������ �����������.
        jfrm.getContentPane().add(jbtnStart);
        jfrm.getContentPane().add(jbtnStop);

        // �������� ��������� �����.
        jlab = new JLabel("Press Start to begin timing.");

        // ���������� ����� � ������.
        jfrm.getContentPane().add(jlab);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    // ��������� �������, ��������� � �������.
    public void actionPerformed(ActionEvent ae)
    {
        Calendar cal = Calendar.getInstance();	// ��������� �������� ���������� �������.
        if (ae.getActionCommand().equals("Start"))
        {
            // ���������� ������� �������.
            start = cal.getTimeInMillis();
            jlab.setText("Stopwatch is running...");
        }
        else
        {
            // ���������� �������, ���������� �� ������� �� ���������.
            jlab.setText("Elapsed time is " + (double) (cal.getTimeInMillis() - start) / 1000);
        }
    }

    public static void main(String args[])
    {
        // �������� ������ � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new StopWatch();
            }
        });
    }
}
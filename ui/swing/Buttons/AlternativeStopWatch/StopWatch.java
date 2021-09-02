// ������� ����������.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class StopWatch implements ActionListener
{
    private JLabel jlab;
    private long start;	// �������� ����� ������� � �������������.
    private JButton jbtnAction;

    public StopWatch()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("A Simple Stopwatch");

        // ��������� ���������� ���������� FlowLayout
        jfrm.setLayout(new FlowLayout());

        // ��������� ��������� ������� ������
        jfrm.setSize(230, 90);

        // ���������� ��������� ��� �������� ������������� ����.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ����� ������ ��� ���� ��������� ��������
        jbtnAction = new JButton("Start");

        // ���������� � ������� ����������� ������� ��������.
        jbtnAction.addActionListener(this);

        // ��������� ������ � ������ ������ �����������.
        jfrm.add(jbtnAction);

        // �������� ��������� �����.
        jlab = new JLabel("Press Start to begin timing.");

        // ���������� ����� � ������.
        jfrm.add(jlab);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    // ��������� �������, ��������� � �������.
    public void actionPerformed(ActionEvent ae)
    {
        Calendar cal = Calendar.getInstance();	// ��������� �������� ���������� �������.
        if (ae.getActionCommand().equals("Start"))
        {
            start = cal.getTimeInMillis();
            jbtnAction.setText("Stop");
            jlab.setText("Stopwatch is running...");
        }
        else if (ae.getActionCommand().equals("Stop"))
        {
            jbtnAction.setText("Start");
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
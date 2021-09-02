// ����� ���� � ������� ������������� ����������.

import java.awt.FlowLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SpinDates
{
    private JSpinner jspin;
    private JLabel jlab;

    public SpinDates()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Spin Dates");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(300, 120);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ������� Calendar, ������� ������������ ������� ����. ������������
        // ���� ������ ����� ����������� ��� ������������ ������ ���������.
        GregorianCalendar g = new GregorianCalendar();

        // ��������� ������� ����.
        Date curDate = new Date();

        // ��������� ������ ������� ���������, ������ ������� ���� ����� ���� �����.
        g.add(Calendar.MONTH, -1);
        Date begin = g.getTime();
        g.add(Calendar.MONTH, 2);
        Date end = g.getTime();

        // �������� ������ ������������� ����������, � ������� ������������
        // �������������� ����� �������� ����.
        SpinnerDateModel spm = new SpinnerDateModel(curDate, begin, end, Calendar.HOUR);

        // �������� ������������� ����������, ������������� �������������� ������.
        jspin = new JSpinner(spm);

        // �������� ����� ��� ����������� ��������� ����.
        jlab = new JLabel(" Selected date is: " + curDate);

        // ���������� ����������� ������� ��������� ��������� � ������������ �����������.
        jspin.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent ce)
            {
                // ��������� ��������� ����.
                Date date = (Date) jspin.getValue();
                // ����������� ��������� ����.
                jlab.setText(" Selected date is: " + date + " ");
            }
        });

        // ��������� ������������� ���������� � ����� � ������ ������ �����������.
        jfrm.getContentPane().add(jspin);
        jfrm.getContentPane().add(jlab);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // Create the frame on the event dispatching thread.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new SpinDates();
            }
        });
    }
}
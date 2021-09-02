// ����� ������������� �������� � ������� ������������� ����������.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SpinInts
{
    private JSpinner jspin;
    private JLabel jlab;

    public SpinInts()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Spin Integers");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(200, 120);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ������, ������������ ��� ��������� ����� �����.
        SpinnerNumberModel spm = new SpinnerNumberModel(1, 1, 10, 1);

        // �������� ���������� JSpinner, ������������� �������������� ����� ������.
        jspin = new JSpinner(spm);

        // ��������� ���������������� �������� ������������� ����������.
        jspin.setPreferredSize(new Dimension(40, 20));

        // �������� �����, ������������ ����� ������������.
        jlab = new JLabel(" Current border size is: 1 ");
        jlab.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // ���������� ����������� ������� ��������� ��������� � ������������ �����������.
        jspin.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent ce)
            {
                // ��������� �������� �������.
                Integer bSize = (Integer) jspin.getValue();
                // ����������� �������� � ������� �������.
                jlab.setText(" Current border size is: " + bSize + " ");
                // ��������� ����� ������ �����. ������� ����� ������������ ���������, ���������
                // ������������� � ������� ������������� ����������.
                jlab.setBorder(BorderFactory.createLineBorder(Color.BLACK, bSize.intValue()));
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
        // �������� ������ � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new SpinInts();
            }
        });
    }
}
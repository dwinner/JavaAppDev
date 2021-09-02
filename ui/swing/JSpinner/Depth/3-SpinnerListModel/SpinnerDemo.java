// ������������ ������ ������������� ����������,
// ����������� �� ������ SpinnerListModel.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SpinnerDemo
{
    private JSpinner jspin;
    private JLabel jlab;
    // �������� ������� ������ RGB.
    private String colors[] =
    {
        "Red", "Green", "Blue"
    };

    public SpinnerDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("SpinnerListModel");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(220, 100);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ������, �������������� ������ �����.
        SpinnerListModel spm = new SpinnerListModel(colors);

        // �������� ���������� JSpinner � �������� ������.
        jspin = new JSpinner(spm);

        // ��������� ���������������� �������� ������������� ����������.
        jspin.setPreferredSize(new Dimension(60, 20));

        // �������� �����, ������� ���������� ����� ������������. ������ �����
        // ��������� ������� �����. ��������� Red - ������ ����� ������, �� ����������
        // �� ��������� ��� �������� ������������� ����������.
        jlab = new JLabel(" Current selection is: Red ");
        jlab.setBorder(BorderFactory.createLineBorder(Color.RED, 4));

        // ���������� ����������� ������� ��������� ��������� � ������������ �����������.
        jspin.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent ce)
            {
                // ��������� ���������� ��������.
                String color = (String) jspin.getValue();
                // ����������� ���������� �������� � ������� �����.
                jlab.setText(" Current selection is: " + color + " ");
                // ������������ ����� ������ �����. ����� ������������ ������, ��������� �������������.
                switch (color)
                {
                    case "Red":
                        jlab.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
                        break;
                    case "Green":
                        jlab.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
                        break;
                    default:
                        jlab.setBorder(BorderFactory.createLineBorder(Color.BLUE, 4));
                        break;
                }
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
        // ����� ��������� � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new SpinnerDemo();
            }
        });
    }
}
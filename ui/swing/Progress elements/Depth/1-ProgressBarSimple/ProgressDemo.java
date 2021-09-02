// ������������ ������ ���������� ���� ��������.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ProgressDemo
{
    private JLabel jlabVert;
    private JLabel jlabHoriz;
    private JProgressBar jprogHoriz;
    private JProgressBar jprogVert;
    private JButton jbtn;

    public ProgressDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Demonstrate Progress Bars");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(280, 270);

        // ���������� ��������� ��� �������� ���������� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ����������� ���� �������� � ������������ � �������������� �����������
        jprogVert = new JProgressBar(JProgressBar.VERTICAL);
        jprogHoriz = new JProgressBar(); // �� ��������� ����������� �������������� ����������

        // ����������� ������, �������������� ����� ����������� ������ � ���������.
        jprogVert.setStringPainted(true);
        jprogHoriz.setStringPainted(true);

        jbtn = new JButton("Push Me");

        // �����, ������������ ������� �������� ����������� ���� ��������.
        jlabHoriz = new JLabel("Value of horizontal progress bar: " + jprogHoriz.getValue());
        jlabVert = new JLabel("Value of vertical progress bar: " + jprogVert.getValue());

        // ���������� �������� ���������� ���� �������� �� ������ �� ������ Push Me.
        // ���� �������� ���������� �������� ���������, ������� �������� �� ���������������.
        jbtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                int hVal = jprogHoriz.getValue();
                int vVal = jprogVert.getValue();

                if (hVal >= jprogHoriz.getMaximum())
                {
                    return;
                }
                else
                {
                    jprogHoriz.setValue(hVal + 10);
                }

                if (vVal >= jprogHoriz.getMaximum())
                {
                    return;
                }
                else
                {
                    jprogVert.setValue(vVal + 10);
                }

                jlabHoriz.setText("Value of horizontal progress bar: " + jprogHoriz.getValue());
                jlabVert.setText("Value of vertical progress bar: " + jprogVert.getValue());
            }
        });

        // ���������� ����������� � ������ �����������.
        jfrm.getContentPane().add(jprogHoriz);
        jfrm.getContentPane().add(jprogVert);
        jfrm.getContentPane().add(jlabHoriz);
        jfrm.getContentPane().add(jlabVert);
        jfrm.getContentPane().add(jbtn);

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
                new ProgressDemo();
            }
        });
    }
}
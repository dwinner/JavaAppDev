// ������������ ������������� �����, ��������� �� �����, � ���������� ����������.

import java.awt.*;
import javax.swing.*;

public class BorderDemo
{
    BorderDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Use Line and Etched Borders");

        // ���������� � ����������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ���������� ������� ������.
        jfrm.setSize(280, 90);

        // ���������� ��������� �� �������� ������������� ����������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ����� � ��������� ��� ��� �����, ��������� �� �����.
        JLabel jlab = new JLabel(" This uses a line border. ");
        // ���������� ���������� � jlab.
        jlab.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // �������� ������ ����� � ��������� ��� ��� ��������� �����.
        JLabel jlab2 = new JLabel(" This uses an etched border. ");
        // ���������� ���������� � jlab2.
        jlab2.setBorder(BorderFactory.createEtchedBorder());

        // ��������� ����� � ������ ������ �����������.
        jfrm.getContentPane().add(jlab);
        jfrm.getContentPane().add(jlab2);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // �������� ������ � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new BorderDemo();
            }
        });
    }
}
// ������������� ������������� �����������

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MnemDemo
{
    public MnemDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Demonstrate Mnemomics");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ���������� ������� ������.
        jfrm.setSize(260, 140);

        // ���������� ��������� ��� �������� ���������� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ���� �����.
        JLabel jlab1 = new JLabel("E-mail Address");
        JLabel jlab2 = new JLabel("Name");

        // ���������� ������������� ����������� ������.
        jlab1.setDisplayedMnemonic('e');
        jlab2.setDisplayedMnemonic('n');

        // �������� ���� ����� ��������������.
        JTextField jtf1 = new JTextField(20);
        JTextField jtf2 = new JTextField(20);

        // ���������� ����� � ������������.
        jlab1.setLabelFor(jtf1);
        jlab2.setLabelFor(jtf2);

        // ���������� ������ �������� ����� ��������������.
        jtf1.setActionCommand("jtf1");
        jtf2.setActionCommand("jtf2");

        // ��������� ����� �������������� � ����� � ������ ������ �����������.
        jfrm.getContentPane().add(jlab1);
        jfrm.getContentPane().add(jtf1);
        jfrm.getContentPane().add(jlab2);
        jfrm.getContentPane().add(jtf2);

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
                new MnemDemo();
            }

        });
    }

}
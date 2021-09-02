// ������������� ���������� JScrollPane ��� ��������� ����������� JPanel.

import java.awt.*;
import javax.swing.*;

public class ScrollJPanelDemo
{
    public ScrollJPanelDemo()
    {
        // �������� ������ ���������� JFrame.
        // ��� ���� ����������� ��������� ���������� �� ���������.
        JFrame jfrm = new JFrame("Scroll a JPanel");

        // ��������� ��������� �������� ������.
        jfrm.setSize(280, 130);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� �����.
        JLabel jlabOptions = new JLabel("Select one or more options: ");

        // �������� ������� �����.
        JCheckBox jcbOpt1 = new JCheckBox("Option One");
        JCheckBox jcbOpt2 = new JCheckBox("Option Two");
        JCheckBox jcbOpt3 = new JCheckBox("Option Three");
        JCheckBox jcbOpt4 = new JCheckBox("Option Four");
        JCheckBox jcbOpt5 = new JCheckBox("Option Five");

        // � ������ ������� ����������� ������� �� ������������.

        // �������� ������� JPanel, � ������� ����� ����������� ������ �����.
        JPanel jpnl = new JPanel();
        jpnl.setLayout(new GridLayout(6, 1));
        jpnl.setOpaque(true);

        // ��������� ������� ����� � ����� � ������ JPanel.
        jpnl.add(jlabOptions);
        jpnl.add(jcbOpt1);
        jpnl.add(jcbOpt2);
        jpnl.add(jcbOpt3);
        jpnl.add(jcbOpt4);
        jpnl.add(jcbOpt5);

        // �������� ������ � ����������.
        JScrollPane jscrlp = new JScrollPane(jpnl);

        // ���������� ������ � ���������� � ������.
        jfrm.getContentPane().add(jscrlp);

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
                new ScrollJPanelDemo();
            }
        });
    }
}
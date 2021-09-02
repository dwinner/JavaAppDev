// ���������� � ������ ��������� ���������� � �����.

import java.awt.*;
import javax.swing.*;

public class AddHeadersDemo
{
    private JCheckBox jcbOpt1;
    private JCheckBox jcbOpt2;
    private JCheckBox jcbOpt3;
    private JCheckBox jcbOpt4;
    private JCheckBox jcbOpt5;

    public AddHeadersDemo()
    {
        // �������� ������ ���������� JFrame. ��� ���� ����������� 
        // ��������� ���������� �� ���������.
        JFrame jfrm = new JFrame("Use Headers");

        // ��������� ��������� �������� ������.
        jfrm.setSize(280, 140);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� �����.
        JLabel jlabOptions = new JLabel("Select one or more options: ");

        // �������� ������� �����.
        jcbOpt1 = new JCheckBox("Option One");
        jcbOpt2 = new JCheckBox("Option Two");
        jcbOpt3 = new JCheckBox("Option Three");
        jcbOpt4 = new JCheckBox("Option Four");
        jcbOpt5 = new JCheckBox("Option Five");

        // � ������ ������� ����������� ������� �� �����.

        // �������� ���������� JPanel ��� ���������� ������� �����.
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

        // �������� ����� ������ ������� ���������.
        jscrlp.setViewportBorder(BorderFactory.createLineBorder(Color.BLACK));

        // �������� �����, ������������ ��� ����������.
        JLabel jlabCH = new JLabel("Configuration Center", SwingConstants.CENTER);
        JLabel jlabRH = new JLabel("<html>C<br />h<br />o<br />o<br />s<br />e", SwingConstants.CENTER);
        jlabRH.setPreferredSize(new Dimension(20, 200));

        // ������������ ���������� ������ � �������.
        jscrlp.setColumnHeaderView(jlabCH);
        jscrlp.setRowHeaderView(jlabRH);

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
                new AddHeadersDemo();
            }
        });
    }
}
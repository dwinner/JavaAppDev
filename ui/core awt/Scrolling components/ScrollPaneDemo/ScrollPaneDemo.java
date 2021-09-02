// ������� ������ ������������� JScrollPane.

import javax.swing.*;

public class ScrollPaneDemo
{
    public ScrollPaneDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Use JScrollPane");

        // ��������� ��������� �������� ������.
        jfrm.setSize(200, 120);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ����� � HTML-���������� �������� ������.
        JLabel jlab =
                new JLabel("<html>JScrollPane simplifies what would<br />"
                + "otherwise be complicated tasks.<br />"
                + "It can be used to scroll any component<br />"
                + "or lightweight container. It is especially<br />"
                + "useful when scrolling tables, lists,<br />"
                + "or images.");

        // �������� ������ � ���������� � �������� �� �����.
        JScrollPane jscrlp = new JScrollPane(jlab);

        // ��������� ������ � ���������� � ������ ������ ����������� ������.
        jfrm.getContentPane().add(jscrlp);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // ����� ��������� � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new ScrollPaneDemo();
            }
        });
    }
}
/*
 * ������ ������������� JSplitPane.
 */

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

public class SplitPaneDemo
{
    public SplitPaneDemo()
    {
        // �������� ������ ���������� JFrame. ��� ���� ����������� ���������
        // ���������� �� ���������.
        JFrame jfrm = new JFrame("Split Pane Demo");

        // ��������� ��������� �������� ������.
        jfrm.setSize(380, 150);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ���� �����, ��������������� ��� ����������� � ������� ����������� ������.
        JLabel jlab = new JLabel(" Left side: ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        JLabel jlab2 = new JLabel(" Right side: ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        // ��������� ����������� �������� ������ �����. ���� ��� �� ��������
        // ��������� �����������, �� ��������� ���������� �������� ���������
        // ��������� �����������.
        jlab.setMinimumSize(new Dimension(90, 30));
        jlab2.setMinimumSize(new Dimension(90, 30));

        // ��������� ����������� ������, ���������� �����.
        JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, jlab, jlab2);
        jsp.setOneTouchExpandable(true);

        // ��������� ����������� ������ � ������ ������ �����������.
        jfrm.getContentPane().add(jsp);

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
                new SplitPaneDemo();
            }
        });
    }
}
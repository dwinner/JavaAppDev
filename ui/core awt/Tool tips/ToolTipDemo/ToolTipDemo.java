// ������, ��������������� ������������� ����� ���������.

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ToolTipDemo
{
    public ToolTipDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Add Tooltips");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(300, 150);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ���� ������.
        JButton jbtnAlpha = new JButton("Alpha");
        JButton jbtnBeta = new JButton("Beta");

        // ��������� ����� ��������� ��� ������.
        jbtnAlpha.setToolTipText("This activates the Alpha option.");
        jbtnBeta.setToolTipText("This activates the Beta option.");

        jfrm.getContentPane().add(jbtnAlpha);
        jfrm.getContentPane().add(jbtnBeta);

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
                new ToolTipDemo();
            }
        });
    }
}
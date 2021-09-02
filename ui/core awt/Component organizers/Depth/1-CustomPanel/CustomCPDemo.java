/*
 * �������� ������� JPanel � ������������� ��� � �������� ������ �����������.
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * ������ ����� ������� ������, ����������� JPanel. ��� ������������ � �������� ������ �����������. � ���� ������ ��
 * ������������� ����� �������������� �����������, �� ��� ��������� ����� ����������� �� ���� �������, � �������
 * ��������� ������������� JPanel.
 */
class MyContentPanel extends JPanel
{
    private JLabel jlab;
    private JButton jbtnRed;
    private JButton jbtnBlue;

    MyContentPanel()
    {
        // ������ ������ ���� ������������.
        setOpaque(true);

        // ��������� ����� �������� ����� �������� � 5 ��������.
        setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));

        // �������� �����.
        jlab = new JLabel("Select Border Color");

        // �������� ���� ������.
        jbtnRed = new JButton("Red");
        jbtnBlue = new JButton("Blue");

        // ���������� ������������ ������� � ��������.
        jbtnRed.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                setBorder(BorderFactory.createLineBorder(Color.RED, 5));
            }
        });

        jbtnBlue.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
            }
        });

        // ��������� ������ � ����� � ������ ������.
        add(jbtnRed);
        add(jbtnBlue);
        add(jlab);
    }
}

/**
 * �������� ���������� �������� ������ � ������������� ������, ��������� � ������� MyContentPanel, � �������� ������
 * �����������.
 * <p/>
 * @author oracle_pr1
 */
public class CustomCPDemo
{
    public CustomCPDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Set the Content Pane");

        // ��������� ��������� �������� ������.
        jfrm.setSize(240, 150);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ���������� ���������������� ������ �����������.
        MyContentPanel mcp = new MyContentPanel();

        // ��������� mcp � �������� ������ �����������.
        jfrm.setContentPane(mcp);

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
                new CustomCPDemo();
            }
        });
    }
}
// ������ ������������� ��������������� ������.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ComboBoxDemo
{
    private JComboBox<String> jcbb;
    private JLabel jlab;

    // �������� ������� � ���������� ������ �����.
    private String apples[] =
    {
        "Winesap",
        "Cortland",
        "Red Delicious",
        "Golden Delicious",
        "Gala",
		"Fuji",
        "Granny Smith",
        "Jonathan"
    };

    public ComboBoxDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("JComboBox Demo");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(220, 240);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ���������� JComboBox �� ������ ������� �����.
        jcbb = new JComboBox<String>(apples);

        // �������� �����, ������������ ����� ������������.
        jlab = new JLabel();

        // ���������� ����������� ������� � �������������� �������.
        jcbb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent le)
            {
                // ��������� ������ �� ��������� �����.
                String item = (String) jcbb.getSelectedItem();
                // ����������� ���������� ������.
                jlab.setText("Current selection " + item);
            }
        });

        // ������������� � ������ ���������� ������ �����.
        jcbb.setSelectedIndex(0);

        // ���������� ��������������� ������ � ����� � ������ �����������.
        jfrm.getContentPane().add(jcbb);
        jfrm.getContentPane().add(jlab);

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
                new ComboBoxDemo();
            }
        });
    }
}
// ������������ ���������� � �������� ������� ��������������� ������.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DynamicComboBox
{
    private JComboBox jcbb;
    private JLabel jlab;
    private JButton jbtnRemove;

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

    public DynamicComboBox()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Dynamic JComboBox");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(220, 240);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ���������� JComboBox
        jcbb = new JComboBox(apples);

        // ��������� ������� �������������� � ������ ��������������� ������.
        jcbb.setEditable(true);

        // �������� �����, ������������ ����� ������������.
        jlab = new JLabel();

        // ���������� ����������� ������� � �������������� �������. ����� �����,
        // ��������� �������������, ����������� � ������.
        jcbb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent le)
            {
                // ��������� ������ �� ��������� �����.
                String item = (String) jcbb.getSelectedItem();

                // ���� �� ���� ����� �� ������, ������� �������� �� �����������.
                if (item == null)
                    return;

                // ����������� ���������� ������.
                jlab.setText("Current selection " + item);

                // ���� ������ ����� ����������� � ������, �� ���������� � ��� ������.
                int i;

                // �������� ������� ������ � ������.
                for (i = 0; i < jcbb.getItemCount(); i++)
                {
                    if (item.equals(jcbb.getItemAt(i)))
                    { // ����� ���� � ������.
                        break;
                    }
                }

                // ���� ������ ��� � ������, �� �����������.
                if (i == jcbb.getItemCount()) 
                    jcbb.addItem(item);
            }
        });

        // ������������� � ������ ���������� ������ �����.
        jcbb.setSelectedIndex(0);

        // �������� ������ Remove Selection.
        jbtnRemove = new JButton("Remove Selection");

        // ���������� ����������� ������� � �������.
        jbtnRemove.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent le)
            {
                // ��������� ������ �� ��������� �����.
                String item = (String) jcbb.getSelectedItem();

                // ���� �� ���� ����� �� ������, ������� �������� �� �����������.
                if (item == null)
                    return;

                // �������� ���������� ������ �� ������.
                jcbb.removeItem(item);

                // ����������� ���������� ������.
                jlab.setText("Removed " + item);
            }
        });

        // ��������� ��������������� ������, ����� � ������ � ������ ������ �����������.
        jfrm.getContentPane().add(jcbb);
        jfrm.getContentPane().add(jlab);
        jfrm.getContentPane().add(jbtnRemove);

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
                new DynamicComboBox();
            }
        });
    }
}
// ������ ������������� ���������� JList.

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListDemo
{
    private JList<String> jlst;
    private JLabel jlab;
    private JScrollPane jscrlp;
    private JButton jbtnBuy;
    // �������� ������� � ������ ������ �����.
    private String apples[] =
    {
        "Winesap", "Cortland", "Red Delicious",
        "Golden Delicious", "Gala", "Fuji",
        "Granny Smith", "Jonathan"
    };

    public ListDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("JList Demo");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(204, 200);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ���������� JList �� ������ �������, ���������� �� ����� ������.
        jlst = new JList<>(apples);

        // ��������� ������, ������������ �������� ������ ���� ����� ������.
        jlst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // ��������� ������ � ������ ������ � ����������.
        jscrlp = new JScrollPane(jlst);

        // ��������� ���������������� �������� ������ � ����������.
        jscrlp.setPreferredSize(new Dimension(120, 90));

        // �������� �����, ������������ ����� ������������.
        jlab = new JLabel("Please Choose an Apple");

        // ���������� �� ������� ����������� �������.
        jlst.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent le)
            {
                // ��������� ������� �������� ���������� ������.
                int idx = jlst.getSelectedIndex();
                // ���� ������������ ������ �����, ������������ ���������� � ������.
                if (idx != -1)
                {
                    jlab.setText("Current selection " + apples[idx]);
                }
                else // � ��������� ������ ����� ��������� ����������� ������� �����.
                {
                    jlab.setText("Please Choose an Apple");
                }
            }
        });

        // ������������ ������ Buy Apple.
        jbtnBuy = new JButton("Buy Apple");

        // ���������� ����������� ������� � ������� Buy Apple.
        jbtnBuy.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // ��������� ������� ������.
                int idx = jlst.getSelectedIndex();
                // ���� ����� ������ ��� ������, ��������� ���������� � ��.
                if (idx != -1)
                {
                    jlab.setText("You purchased " + apples[idx]);
                }
                else // � ��������� ������ ��������� ��������� � ������������ ���������.
                {
                    jlab.setText("No apple has been selected.");
                }
            }
        });

        // ��������� ������ � ����� � ������ ������ �����������.
        jfrm.getContentPane().add(jscrlp);
        jfrm.getContentPane().add(jbtnBuy);
        jfrm.getContentPane().add(jlab);

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
                new ListDemo();
            }
        });
    }
}
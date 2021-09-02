// ����� ���������� ������� ������.

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MultiRangeList
{
    private JList<String> jlst;
    private JLabel jlab;
    private JScrollPane jscrlp;
    private JButton jbtnBuy;
    
    // �������� ������� � ���������� ������ �����.
    private String apples[] =
    {
        "Winesap", "Cortland", "Red Delicious",
        "Golden Delicious", "Gala", "Fuji",
        "Granny Smith", "Jonathan"
    };

    public MultiRangeList()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Multiple Range");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(180, 240);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ���������� JList. �� ��������� ������ ���������
        // ����� ������� � ���������� ����������.
        jlst = new JList<>(apples);

        // ��������� ������ � ������ ������ � ����������.
        jscrlp = new JScrollPane(jlst);

        // ��������� ���������������� �������� ������ � ����������.
        jscrlp.setPreferredSize(new Dimension(120, 90));

        // �������� �����, ������������ ����� ������������.
        jlab = new JLabel("Please Choose an Apple");

        // ���������� ����������� ������� �� �������.
        jlst.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent le)
            {
                String what = "";

                // ��������� ������� � ��������� ��������� �������.
                int indices[] = jlst.getSelectedIndices();

                // ��������, ��� �� ������ ���� �� ���� �����. ���� ����� �������
                // ����� ����, �� ���� ����� �� ��� ������.
                if (indices.length == 0)
                {
                    jlab.setText("Please Choose an Apple.");
                    return;
                }

                // ����������� ��������� �������.
                for (int i = 0; i < indices.length; i++)
                {
                    what += apples[indices[i]] + "<br />";
                }

                jlab.setText("<html>Current selection:<br />" + what);
            }
        });

        // �������� ������ Buy Apple.
        jbtnBuy = new JButton("Buy Apple");

        // ���������� � ������� Buy Apple ����������� �������.
        jbtnBuy.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                String what = "";

                // ��������� �������� ��������� �������.
                int indices[] = jlst.getSelectedIndices();

                // ��������, ��� �� ������ ���� �� ���� ����� ������.
                if (indices.length == 0)
                {
                    jlab.setText("No apple has been selected.");
                    return;
                }

                // ����������� ��������� �������.
                for (int i = 0; i < indices.length; i++)
                {
                    what += apples[indices[i]] + "<br />";
                }

                jlab.setText("<html>Apples Purchased:<br />" + what);
            }
        });

        // ���������� ������ � ����� � ������ �����������.
        jfrm.getContentPane().add(jscrlp);
        jfrm.getContentPane().add(jbtnBuy);
        jfrm.getContentPane().add(jlab);

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
                new MultiRangeList();
            }
        });
    }
}
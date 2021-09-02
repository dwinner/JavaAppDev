// ������������� ���������� ListModel

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListModelDemo
{
    private JList<String> jlst;
    private JLabel jlab;
    private JScrollPane jscrlp;
    private JButton jbtnBuy;
    private JButton jbtnAddDel;

    public ListModelDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("JList ModelDemo");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(180, 240);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� � ���������� ������ ������.
        //
        // ������� ��������� ��������� DefaultListModel.
        DefaultListModel<String> lm = new DefaultListModel<>();

        // ����� � ������ ����������� ������.
        lm.addElement("Winesap");
        lm.addElement("Cortland");
        lm.addElement("Red Delicious");
        lm.addElement("Golden Delicious");
        lm.addElement("Gala");

        // �������� ������� JList �� ��������� ������ ������.
        jlst = new JList<>(lm);

        // ��������� ������ � ������ � ����������.
        jscrlp = new JScrollPane(jlst);

        // ��������� ���������������� �������� ������ � ����������.
        jscrlp.setPreferredSize(new Dimension(120, 90));

        // �������� �����, ������������ ���������� ������.
        jlab = new JLabel("Please choose an apple.");

        // ���������� ������� ������ ��� ������.
        jlst.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent le)
            {
                String what = "";

                // ��������� ������� ������.
                @SuppressWarnings("deprecation")
                Object values[] = jlst.getSelectedValues();

                // ��������, ��� �� ������ ���� �� ���� �����.
                if (values.length == 0)
                {
                    jlab.setText("Please choose an apple.");
                    return;
                }

                // ����������� ��������� �������.
                for (int i = 0; i < values.length; i++)
                {
                    what += values[i] + "<br />";
                }

                jlab.setText("<html>Current selection:<br /><hr />" + what);
            }
        });

        // �������� ������ ��� "�������" ���������� �����.
        jbtnBuy = new JButton("Buy Apple");

        // ���������� ����������� ������� � ������� Buy Apple.
        jbtnBuy.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                String what = "";

                // ��������� ��������� �������.
                @SuppressWarnings("deprecation")
                Object values[] = jlst.getSelectedValues();

                // ��������, ��� �� ������ ���� �� ���� ����� ������.
                if (values.length == 0)
                {
                    jlab.setText("No apple has been selected.");
                    return;
                }

                // ����������� ��������� �������.
                for (int i = 0; i < values.length; i++)
                {
                    what += values[i] + "<br />";
                }

                jlab.setText("<html>Apples purchased:<br />" + what);
            }
        });

        // �������� ������, ����������� ������.
        jbtnAddDel = new JButton("Add More Varieties");

        // ���������� ����������� ������� � �������� Add More Varieties.
        jbtnAddDel.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // ��������� ������ �� ������.
                @SuppressWarnings("unchecked")
                DefaultListModel<String> lm = (DefaultListModel) jlst.getModel();

                // ��������, ���� �� ��� ��������� �������������� ������.
                if (lm.getSize() > 5)
                {
                    // ��� ������������� ���������� �������� ������ ���������.
                    for (int i = 7; i > 4; i--)
                    {
                        lm.remove(i);
                    }
                    jbtnAddDel.setText("Add More Varieties");
                }
                else
                {
                    // ���������� �������������� �������.
                    lm.addElement("Fuji");
                    lm.addElement("Granny Smith");
                    lm.addElement("Jonathan");
                    jbtnAddDel.setText("Remove Extra Varieties");
                }
            }
        });

        // ��������� ������ � ����� � ������ �����������.
        jfrm.getContentPane().add(jscrlp);
        jfrm.getContentPane().add(jbtnBuy);
        jfrm.getContentPane().add(jbtnAddDel);
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
                new ListModelDemo();
            }
        });
    }
}
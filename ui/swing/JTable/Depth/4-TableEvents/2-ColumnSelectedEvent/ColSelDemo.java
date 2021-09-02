/*
 * ��������� ������� ������ �������� �������.
 */

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;

public class ColSelDemo
{
    private String[] headings =
    {
        "From", "Address", "Subject", "Size"
    };
    private Object[][] data =
    {
        {
            "Wendy", "Wendy@HerbSchildt.com", "Hello Herb", new Integer(287)
        },
        {
            "Alex", "Alex@HerbSchildt.com", "Check this out!", new Integer(308)
        },
        {
            "Hale", "Hale@HerbSchildt.com", "Found a bug", new Integer(887)
        },
        {
            "Todd", "Todd@HerbSchildt.com", "Did you see this?", new Integer(223)
        },
        {
            "Steve", "Steve@HerbSchildt.com", "I'm back", new Integer(357)
        },
        {
            "Ken", "Ken@HerbSchildt.com", "Arrival time change", new Integer(512)
        }
    };
    private JTable jtabEmail;
    private JLabel jlab;

    public ColSelDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Column Selection Demo");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(500, 160);

        // ���������� ��������� ��� �������� ���� ��������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ����� ��� ����������� ���������� � ������ ������������.
        jlab = new JLabel();

        // �������� ������� ��� ����������� �������� ���������.
        jtabEmail = new JTable(data, headings);

        // ��������� ������ � ������ ������ � ����������.
        JScrollPane jscrlp = new JScrollPane(jtabEmail);

        // ��������� �������� �������������� ������� ���������.
        jtabEmail.setPreferredScrollableViewportSize(new Dimension(450, 80));

        // ���������� ������ �������� � ������ ������ �����.
        jtabEmail.setColumnSelectionAllowed(true);
        jtabEmail.setRowSelectionAllowed(false);

        // ��������� ������ ������ ��� ������ �������.
        // � ������� ������ ����������� ���������� �������.
        TableColumnModel tcm = jtabEmail.getColumnModel();
        ListSelectionModel lsmCol = tcm.getSelectionModel();

        // ��������� ������� ������.
        lsmCol.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent le)
            {
                String str = "Selected Columns: ";

                // ��������� �������� ��������� ��������.
                int[] cols = jtabEmail.getSelectedColumns();

                // ������������ ������, ���������� �������.
                for (int i = 0; i < cols.length; i++)
                {
                    str += cols[i] + " ";
                }

                // ����������� �������� ��������� ��������.
                jlab.setText(str);
            }
        });

        // ��������� ������� � ����� � ������ ������ �����������.
        jfrm.getContentPane().add(jscrlp);
        jfrm.getContentPane().add(jlab);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // ����� ��������� � ������ ��������� �������
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new ColSelDemo();
            }
        });
    }
}
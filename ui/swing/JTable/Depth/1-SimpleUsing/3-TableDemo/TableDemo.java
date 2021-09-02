/*
 * ������ ������������ ������� �������.
 */

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class TableDemo
{
    // ������, ���������� ��������� �������.
    private String[] headings =
    {
        "From", "Address", "Subject", "Size"
    };
    // ������, ���������� ������ �������.
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

    public TableDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Simple Table Demo");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(500, 160);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ������� ��� ����������� �������� ���������.
        // ��������� � ������ �������� ����������� ��������.
        jtabEmail = new JTable(data, headings);

        /*
         * ����� ��������. ������ �� ����� �����. jtabEmail.setColumnSelectionAllowed(true);
         * jtabEmail.setRowSelectionAllowed(false);
         */

        // ��������� ������� � ������ ������ � ����������.
        JScrollPane jscrlp = new JScrollPane(jtabEmail);

        // ��������� �������� �������������� ������� ���������.
        jtabEmail.setPreferredScrollableViewportSize(new Dimension(450, 80));

        // ��������� ������� � ������ ������ �����������.
        jfrm.getContentPane().add(jscrlp);

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
                new TableDemo();
            }
        });
    }
}
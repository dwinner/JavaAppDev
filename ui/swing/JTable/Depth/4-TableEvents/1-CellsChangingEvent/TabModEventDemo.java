/*
 * ������������ ��������� ������ �������.
 */

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class TabModEventDemo
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
    private JLabel jlab2;
    private TableModel tm;

    TabModEventDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Table Model Events Demo");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(500, 200);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ����� ��� ����������� ���������� � ������ ������������.
        jlab = new JLabel();
        jlab.setPreferredSize(new Dimension(400, 20));
        jlab.setHorizontalAlignment(SwingConstants.CENTER);

        // �������� ����� ��� ����������� ���������� �� ��������� ������.
        jlab2 = new JLabel();

        // �������� ������� ��� ����������� �������� ���������.
        jtabEmail = new JTable(data, headings);

        // ��������� ������ � ������ � ����������.
        JScrollPane jscrlp = new JScrollPane(jtabEmail);

        // ��������� �������� �������������� ������� ���������.
        jtabEmail.setPreferredScrollableViewportSize(new Dimension(450, 80));

        // ��������� ������ ������.
        ListSelectionModel listSelMod = jtabEmail.getSelectionModel();

        // ��������� ������� ������ �����.
        listSelMod.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent le)
            {
                String str = "Selected Row(s): ";

                // ��������� �������� ��������� �����.
                int[] rows = jtabEmail.getSelectedRows();

                // �������� ������ ��� ������������� ��������.
                for (int i = 0; i < rows.length; i++)
                {
                    str += rows[i] + " ";
                }

                // ����������� �������� ��������� �����.
                jlab.setText(str);
            }
        });

        // ��������� ������ �������.
        tm = jtabEmail.getModel();

        // ��������� ������� ��������� ������ �������.
        tm.addTableModelListener(new TableModelListener()
        {
            @Override
            public void tableChanged(TableModelEvent tme)
            {
                if (tme.getType() == TableModelEvent.UPDATE)
                {
                    // ����������� ��������� ������ � ������ ��������.
                    jlab2.setText("Cell " + tme.getFirstRow() + ", " + tme.getColumn() + " changed."
                        + " The new value: " + tm.getValueAt(tme.getFirstRow(), tme.getColumn()));
                }
            }
        });

        // ��������� ������� � ����� � ������ ������ �����������.
        jfrm.getContentPane().add(jscrlp);
        jfrm.getContentPane().add(jlab);
        jfrm.getContentPane().add(jlab2);

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
                new TabModEventDemo();
            }
        });
    }
}
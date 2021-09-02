/*
 * ������������� ����������� ������ ��� �������� �������.
 */

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UsingDefaultTableModel extends JFrame
{
    // ���� ������
    private DefaultTableModel dtm;

    public UsingDefaultTableModel() throws HeadlessException
    {
        super("Default Table Model");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ������� ����������� ������
        dtm = new DefaultTableModel();
        // ������ �������� ��������
        dtm.setColumnIdentifiers(new String[]
            {
                "�����", "�����", "����"
            });
        // ��������� ������ �������
        dtm.addRow(new String[]
            {
                "�1", "�������", "5"
            });
        dtm.addRow(new String[]
            {
                "�2", "�������", "175"
            });
        dtm.addRow(new String[]
            {
                "�3", "��������", "1.2"
            });
        // �������� ������ � �������
        JTable table = new JTable(dtm);

        // ������ ����� �������� �����������
        JButton add = new JButton("��������");
        add.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // ��������� ����� ������
                dtm.addRow(new String[]
                    {
                        "?", "�������!", "����� ����!"
                    });
            }
        });

        JButton remove = new JButton("�������");
        remove.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // ������� ��������� ������ (������ � ����)
                dtm.removeRow(dtm.getRowCount() - 1);
            }
        });

        // ��������� ������ � ������� � ������ �����������
        getContentPane().add(new JScrollPane(table));
        JPanel buttons = new JPanel();
        buttons.add(add);
        buttons.add(remove);
        getContentPane().add(buttons, BorderLayout.SOUTH);

        // ������� ���� �� �����
        setSize(200, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new UsingDefaultTableModel();
    }
}
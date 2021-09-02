/*
 * ������������� ����������� ������ �������� ������� � �������� TableColumn
 */

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class UsingTableColumnModel extends JFrame
{
    // ������ ��������
    private TableColumnModel columnModel;
    // �������� �������� �������
    private String[] columnNames =
    {
        "���", "������� ����", "�������"
    };
    // ������ ��� �������
    private String[][] data =
    {
        {
            "����", "�������", "������������ ���"
        },
        {
            "���������", "�������", "������� ���"
        }
    };

    public UsingTableColumnModel() throws HeadlessException
    {
        super("Using Table Column Model");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ���� �������
        JTable table = new JTable(data, columnNames);

        // �������� ����������� ������
        columnModel = table.getColumnModel();
        columnModel.addColumnModelListener(new TableColumnModelListener()
        {
            @Override
            public void columnAdded(TableColumnModelEvent e)
            {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void columnRemoved(TableColumnModelEvent e)
            {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void columnMoved(TableColumnModelEvent e)
            {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void columnMarginChanged(ChangeEvent e)
            {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void columnSelectionChanged(ListSelectionEvent e)
            {
                // throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        // ������ ������� ��������
        Enumeration<TableColumn> e = columnModel.getColumns();
        while (e.hasMoreElements())
        {
            TableColumn column = e.nextElement();
            column.setMinWidth(30);
            column.setMaxWidth(90);
        }

        // ������� ���� ������
        JPanel buttons = new JPanel();
        JButton move = new JButton("�������� �������");
        move.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // ������ ������� ������ ��� �������
                columnModel.moveColumn(0, 1);
            }
        });
        buttons.add(move);
        JButton add = new JButton("��������");
        add.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // ��������� �������
                TableColumn newColumn = new TableColumn(1, 100);
                newColumn.setHeaderValue("<html><b>New!</b>");
                columnModel.addColumn(newColumn);
            }
        });
        buttons.add(add);

        // ������� ���� �� �����
        getContentPane().add(new JScrollPane(table));
        getContentPane().add(buttons, BorderLayout.SOUTH);
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new UsingTableColumnModel();
    }
}
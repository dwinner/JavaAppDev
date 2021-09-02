/*
 * �������� ��� ����� �������, ������������ ����.
 */

import java.awt.Component;
import java.awt.HeadlessException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/**
 * �������� ����� ������� � ���� �������� ���.
 * @author oracle_pr1
 */
class DateCellEditor extends AbstractCellEditor implements TableCellEditor
{
    // �������� - �������
    private JSpinner editor;

    // �����������
    DateCellEditor()
    {
        // ����������� �������
        SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        editor = new JSpinner(model);
    }

    // ���������� ���������, ����������� ��� ��������������
    @Override
    public Component getTableCellEditorComponent(
        JTable table,
        Object value,
        boolean isSelected,
        int row,
        int column)
    {
        // ������ �������� � ���������� �������
        editor.setValue(value);
        return editor;
    }

    // ���������� ������� �������� � ���������
    @Override
    public Object getCellEditorValue()
    {
        return editor.getValue();
    }
}

/**
 * ���������� ������������������� ��������� ��� ����� �������.
 * @author oracle_pr1
 */
public class DateCellEditorTest extends JFrame
{
    // ��������� �������� �������
    private String[] columns =
    {
        "Operation", "Date"
    };
    // ������ �������
    private Object[][] data =
    {
        {
            "�������", new Date()
        },
        {
            "�������", new Date()
        }
    };

    public DateCellEditorTest() throws HeadlessException
    {
        super("Custom table cell editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� ������� �� ������ ����������� ������
        DefaultTableModel model = new DefaultTableModel(data, columns)
        {
            // ���������� ������� ��� �������
            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return data[0][columnIndex].getClass();
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(20);
        
        // ��������� �������� ��� �����
        table.setDefaultEditor(Date.class, new DateCellEditor());

        // ������� ���� �� �����
        getContentPane().add(new JScrollPane(table));
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new DateCellEditorTest();
    }
}
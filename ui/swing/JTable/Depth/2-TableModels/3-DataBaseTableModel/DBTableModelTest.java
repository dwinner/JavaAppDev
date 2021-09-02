/*
 * ������ ������ �������, ���������� � ��������� � ����� ������.
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 * �����, ���������� ��������� ��������� ������ �� �� �� ������ ������� Swing
 * <p/>
 * @author dwinner@inbox.ru
 */
class DatabaseTableModel extends AbstractTableModel
{
    // ����� �� ����� ������� �������� ��������
    private ArrayList<String> columnNames = new ArrayList<>(0x8);
    // ������ ����� ��������
    private ArrayList<Class<?>> columnTypes = new ArrayList<>(0x8);
    // ��������� ��� ��������� ������ �� ����
    private List<List<String>> data =
        Collections.synchronizedList(new ArrayList<List<String>>(0x40));
    private boolean editable;

    // ����������� ��������� ������ ����������� ��������������
    DatabaseTableModel(boolean editable)
    {
        this.editable = editable;
    }

    public boolean isEditable()
    {
        return editable;
    }

    public void setEditable(boolean editable)
    {
        this.editable = editable;
    }

    // ���������� �����
    @Override
    public int getRowCount()
    {
        return data.size();
    }

    // ���������� ��������
    @Override
    public int getColumnCount()
    {
        return columnNames.size();
    }

    // ������ � ������
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        return data.get(rowIndex).get(columnIndex);
    }

    // ��� ������ �������
    @Override
    public Class<?> getColumnClass(int column)
    {
        return columnTypes.get(column);
    }

    // �������� �������
    @Override
    public String getColumnName(int column)
    {
        return columnNames.get(column);
    }

    // ����������� ��������������
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return isEditable();
    }

    // ������ �������� ������
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        data.get(rowIndex).set(columnIndex, aValue.toString());
    }

    // ��������� ������ �� ������� ResultSet
    public void setDataSource(ResultSet rs)
        throws SQLException, ClassNotFoundException
    {
        // ������� ������� ������
        data.clear();
        columnNames.clear();
        columnTypes.clear();

        // �������� ��������������� ���������� � ��������
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        for (int i = 0; i < columnCount; i++)
        {
            // �������� �������
            columnNames.add(rsmd.getColumnName(i + 1));
            // ��� �������
            Class<?> type = Class.forName(rsmd.getColumnClassName(i + 1));
            columnTypes.add(type);
        }

        // �������� �� ���������� � ��������� ������
        fireTableStructureChanged();

        // ����� ����� ������� ������ ����� ������
        ArrayList<String> row = new ArrayList<>(0x8);
        for (int i = 0; i < columnCount; i++)
        {
            if (columnTypes.get(i) == String.class)
            {
                row.add(rs.getString(i + 1));
            }
            else
            {
                row.add(rs.getObject(i + 1).toString());
            }
        }

        data.add(row);
        // �������� � ����������� ������
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}

/**
 * �������, ���������� � ����� ������ ����������� ������.
 */
public class DBTableModelTest
{
    // ��������� �����������
    private static String
        dsn ="jdbc:derby://localhost:1527/sample",
        uid = "app",
        pwd = "app";

    public static void main(String[] args) throws SQLException
    {
        // ������������� JDBC
        Connection conn = null;
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try
            {
                // ������-���������� � ��
                conn = DriverManager.getConnection(dsn, uid, pwd);
                Statement st = conn.createStatement();
                try (ResultSet rs = st.executeQuery("select * from readers"))
                {
                    DatabaseTableModel dbtm = new DatabaseTableModel(false);
                    // ������� � ����
                    JTable table = new JTable(dbtm);
                    JFrame frame = new JFrame("Title");
                    frame.setSize(400, 300);
                    frame.getContentPane().add(new JScrollPane(table));
                    frame.setVisible(true);
                    // ������� ��������� ������� �� �����
                    dbtm.setDataSource(rs);
                }
            }
            catch (SQLException ex)
            {
                throw new RuntimeException(ex);
            }
        }
        catch (ClassNotFoundException ex)
        {
            throw new RuntimeException(ex);
        }
        finally
        {
            if (conn != null)
            {
                conn.close();
            }
        }
    }
}
/*
 * ������ ������, ���������� � ����� ������.
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

class DatabaseListModel extends AbstractListModel
{
    // ����� ����� ������� ������
    private List<String> data = new ArrayList<>();

    // �������� �� ���� ������
    public void setDataSource(ResultSet rs, String column)
    {
        // �������� ������
        if (!data.isEmpty())
        {
            data.clear();
        }
        try
        {
            while (rs.next())
            {
                synchronized (data)
                {
                    data.add(rs.getString(column));
                }
                // ��������� ���� (���� ��� ����)
                fireIntervalAdded(this, 0, data.size());
            }
            rs.close();
            fireContentsChanged(this, 0, data.size());
        }
        catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    // ������ ������ ��� ������ ������ ������
    @Override
    public int getSize()
    {
        synchronized (data)
        {
            return data.size();
        }
    }

    @Override
    public Object getElementAt(int index)
    {
        synchronized (data)
        {
            return data.get(index);
        }
    }
}

public class DBListModelTest
{
    // ��������� ���� ������
    private static String dsn = "jdbc:odbc:Library",
        uid = "sa",
        pwd = "",
        query = "select * from readers";

    public static void main(String[] args) throws SQLException
    {
        // ������������� JDBC
        Connection conn = null;
        ResultSet rs = null;
        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            // ������ ���������� � ����� ������
            conn = DriverManager.getConnection(dsn, uid, pwd);
            Statement st;
            st = conn.createStatement();
            rs = st.executeQuery(query);
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            throw new RuntimeException(ex);
        }
        
        // ������� ������ � ������������ ������
        DatabaseListModel dblm = new DatabaseListModel();
        JList<String> list = new JList<>(dblm);
        
        // ��������� ������
        dblm.setDataSource(rs, "surname");
        
        // �������� ������ � ����
        JFrame frame = new JFrame("DBList");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.getContentPane().add(new JScrollPane(list));
        frame.setVisible(true);
    }
}
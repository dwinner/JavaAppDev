/*
 * Модель списка, работающая с базой данных.
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
    // Здесь будем хранить данные
    private List<String> data = new ArrayList<>();

    // Загрузка из базы данных
    public void setDataSource(ResultSet rs, String column)
    {
        // Получаем данные
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
                // Оповещаем виды (если они есть)
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

    // Методы модели для выдачи данных списку
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
    // Параметры базы данных
    private static String dsn = "jdbc:odbc:Library",
        uid = "sa",
        pwd = "",
        query = "select * from readers";

    public static void main(String[] args) throws SQLException
    {
        // Инициализация JDBC
        Connection conn = null;
        ResultSet rs = null;
        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            // Объект соединения с базой данных
            conn = DriverManager.getConnection(dsn, uid, pwd);
            Statement st;
            st = conn.createStatement();
            rs = st.executeQuery(query);
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            throw new RuntimeException(ex);
        }
        
        // Создаем модель и присоединяем список
        DatabaseListModel dblm = new DatabaseListModel();
        JList<String> list = new JList<>(dblm);
        
        // Загружаем данные
        dblm.setDataSource(rs, "surname");
        
        // Помещаем список в окно
        JFrame frame = new JFrame("DBList");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.getContentPane().add(new JScrollPane(list));
        frame.setVisible(true);
    }
}
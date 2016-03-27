
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Эта программа проверяет правильность настройки базы данных Oracle
 * и JDBC-драйвера.
 * <p/>
 * @version 1.01 2004-09-24
 * @author Cay Horstmann
 */
public class TestDB
{
    private TestDB() { assert false; }

    public static void main(String args[])
    {
        try
        {
            runTest();
        }
        catch (SQLException sqlEx)
        {
            for (Throwable t : sqlEx)
            {
                t.printStackTrace();
            }
        }
        catch (IOException ioEx)
        {
            ioEx.printStackTrace();
        }
    }

    /**
     * Тестирование подключения.
     * <p/>
     * @throws SQLException
     * @throws IOException
     */
    public static void runTest() throws SQLException, IOException
    {
        try (Connection conn = getConnection())
        {
            Statement stat = conn.createStatement();            
            String qString = "select sysdate from dual";
            
            try (ResultSet resultSet = stat.executeQuery(qString))
            {
                if (resultSet.next())
                {
                    System.out.println(resultSet.getDate(1).toString());
                }
            }
        }
    }

    /**
     * Установка соединения с использованием свойств, заданных в файле database.properties
     * <p/>
     * @return Соединение с базой данных.
     * <p/>
     * @throws SQLException
     * @throws IOException                                                                                                                                                                         
     */
    public static Connection getConnection() throws SQLException, IOException
    {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream("database.properties"))
        {
            props.load(in);
        }

        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null)
        {
            System.setProperty("jdbc.drivers", drivers);
        }

        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        return DriverManager.getConnection(url, username, password);
    }
}
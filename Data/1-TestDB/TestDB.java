
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Эта программа проверяет правильность настройки базы данных и JDBC-драйвера.
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
     * Тестирование, включающее создание таблицы, добавление значения, отображение содержимого
     * таблицы и удаление таблицы.
     * <p/>
     * @throws SQLException
     * @throws IOException
     */
    public static void runTest() throws SQLException, IOException
    {
        try (Connection conn = getConnection())
        {
            Statement stat = conn.createStatement();

            stat.executeUpdate("CREATE TABLE Greetings (Message CHAR(20))");
            stat.executeUpdate("INSERT INTO Greetings VALUES ('Hello, World!')");
            try (ResultSet result = stat.executeQuery("SELECT * FROM Greetings"))
            {
                if (result.next())
                {
                    System.out.println(result.getString(1));
                }
            }
            stat.executeUpdate("DROP TABLE Greetings");
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
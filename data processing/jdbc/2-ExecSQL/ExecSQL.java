
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * Программа, демонстрирующая выполнение SQL-команд, которые содержатся в текстовом файле.
 * Вызывается следующим образом: java -classpath путь_к_драйверу:. ExecSQL файл
 * <p/>
 * @version 1.30 2004-08-05
 * @author Cay Horstmann
 */
public class ExecSQL
{
    public static void main(String args[])
    {
        try
        {
            Scanner in = (args.length == 0)
                ? new Scanner(System.in)
                : new Scanner(new File(args[0]));
            try (Connection conn = getConnection())
            {
                Statement stat = conn.createStatement();
                while (true)
                {                    
                    if (args.length == 0)
                    {
                        System.out.println("Enter command or EXIT to exit:");
                    }
                    
                    if (!in.hasNextLine())
                    {
                        return;
                    }
                    
                    String line = in.nextLine();
                    if (line.equalsIgnoreCase("EXIT"))
                    {
                        return;
                    }
                    if (line.trim().endsWith(";"))
                    {   // Удаление завершающей точки с запятой
                        line = line.trim();
                        line = line.substring(0, line.length() - 1);
                    }
                    try
                    {
                        boolean hasResultSet = stat.execute(line);
                        if (hasResultSet)
                        {
                            showResultSet(stat);
                        }
                    }
                    catch (SQLException sqlEx) 
                    {
                        for (Throwable e : sqlEx)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        catch (SQLException sqlEx)
        {
            for (Throwable e : sqlEx)
            {
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException fnotEx)
        {
            fnotEx.printStackTrace();
        }
        catch (IOException ioEx)
        {
            ioEx.printStackTrace();
        }
    }

    /**
     * Установление соединения на основе свойств, заданных в файле database.properties
     * <p/>
     * @return Соединение с базой данных
     * <p/>
     * @throws FileNotFoundException
     * @throws IOException
     * @throws SQLException
     */
    public static Connection getConnection() throws FileNotFoundException, IOException, SQLException
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

    /**
     * Вывод результирующего набора.
     * <p/>
     * @param stat Объект Statement, результаты которого должны быть выведены
     * <p/>
     * @throws SQLException
     */
    public static void showResultSet(Statement stat) throws SQLException
    {
        try (ResultSet result = stat.getResultSet())
        {
            ResultSetMetaData metaData = result.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++)
            {
                if (i > 1)
                {
                    System.out.print(", ");
                }
                System.out.print(metaData.getColumnLabel(i));
            }
            System.out.println();

            while (result.next())
            {
                for (int i = 1; i <= columnCount; i++)
                {
                    if (i > 1)
                    {
                        System.out.print(", ");
                    }
                    System.out.print(result.getString(i));
                }
            }
        }
    }
}
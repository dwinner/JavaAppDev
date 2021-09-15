package ru.aztpa.a.ak.eng.bom.v01.dbconn;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * �����, ������� �� ���� �������������� �� ����������� � ����
 * <p/>
 * @version 1.0.0 17.05.2012
 * @author jdeveloper@aztpa.ru
 */
public final class ConnectionManager
{
    private final static String dbCongifPath = "database.properties";

    /**
     * ��������� ���������� � �������������� �������, �������� � ����� database.properties
     * <p/>
     * @return ���������� � ����� ������.
     * <p/>
     * @throws IOException
     * @throws SQLException
     */
    public static Connection getConnection() throws IOException, SQLException
    {
        Properties props = new Properties();
        InputStream iStream = null;
        try
        {
            iStream = ConnectionManager.class.getResourceAsStream(dbCongifPath);
            props.load(iStream);
            String drivers = props.getProperty("jdbc.drivers");
            if (drivers != null)
                System.setProperty("jdbc.drivers", drivers);
            String url = props.getProperty("jdbc.url");
            return DriverManager.getConnection(url);
        }
        finally
        {
            if (iStream != null)
                iStream.close();
        }
    }

    private ConnectionManager() { assert false; }
}

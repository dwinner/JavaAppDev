package ru.aztpa.a.oaa.mfg.wipplan.v01.dbconn;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Тип для поддержки соединений с СУБД Oracle
 * @author jdeveloper@aztpa.ru
 */
public final class DataBaseConnection
{
    private final static String dbConfFile = "dbconfig.properties";
    private static DataSource oracleDataSource;
    private boolean connectionBusy = false;

    private Connection dbConn;
    public Connection getDbConn() { return dbConn; }

    static
    {
        Properties prop = new Properties();
        InputStream inputStream = DataBaseConnection.class.getResourceAsStream(dbConfFile);
        try
        {
            prop.load(inputStream);
            String jndiContext = prop.getProperty("jndiContext");
            if (jndiContext == null)
            {
                NullPointerException npEx =
                   new NullPointerException("JNDI context string is not defined");
                NamingException namingEx = new NamingException();
                namingEx.initCause(npEx);
                throw namingEx;
            }
            Context context = new InitialContext();
            oracleDataSource = (DataSource) context.lookup(jndiContext);
        }
        catch (IOException ioEx)
        {
            throw new RuntimeException(ioEx);
        }
        catch (NamingException namingEx)
        {
            throw new RuntimeException(namingEx);
        }
        finally
        {
            try
            {
                inputStream.close();
            }
            catch (IOException ioEx)
            {
                throw new RuntimeException(ioEx);
            }
        }
    }

    /**
     * Инициализатор подключения.
     * @throws SQLException
     */
    public DataBaseConnection() throws SQLException { reConnect(); }

    /**
     * Процедура (может быть повторного) соединения.
     * @throws SQLException
     */
    public void reConnect() throws SQLException
    {
        if (oracleDataSource == null)
            throw new SQLException("No data source");
        if (connectionBusy)
            close();
        dbConn = oracleDataSource.getConnection();
        if (dbConn == null)
            throw new SQLException("No connection");
        connectionBusy = true;
    }

    /**
     * Закрытие подключения.
     * @throws SQLException
     */
    public void close() throws SQLException
    {
        if (dbConn != null && !dbConn.isClosed())
            dbConn.close();
        connectionBusy = false;
    }

    /**
     * Проверка поддержки для пакетных операций обновления.
     * @return true если поддержка есть
     * @throws SQLException
     */
    public boolean batchUpdateSupport() throws SQLException
    {
        if (connectionBusy)
        {
            DatabaseMetaData dbmd = dbConn.getMetaData();
            return dbmd.supportsBatchUpdates() ? true : false;
        }
        throw new SQLException("Connection is failed");
    }
}
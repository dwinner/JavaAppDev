package ru.aztpa.a.tp.mfg.process.utilities;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * ����������� ������������� ����������� � ���� ����� �������� JNDI.
 * ��� ����������� �������� � ��� ������ ������ ����� ������ ������ JVM.
 * @author jdeveloper@aztpa.ru 15.03.2012 (updated)
 * @version 3.0
 */
public class CachedConnection
{
    private CachedConnection() { assert false; }

    // ��� �������� �����������
    private static Map<String, Connection> cachedConns =
        Collections.synchronizedMap(new HashMap<String, Connection>(0xA)
    {
        private static final long serialVersionUID = 7466291025126853439L;

        @Override
        protected void finalize() throws Throwable
        {
            super.finalize();

            for (Connection aConn : this.values())
                if (!aConn.isClosed())
                    aConn.close();
        }
    });

    /**
     * ������� ����������� �������� ������������� ������� �����������.
     * @param aContextName ��� ��������� �����������.
     * @param autoCommit   ���� �������������� �������� ���������� ����.
     * @throws NamingException ���� JNDI-�������� �������� �� �������.
     * @throws SQLException    ���� �������� ������ �� JNDI-��������� �� ������.
     */
    private CachedConnection(String aContextName, boolean autoCommit)
       throws NamingException, SQLException
    {
        if (aContextName == null || aContextName.trim().isEmpty())
            throw new AssertionError("Context name cannot be null or empty");

        InitialContext initContext = new InitialContext();
        DataSource dbSource = (DataSource) initContext.lookup(aContextName);

        if (dbSource == null)
            throw new SQLException("No data source");

        Connection dbConn = dbSource.getConnection();
        dbConn.setAutoCommit(autoCommit);
        cachedConns.put(aContextName, dbConn);
    }

    /**
     * ������� ����������� �������� ������������� ������� ����������� �� ����������� ���������� ����
     * @param aContextName ��� ��������� �����������.
     * @throws NamingException ���� JNDI-�������� �������� �� �������.
     * @throws SQLException    ���� �������� ������ �� JNDI-��������� �� ������.
     */
    private CachedConnection(String aContextName)
       throws NamingException, SQLException
    { this(aContextName, false); }

    /**
     * ��������� ������� ����������� ����� JNDI-�������� �� ���� �����������.
     * @param aContextName ��� ��������� �����������.
     * @param autoCommit   ���� �������������� �������� ���������� ����.
     * @return ������ �����������.
     * @throws NamingException ���� JNDI-�������� �������� �� �������.
     * @throws SQLException    ���� �������� ������ �� JNDI-��������� �� ������.
     */
    public static Connection getCacheConnection(String aContextName, boolean autoCommit)
        throws NamingException, SQLException
    {
        if (cachedConns.containsKey(aContextName))
        {
            Connection founded = cachedConns.get(aContextName);
            founded.setAutoCommit(autoCommit);
            return founded;
        }

        CachedConnection cachedConnection = new CachedConnection(aContextName, autoCommit);
        return cachedConns.get(aContextName);
    }

    /**
     * ��������� ������� ����������� ����� JNDI-�������� �� ����������� ���������� ����.
     * @param aContextName ��� ��������� �����������.
     * @return ������ �����������.
     * @throws NamingException ���� JNDI-�������� �������� �� �������.
     * @throws SQLException    ���� �������� ������ �� JNDI-��������� �� ������.
     */
    public static Connection getCacheConnection(String aContextName)
        throws NamingException, SQLException
    {
        return getCacheConnection(aContextName, false);
    }

    /**
     * ����� �������� ����������� ��� ����������� ���������
     * @param aContextName ��� ��������
     * @return true, ���� ���� �������� ��� ��������, false � ��������� ������.
     * @throws SQLException ���� ��������� ���� ��� ��������.
     */
    public static boolean closeByContext(String aContextName) throws SQLException
    {
        if (cachedConns.containsKey(aContextName))
        {
            if (!cachedConns.get(aContextName).isClosed())
            {
                cachedConns.get(aContextName).close();
                cachedConns.remove(aContextName);
                return true;
            }
        }
        return false;
    }

    /**
     * ����� �������� ���� �����������.
     * @throws SQLException ���� ��������� ���� ��� ��������.
     */
    public static void closeAll() throws SQLException
    {
        for (Connection aConn : cachedConns.values())
            if (!aConn.isClosed())
                aConn.close();
        cachedConns.clear();
    }
}

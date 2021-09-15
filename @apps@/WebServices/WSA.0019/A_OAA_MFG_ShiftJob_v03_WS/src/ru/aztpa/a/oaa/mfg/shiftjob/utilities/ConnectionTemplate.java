package ru.aztpa.a.oaa.mfg.shiftjob.utilities;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * �������� �������� ����������� � ���� ����� JNDI-��������.
 * @author jdeveloper@aztpa.ru
 * @version 1.0.0
 */
public abstract class ConnectionTemplate
{   // TODO: ������ ����� ����� ������� Singleton'��
    private DataSource dbSource;
    public DataSource getDbSource() { return dbSource; }

    private Connection singleConn;
    private boolean connAlreadyExists = false;

    /**
     * ������������� ������������ ����������� � ����
     * @param aContextName ��� "������������" ��������� �����������
     */
    public ConnectionTemplate(String aContextName)
    {
        if (aContextName == null || aContextName.trim().isEmpty())
            throw new AssertionError("JNDI name must be correct");
        try
        {
            Context ctx = new InitialContext();
            dbSource = (DataSource) ctx.lookup(aContextName);
        }
        catch (NamingException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    /**
     * ����������� ������������ � ��������� ����������� ����� JNDI-��������.
     * @param autoCommit ���� �������������� �������� ���������� ����.
     * @return ������ �����������.
     * @throws SQLException ���� �� ������� �������� �����������.
     */
    public Connection doConnect(boolean autoCommit) throws SQLException
    {	// FIXME: ����� �������!
        if (dbSource == null)
            throw new SQLException("No data source");
        if (singleConn == null && !connAlreadyExists)
        {
            singleConn = dbSource.getConnection();
            singleConn.setAutoCommit(autoCommit);
            connAlreadyExists = true;
        }
        if (singleConn == null && connAlreadyExists)
            throw new SQLException("No connection");
        if (singleConn.isClosed())
            singleConn = dbSource.getConnection();

        return singleConn;
    }

    /**
     * ����������� ������������ � ��������� ����������� ����� JNDI-�������� ��� ��������������
     * �������� ����������.
     * @return ������ �����������.
     * @throws SQLException ���� �� ������� �������� �����������.
     */
    public Connection doConnect() throws SQLException
    {
        return doConnect(false);
    }

    public void setDbSource(DataSource aDBSource)
    {
        if (aDBSource == null)
            throw new AssertionError("Specify the data source");
        dbSource = aDBSource;
    }
}

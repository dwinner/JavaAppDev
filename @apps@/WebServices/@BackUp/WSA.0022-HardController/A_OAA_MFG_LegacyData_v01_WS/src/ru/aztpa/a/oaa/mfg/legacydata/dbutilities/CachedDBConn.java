package ru.aztpa.a.oaa.mfg.legacydata.dbutilities;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Организация кэшированного подключения к СУБД через контекст JNDI.
 * Все подключения вернутся в пул неявно только после сборки мусора JVM.
 * <p/>
 * @author jdeveloper@aztpa.ru 15.03.2012 (updated)
 * @version 3.0
 */
public class CachedDBConn
{
    private CachedDBConn()
    {
        assert false;
    }
    // Кэш объектов подключения
    private static Map<String, Connection> cachedConns =
        Collections.synchronizedMap(new HashMap<String, Connection>(0xA)
    {
        private static final long serialVersionUID = 7466291025126853439L;

        @Override
        protected void finalize() throws Throwable
        {
            super.finalize();

            for (Connection aConn : this.values())
            {
                if (!aConn.isClosed())
                {
                    aConn.close();
                }
            }
        }
    });

    /**
     * Частный конструктор создания кэшированного объекта подключения.
     * <p/>
     * @param aContextName Имя контекста подключения.
     * @param autoCommit   Флаг автоматической фиксации транзакций СУБД.
     * <p/>
     * @throws NamingException Если JNDI-контекст получить не удалось.
     * @throws SQLException    Если источник данных по JNDI-контексту не найден.
     */
    private CachedDBConn(String aContextName, boolean autoCommit) throws NamingException,
                                                                             SQLException
    {
        if (aContextName == null)
        {
            throw new AssertionError("Context name cannot be null");
        }
        if (aContextName.length() == 0)
        {
            throw new AssertionError("Context name cannot be empty");
        }

        InitialContext initContext = new InitialContext();
        DataSource dbSource = (DataSource) initContext.lookup(aContextName);

        if (dbSource == null)
        {
            throw new SQLException("No data source");
        }

        Connection dbConn = dbSource.getConnection();
        dbConn.setAutoCommit(autoCommit);
        cachedConns.put(aContextName, dbConn);
    }

    /**
     * Частный конструктор создания кэшированного объекта подключения не фиксирующий транзакции
     * СУБД.
     * <p/>
     * @param aContextName Имя контекста подключения.
     * <p/>
     * @throws NamingException Если JNDI-контекст получить не удалось.
     * @throws SQLException    Если источник данных по JNDI-контексту не найден.
     */
    private CachedDBConn(String aContextName) throws NamingException, SQLException
    {
        this(aContextName, false);
    }

    /**
     * Получение объекта подключения через JNDI-контекст из кэша подключений.
     * <p/>
     * @param aContextName Имя контекста подключения.
     * @param autoCommit   Флаг автоматической фиксации транзакций СУБД.
     * <p/>
     * @return Объект подключения.
     * <p/>
     * @throws NamingException Если JNDI-контекст получить не удалось.
     * @throws SQLException    Если источник данных по JNDI-контексту не найден.
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

        CachedDBConn cachedConnection = new CachedDBConn(aContextName, autoCommit);
        return cachedConns.get(aContextName);
    }

    /**
     * Получение объекта подключения через JNDI-контекст не фиксирующий транзакции СУБД.
     * <p/>
     * @param aContextName Имя контекста подключения.
     * <p/>
     * @return Объект подключения.
     * <p/>
     * @throws NamingException Если JNDI-контекст получить не удалось.
     * @throws SQLException    Если источник данных по JNDI-контексту не найден.
     */
    public static Connection getCacheConnection(String aContextName)
        throws NamingException, SQLException
    {
        return getCacheConnection(aContextName, false);
    }

    /**
     * Явное закрытие подключения для переданного контекста
     * <p/>
     * @param aContextName Имя котекста
     * <p/>
     * @return true, если факт закрытия был успешным, false в противном случае.
     * @throws SQLException Если произошел сбой при закрытии.
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
     * Янвое закрытие всех подключений.
     * <p/>
     * @throws SQLException Если произошел сбой при закрытии.
     */
    public static void closeAll() throws SQLException
    {
        for (Connection aConn : cachedConns.values())
        {
            if (!aConn.isClosed())
            {
                aConn.close();
            }
        }
        cachedConns.clear();
    }
}
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
 * Организация кэшированного подключения к СУБД через контекст JNDI.
 * Все подключения вернутся в пул неявно только после сборки мусора JVM.
 * @author jdeveloper@aztpa.ru 15.03.2012 (updated)
 * @version 3.0
 */
public class CachedConnection
{
    private CachedConnection() { assert false; }

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
                if (!aConn.isClosed())
                    aConn.close();
        }
    });

    /**
     * Частный конструктор создания кэшированного объекта подключения.
     * @param aContextName Имя контекста подключения.
     * @param autoCommit   Флаг автоматической фиксации транзакций СУБД.
     * @throws NamingException Если JNDI-контекст получить не удалось.
     * @throws SQLException    Если источник данных по JNDI-контексту не найден.
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
     * Частный конструктор создания кэшированного объекта подключения не фиксирующий транзакции СУБД
     * @param aContextName Имя контекста подключения.
     * @throws NamingException Если JNDI-контекст получить не удалось.
     * @throws SQLException    Если источник данных по JNDI-контексту не найден.
     */
    private CachedConnection(String aContextName)
       throws NamingException, SQLException
    { this(aContextName, false); }

    /**
     * Получение объекта подключения через JNDI-контекст из кэша подключений.
     * @param aContextName Имя контекста подключения.
     * @param autoCommit   Флаг автоматической фиксации транзакций СУБД.
     * @return Объект подключения.
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

        CachedConnection cachedConnection = new CachedConnection(aContextName, autoCommit);
        return cachedConns.get(aContextName);
    }

    /**
     * Получение объекта подключения через JNDI-контекст не фиксирующий транзакции СУБД.
     * @param aContextName Имя контекста подключения.
     * @return Объект подключения.
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
     * @param aContextName Имя котекста
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
     * @throws SQLException Если произошел сбой при закрытии.
     */
    public static void closeAll() throws SQLException
    {
        for (Connection aConn : cachedConns.values())
            if (!aConn.isClosed())
                aConn.close();
        cachedConns.clear();
    }
}

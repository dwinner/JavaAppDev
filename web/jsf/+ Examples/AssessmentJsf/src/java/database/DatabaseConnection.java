package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Helper class for database connection(s).
 *
 * @author Denis
 * @version demo 17-12-2012
 */
public final class DatabaseConnection
{
   /**
    * Factory method for a new database connection
    *
    * @return A new database connection
    * @throws SQLException
    */
   public static DatabaseConnection create() throws SQLException
   {
      return new DatabaseConnection();
   }

   /**
    * Factory method for a new database connection
    *
    * @param hasJndi True if application server has naming and dependency injection for the data source
    * @return A new database connection
    * @throws SQLException
    */
   public static DatabaseConnection create(boolean hasJndi) throws SQLException
   {
      return new DatabaseConnection(hasJndi);
   }

   /**
    * Construct a database connection
    *
    * @param hasJndi True if application server has naming and dependency injection for the data source
    * @throws SQLException
    */
   private DatabaseConnection(boolean hasJndi) throws SQLException
   {
      if (hasJndi)
      {
         try
         {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/" + JNDI);
            dbConnection = dataSource.getConnection();
         }
         catch (NamingException namingEx)
         {
            throw new SQLException(namingEx);
         }
      }
      else
      {
         dbConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
      }
   }

   /**
    * Construct a database connection.
    *
    * @throws SQLException
    */
   private DatabaseConnection() throws SQLException
   {
      this(false);
   }

   /**
    * Close a database connection if it's not closed yet
    *
    * @throws SQLException
    */
   public void closeConnection() throws SQLException
   {
      if (dbConnection != null && !dbConnection.isClosed())
      {
         dbConnection.close();
      }
   }

   /**
    * Get a database connection.
    *
    * @return Database connection
    */
   public Connection getDbConnection()
   {
      return dbConnection;
   }

   @Override
   protected void finalize() throws Throwable   // If you forgot to close resource
   {
      super.finalize();
      closeConnection();
   }

   private Connection dbConnection;
   private static final String DB_URL;
   private static final String DB_USERNAME;
   private static final String DB_PASSWORD;
   private static final String JNDI;
   private static final InputStream DB_CONF_PATH =
     DatabaseConnection.class.getResourceAsStream("dbconf.properties");

   static
   {
      try
      {
         Properties properties = new Properties();
         properties.load(DB_CONF_PATH);
         String drivers = properties.getProperty("jdbc.drivers");
         if (drivers != null)
         {
            System.setProperty("jdbc.drivers", drivers);
         }
         DB_URL = properties.getProperty("jdbc.url");
         DB_USERNAME = properties.getProperty("jdbc.username");
         DB_PASSWORD = properties.getProperty("jdbc.password");
         JNDI = properties.getProperty("jdbc.context");
      }
      catch (IOException ioEx)
      {
         throw new RuntimeException(ioEx);
      }
   }

}

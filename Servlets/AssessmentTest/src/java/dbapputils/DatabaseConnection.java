package dbapputils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Utility for database connection.
 *
 * @author Denis
 * @version demo 17-12-2012
 */
public final class DatabaseConnection
{
   /**
    * A new database connection
    *
    * @return A new database connection
    * @throws SQLException
    */
   public static DatabaseConnection create() throws SQLException
   {
      return new DatabaseConnection();
   }

   /**
    * Construct a database connection.
    *
    * @throws SQLException
    */
   private DatabaseConnection() throws SQLException
   {
      dbConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
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
      }
      catch (IOException ioEx)
      {
         throw new RuntimeException(ioEx);
      }
   }

}

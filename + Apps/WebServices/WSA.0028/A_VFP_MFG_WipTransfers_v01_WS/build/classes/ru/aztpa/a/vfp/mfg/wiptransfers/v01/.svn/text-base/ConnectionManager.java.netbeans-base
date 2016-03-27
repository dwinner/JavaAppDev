package ru.aztpa.a.vfp.mfg.wiptransfers.v01;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Прямое подключение к СУБД.
 *
 * @version 1.0.0 17.05.2012
 * @author jdeveloper@aztpa.ru
 */
public final class ConnectionManager
{
   private final static String DB_CONFIG_PATH = "database.properties";

   /**
    * Прямое подключение к СУБД
    *
    * @return Объект праямого подключения
    *
    * @throws IOException
    * @throws SQLException
    */
   public static Connection getConnection() throws IOException, SQLException
   {
      Properties props = new Properties();
      InputStream iStream = null;
      try
      {
         iStream = ConnectionManager.class.getResourceAsStream(DB_CONFIG_PATH);
         props.load(iStream);
         String drivers = props.getProperty("jdbc.drivers");
         if (drivers != null)
         {
            System.setProperty("jdbc.drivers", drivers);
         }
         String url = props.getProperty("jdbc.url");
         return DriverManager.getConnection(url);
      }
      finally
      {
         if (iStream != null)
         {
            iStream.close();
         }
      }
   }

   private ConnectionManager()
   {
   }
}

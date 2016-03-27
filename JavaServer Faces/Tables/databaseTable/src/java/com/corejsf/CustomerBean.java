package com.corejsf;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

@Named
@RequestScoped
public class CustomerBean
{
   @Resource(name = "jdbc/oracle11gR2")
   private DataSource dataSource;

   public ResultSet getAll() throws SQLException
   {
      Connection connection = dataSource.getConnection();
      try
      {
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT * FROM Another_Customers");
         CachedRowSet cachedRowSet = new com.sun.rowset.CachedRowSetImpl();
         cachedRowSet.populate(resultSet);
         return cachedRowSet;
      }
      finally
      {
         if (connection != null && !connection.isClosed())
         {
            connection.close();
         }
      }
   }

}

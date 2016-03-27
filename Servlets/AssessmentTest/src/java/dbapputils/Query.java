package dbapputils;

import apputils.TwoTuple;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Some database queries.
 *
 * @author Denis
 * @version demo 18-12-2012
 */
public class Query
{
   /**
    * Construct a query object
    *
    * @param databaseConnection A database connection
    */
   public Query(Connection databaseConnection)
   {
      dbConnection = databaseConnection;
   }

   /**
    * Retrieves the total capacity for each employee between start and end date intervals
    *
    * @param startDate Start date
    * @param endDate End date
    * @return A list of two tuple values employee key <=> total capacity
    * @throws SQLException
    */
   public List<TwoTuple<String, Integer>> retrieveCapacityPerEmployee(String startDate, String endDate)
     throws SQLException
   {
      PreparedStatement preparedStatement = null;
      ResultSet resultSet = null;
      List<TwoTuple<String, Integer>> retrieved = new ArrayList<TwoTuple<String, Integer>>(0xFF + 1);
      try
      {
         preparedStatement = dbConnection.prepareStatement(CAPACITY_PER_EMPLOYEE);
         preparedStatement.setString(1, startDate);
         preparedStatement.setString(2, endDate);
         resultSet = preparedStatement.executeQuery();
         while (resultSet.next())
         {
            retrieved.add(
              new TwoTuple<String, Integer>(resultSet.getString(1),
                                            resultSet.getInt(2)));
         }
      }
      finally
      {
         if (resultSet != null)
         {
            resultSet.close();
         }
         if (preparedStatement != null)
         {
            preparedStatement.close();
         }
      }
      return retrieved;
   }

   private Connection dbConnection;
   private static final String CAPACITY_PER_EMPLOYEE =
     "   SELECT t1.employee_key AS employee_key, COUNT(t1.employee_id) AS capacity "
     + " FROM employee t1, schedule t2 "
     + " WHERE t1.employee_id = t2.employee_id "
     + " AND startdatetime >= ? and enddatetime <= ? GROUP BY t1.employee_key ";
}

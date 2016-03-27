package dbapputils;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple test to fill database data from csv-files.
 *
 * @author Denis
 * @version demo 17-12-2012
 */
public class CsvToSqlInstaller
{
   public static void main(String[] args) throws FileNotFoundException,
                                                 IOException,
                                                 SQLException
   {

      DatabaseConnection dbConnection = DatabaseConnection.create();
      try
      {
         deleteEmployeeRecords(dbConnection.getDbConnection());
         int empInserted = fillEmployeeTableFromCsv(dbConnection.getDbConnection());
         LOG.log(Level.INFO, "Inserted records in employee table: {0}", empInserted);
         deleteScheduleRecords(dbConnection.getDbConnection());
         int schInserted = fillScheduleTableFromCsv(dbConnection.getDbConnection());
         LOG.log(Level.INFO, "Inserted records in schedule table: {0}", schInserted);
      }
      finally
      {
         dbConnection.closeConnection();
      }
   }

   /**
    * Insert all records as batch command from csv to database for schedule table.
    *
    * @param connection A database connection
    * @return A count of inserted records
    * @throws FileNotFoundException If csv file does not exists
    * @throws IOException An error occured while reading csv
    * @throws SQLException If SQL server return an error while inserting
    */
   private static int fillScheduleTableFromCsv(Connection connection) throws FileNotFoundException,
                                                                             IOException,
                                                                             SQLException
   {
      CSVReader csvReader = null;
      int inserted = 0;
      try
      {
         csvReader = new CSVReader(new FileReader(SCHEDULES_CSV_FILE));
         String[] columnNames = csvReader.readNext();
         String scheduleIdColumnName = columnNames[0];
         String employeeIdColumnName = columnNames[1];
         String startDateColumnName = columnNames[2];
         String endDateColumnName = columnNames[3];
         String insertQuery = "INSERT INTO schedule ("
           + scheduleIdColumnName + ", " + employeeIdColumnName + ", "
           + startDateColumnName + ", " + endDateColumnName + ") VALUES (?, ?, ?, ?)";
         PreparedStatement insertStatement = null;
         boolean autoCommit = false;
         try
         {
            autoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            insertStatement = connection.prepareStatement(insertQuery);
            String[] nextLine = null;
            do
            {
               nextLine = csvReader.readNext();
               if (nextLine != null && nextLine.length > 3)
               {
                  int scheduleIdValue = Integer.valueOf(nextLine[0]);
                  int employeeIdValue = Integer.valueOf(nextLine[1]);
                  String startDateValue = nextLine[2].trim();
                  String endDateValue = nextLine[3].trim();
                  insertStatement.clearParameters();
                  insertStatement.setInt(1, scheduleIdValue);
                  insertStatement.setInt(2, employeeIdValue);
                  insertStatement.setString(3, startDateValue);
                  insertStatement.setString(4, endDateValue);
                  insertStatement.addBatch();
               }
            }
            while (nextLine != null);
            int[] _inserted = insertStatement.executeBatch();
            for (int i : _inserted)
            {
               if (i > 0)
               {
                  inserted += i;
               }
            }
         }
         catch (SQLException sqlEx)
         {
            connection.rollback();
            throw sqlEx;
         }
         finally
         {
            connection.setAutoCommit(autoCommit);
            if (insertStatement != null)
            {
               insertStatement.clearParameters();
               insertStatement.clearBatch();
               insertStatement.close();
            }
         }
      }
      finally
      {
         if (csvReader != null)
         {
            csvReader.close();
         }
      }
      return inserted;
   }

   /**
    * Insert all records as batch command from csv to database for employee table.
    *
    * @param connection A database connection
    * @return A count of inserted records
    * @throws FileNotFoundException If csv file does not exists
    * @throws IOException An error occured while reading csv
    * @throws SQLException If SQL server return an error while inserting
    */
   private static int fillEmployeeTableFromCsv(Connection connection) throws FileNotFoundException,
                                                                             IOException,
                                                                             SQLException
   {
      CSVReader csvReader = null;
      int inserted = 0;
      try
      {
         csvReader = new CSVReader(new FileReader(EMPLOYEES_CSV_FILE));
         String[] columnNames = csvReader.readNext();
         String empIdColumnName = columnNames[0];
         String empKeyColumnName = columnNames[1];
         String insertQuery =
           "INSERT INTO employee (" + empIdColumnName + ", " + empKeyColumnName + ") VALUES (?, ?)";
         PreparedStatement insertStatement = null;
         boolean autoCommit = false;
         try
         {
            autoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            insertStatement = connection.prepareStatement(insertQuery);
            String[] nextLine = null;
            do
            {
               nextLine = csvReader.readNext();
               if (nextLine != null && nextLine.length > 1)
               {
                  int empIdValue = Integer.valueOf(nextLine[0]);
                  String empKeyValue = nextLine[1].trim();
                  insertStatement.clearParameters();
                  insertStatement.setInt(1, empIdValue);
                  insertStatement.setString(2, empKeyValue);
                  insertStatement.addBatch();
               }
            }
            while (nextLine != null);
            int[] _inserted = insertStatement.executeBatch();
            for (int i : _inserted)
            {
               if (i > 0)
               {
                  inserted += i;
               }
            }
         }
         catch (SQLException sqlEx)
         {
            connection.rollback();
            throw sqlEx;
         }
         finally
         {
            connection.setAutoCommit(autoCommit);
            if (insertStatement != null)
            {
               insertStatement.clearParameters();
               insertStatement.clearBatch();
               insertStatement.close();
            }
         }
      }
      finally
      {
         if (csvReader != null)
         {
            csvReader.close();
         }
      }
      return inserted;
   }

   /**
    * Delete all employee records without truncating sql command.
    *
    * @param connection A database connection
    * @throws SQLException
    */
   private static void deleteEmployeeRecords(Connection connection) throws SQLException
   {
      String sqlCommand = "DELETE FROM employee";
      Statement statement = null;
      try
      {
         statement = connection.createStatement();
         statement.execute(sqlCommand);
      }
      finally
      {
         if (statement != null)
         {
            statement.close();
         }
      }
   }

   /**
    * Delete all schedule records without truncating sql command.
    *
    * @param connection A database connection
    * @throws SQLException
    */
   private static void deleteScheduleRecords(Connection connection) throws SQLException
   {
      String sqlCommand = "DELETE FROM schedule";
      Statement statement = null;
      try
      {
         statement = connection.createStatement();
         statement.execute(sqlCommand);
      }
      finally
      {
         if (statement != null)
         {
            statement.close();
         }
      }
   }

   private CsvToSqlInstaller()
   {
   }

   private final static String SCHEDULES_CSV_FILE = "sql/schedules.csv";
   private final static String EMPLOYEES_CSV_FILE = "sql/employees.csv";
   private static final Logger LOG = Logger.getLogger(CsvToSqlInstaller.class.getName());

   static
   {
      LOG.setLevel(Level.ALL);
   }

}

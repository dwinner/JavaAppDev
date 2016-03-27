package userbeans;

import apputils.TwoTuple;
import database.DatabaseConnection;
import database.Query;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;

import static userbeans.IndexNavigationRules.*;
import static apputils.CheckDatesUtility.isDateString;
import static database.CsvToSqlInstaller.parseStringToDate;

/**
 * A date bean for servlet container.
 *
 * @author Denis
 * @version 1.0.0
 */
@ManagedBean(name = "dateBean")
@SessionScoped
public class DateBean implements Serializable
{
   /**
    * Defaulf constructor.
    */
   public DateBean()
   {
   }

   /**
    * Construct a date managed bean.
    *
    * @param aStartDate A start date
    * @param anEndDate An end date
    */
   public DateBean(String aStartDate, String anEndDate)
   {
      startDate = aStartDate;
      endDate = anEndDate;
   }

   /**
    * Get a start date.
    *
    * @return Get a start date
    */
   public String getStartDate()
   {
      return startDate;
   }

   /**
    * Set a new start date.
    *
    * @param newValue A new start date value
    */
   public void setStartDate(String newValue)
   {
      startDate = newValue;
   }

   /**
    * Get an end date.
    *
    * @return Get an end date
    */
   public String getEndDate()
   {
      return endDate;
   }

   /**
    * Set a new end date.
    *
    * @param newValue A new end date value
    */
   public void setEndDate(String newValue)
   {
      endDate = newValue;
   }

   /**
    * Return view id depending on the date.
    *
    * @return PREVIEW if dates are valid, ERRORDATE otherwise
    */
   public IndexNavigationRules preview()
   {
      return (startDate.trim().length() > 0 && endDate.trim().length() > 0
        && isDateString(startDate) && isDateString(endDate)
        && parseStringToDate(startDate) <= parseStringToDate(endDate)) ? PREVIEW : ERRORDATE;
   }

   /**
    * Get tuples for current date values
    *
    * @return A list of tuples
    */
   public List<TwoTuple<String, Integer>> getTuples()
   {
      try
      {
         DatabaseConnection dbConnection = null;
         try
         {
            dbConnection = DatabaseConnection.create(true);
            Query query = new Query(dbConnection.getDbConnection());
            tuples.clear();
            tuples = null;   // Try to help GC
            tuples = query.retrieveCapacityPerEmployee(startDate, endDate);
         }
         finally
         {
            if (dbConnection != null)
            {
               dbConnection.closeConnection();
            }
         }
      }
      catch (SQLException sqlEx)
      {
         LOG.log(Level.SEVERE, sqlEx.getLocalizedMessage(), sqlEx);
         // throw new RuntimeException(sqlEx);
      }
      putSessionValues();
      return Collections.unmodifiableList(tuples);
   }

   /**
    * Put date values for current session.
    */
   private void putSessionValues()
   {
      ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
      Map<String, Object> session = external.getSessionMap();
      session.put("start_date", startDate);
      session.put("end_date", endDate);
   }

   private String startDate;
   private String endDate;
   private List<TwoTuple<String, Integer>> tuples = new LinkedList<TwoTuple<String, Integer>>();
   private static final long serialVersionUID = 1L;
   private static final Logger LOG = Logger.getLogger(DateBean.class.getName());
}

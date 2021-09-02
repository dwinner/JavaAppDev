package servlets;

import apputils.TwoTuple;
import au.com.bytecode.opencsv.CSVWriter;
import database.DatabaseConnection;
import database.Query;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet for download result set as CSV.
 *
 * @author Denis
 * @version demo 22-12-2012
 */
public class CsvServlet extends HttpServlet
{
   /**
    * Processes requests for both HTTP
    * <code>GET</code> and
    * <code>POST</code> methods.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException
   {
      HttpSession httpSession = request.getSession();
      String startDate = (String) httpSession.getAttribute("start_date");
      String endDate = (String) httpSession.getAttribute("end_date");
      if (startDate == null || endDate == null)
      {
         return;
      }
      response.setContentType("text/csv;charset=UTF-8");
      try
      {
         DatabaseConnection dbConnection = null;
         CSVWriter csvWriter = null;
         try
         {
            dbConnection = DatabaseConnection.create();
            Query query = new Query(dbConnection.getDbConnection());
            List<TwoTuple<String, Integer>> result = query.retrieveCapacityPerEmployee(startDate, endDate);
            csvWriter = new CSVWriter(response.getWriter(), ';', ' ',
                                      System.getProperty("line.separator"));
            for (TwoTuple<String, Integer> twoTuple : result)
            {
               csvWriter.writeNext(new String[]
                 {
                    twoTuple.getFirst(),
                    Integer.toString(twoTuple.getSecond())
                 });
            }
         }
         finally
         {
            if (csvWriter != null)
            {
               csvWriter.close();
            }
            if (dbConnection != null)
            {
               dbConnection.closeConnection();
            }
         }
      }
      catch (SQLException sqlEx)
      {
         ServletException servletEx = new ServletException(sqlEx);
         throw servletEx;
      }
   }

   /**
    * Handles the HTTP
    * <code>GET</code> method.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException
   {
      processRequest(request, response);
   }

   /**
    * Handles the HTTP
    * <code>POST</code> method.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException
   {
      processRequest(request, response);
   }

   /**
    * Returns a short description of the servlet.
    *
    * @return a String containing servlet description
    */
   @Override
   public String getServletInfo()
   {
      return "CSV download servlet";
   }

}

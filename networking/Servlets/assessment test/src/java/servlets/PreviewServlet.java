package servlets;

import apputils.TwoTuple;
import dbapputils.DatabaseConnection;
import dbapputils.Query;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The servlet for sending html table back to client with capacity per employee.
 *
 * @author Denis
 * @version demo 17-12-2012
 */
public class PreviewServlet extends HttpServlet
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
      HttpSession httpSession = request.getSession(true);   // Create a new session
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      // Append PDF and CSV buttons
      out.write("<center><table><tr><td>"
              + "<button id='pdf_preview' title='Download as PDF' "
              + " onclick=\"document.location = 'http://localhost:8080/AssessmentTest/PdfServlet';\">"
              + " PDF</button></td>");
      out.write("<td>"
              + "<button id='csv_preview' title='Download as CSV' "
              + " onclick=\"document.location = 'http://localhost:8080/AssessmentTest/CsvServlet';\">"
              + " CSV</button></td>");
      out.write("</tr></table></center>");
      StringBuilder strBuilder = new StringBuilder(0x400);
      try
      {
         strBuilder.append("<html><head><title>Employee per Capacity Servlet</title>")
           .append("</head><body></p></p><center>");
         DatabaseConnection dbConnection = null;
         try
         {
            dbConnection = DatabaseConnection.create();
            // Get and check post request parameters
            String startDate = request.getParameter("start_date");
            String endDate = request.getParameter("end_date");
            boolean hasErrors = false;
            if (startDate == null || startDate.trim().length() == 0)
            {
               strBuilder.append("<b>Start date is empty</b>");
               hasErrors = true;
            }
            if (endDate == null || endDate.trim().length() == 0)
            {
               strBuilder.append("<b>End date is empty</b>");
               hasErrors = true;
            }
            // TODO: Further checking for parameters...
            if (hasErrors)
            {
               return;
            }
            Query query = new Query(dbConnection.getDbConnection());
            List<TwoTuple<String, Integer>> result =
              query.retrieveCapacityPerEmployee(startDate, endDate);
            if (result.isEmpty())
            {
               strBuilder.append("<h1>There are no results</h1>");
               return;
            }
            strBuilder.append("<table border='2'>")
              .append("<thead>Capacity Per Employee</thead>")
              .append("<tr><th>Employee Key</th><th>Capacity (h)</th></tr>");
            for (TwoTuple<String, Integer> twoTuple : result)
            {
               strBuilder.append("<tr>").append("<td>").append(twoTuple.first)
                 .append("</td>").append("<td>")
                 .append(twoTuple.second)
                 .append("</td>")
                 .append("</tr>");
            }
            strBuilder.append("</table>");
         }
         finally
         {
            dbConnection.closeConnection();
         }
      }
      catch (SQLException sqlEx)
      {
         strBuilder.append("<ul>");
         for (Throwable throwable : sqlEx)
         {
            strBuilder.append("<li>").append(throwable.getLocalizedMessage()).append("</li>");
         }
         strBuilder.append("</ul>");
      }
      finally
      {
         httpSession.setAttribute("start_date", request.getParameter("start_date"));
         httpSession.setAttribute("end_date", request.getParameter("end_date"));
         strBuilder.append("</center></body></html>");
         out.println(strBuilder);
         out.close();
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
      return "Capacity per Employee servlet";
   }

}

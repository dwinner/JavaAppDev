package servlets;

import apputils.TwoTuple;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dbapputils.DatabaseConnection;
import dbapputils.Query;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet for download result set as PDF.
 *
 * @author Denis
 * @version demo 18-12-2012
 */
public class PdfServlet extends HttpServlet
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
    * @throws DocumentException
    */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException
   {
      HttpSession httpSession = request.getSession(); // Retrieve current session parameters
      String startDate = (String) httpSession.getAttribute("start_date");
      String endDate = (String) httpSession.getAttribute("end_date");
      response.setContentType("application/pdf");
      try
      {
         DatabaseConnection dbConnection = null;
         Document pdfDocument = null;
         try
         {
            dbConnection = DatabaseConnection.create();
            Query query = new Query(dbConnection.getDbConnection()); // Retrieve result set again
            List<TwoTuple<String, Integer>> result =
              query.retrieveCapacityPerEmployee(startDate, endDate);
            pdfDocument = new Document(PageSize.A4);  // Write result in PDF table
            PdfWriter.getInstance(pdfDocument, response.getOutputStream());
            pdfDocument.open();
            PdfPTable table = new PdfPTable(2);
            for (TwoTuple<String, Integer> twoTuple : result)
            {
               table.addCell(twoTuple.first);
               table.addCell(Integer.toString(twoTuple.second));
            }
            pdfDocument.add(new Paragraph("Capacity per Employee."));
            pdfDocument.add(new Paragraph("From: " + startDate));
            pdfDocument.add(new Paragraph("To: " + endDate));
            pdfDocument.add(table);
            pdfDocument.add(new Paragraph("Generated time: " + new Date()));
         }
         finally
         {
            if (pdfDocument != null)
            {
               pdfDocument.close();
            }
            dbConnection.closeConnection();
         }
      }
      catch (SQLException sqlEx)
      {
         ServletException servletEx = new ServletException(sqlEx);
         throw servletEx;
      }
      catch (DocumentException docEx)
      {
         IOException ioEx = new IOException(docEx);
         throw ioEx;
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
      return "Current capacity per employee as PDF document";
   }

}

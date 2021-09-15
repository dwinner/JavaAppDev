import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Безопасный сервлет для расчета ссуды.
 * @author dwinner@inbox.ru
 */
@WebServlet(name = "RegPayS", urlPatterns = {"/RegPayS"})
public class RegPayS extends HttpServlet {
    
    private double principal;   // Начальные вложения.
    private double intRate;     // Ставка процента.
    private double numYears;    // Срок ссуды в годах.
    private NumberFormat nf;
    
    // Количество платежей за год. Можно разрешить пользователю
    // вводить это значение.
    public static final int payPerYear = 12;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String payStr = "";
        
        nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        
        // Получение параметров.
        String amountStr = request.getParameter("amount");
        String periodStr = request.getParameter("period");
        String rateStr = request.getParameter("rate");
        
        try {
            if (amountStr != null && periodStr != null && rateStr != null) {
                principal = Double.parseDouble(amountStr);
                numYears = Double.parseDouble(periodStr);
                intRate = Double.parseDouble(rateStr) / 100;
                
                payStr = nf.format(compute());
            }
            else {
                amountStr = "";
                periodStr = "";
                rateStr = "";
            }
        }
        catch (NumberFormatException exc) {
            // Ничего не делаем.
        }
        
        // Установить тип содержимого
        response.setContentType("text/html;charset=UTF-8");
        
        // Получить выходной поток.
        PrintWriter out = response.getWriter();
        
        try {
            // Отобразить в формате HTML.
            out.print("" +
                "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />" +
                "<title>Welcome to Spring Web MVC project</title></head>" +
                "<body><form name=\"Form1\" action=\"http://localhost:8080/RegPayServlet/RegPayS\">" +
                "<b>Enter amount to finance:</b>" +
                "<input type=\"text\" name=\"amount\" size=\"12\" value=\"\" /><br />" +
                "<b>Enter term in years:</b>" +
                "<input type=\"text\" name=\"period\" size=\"12\" value=\"\" /><br />" +
                "<b>Enter interest rate:</b>" +
                "<input type=\"text\" name=\"rate\" size=\"12\" value=\"\" /><br />" +
                "<b>Monthly Payment:</b>" +
                "<input readonly=\"true\" type=\"text\" name=\"payment\" size=\"12\" value=\"\" />" +
                "<br /><p><input type=\"submit\" value=\"Submit\" /></p></form></body></html>"
            );
        }
        finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    /**
     * Выполнение расчетов
     * @return Результат расчетов.
     */
    private double compute() {
        double numer;
        double denom;
        double b, e;
        
        numer = intRate * principal / payPerYear;
        
        e = -(payPerYear * numYears);
        b = (intRate / payPerYear) + 1.0;
        
        denom = 1.0 - Math.pow(b, e);
        
        return numer / denom;
    }
    
}
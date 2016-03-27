import java.io.*;
import java.util.*;
import javax.servlet.*;

public class PostParametersServlet extends GenericServlet {

    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

        // получить записи печати
        PrintWriter pw = response.getWriter();

        // получить перечисление имен параметров
        Enumeration e = request.getParameterNames();

        // показать имена и значения параметров
        while (e.hasMoreElements()) {
            String pname = (String) e.nextElement();
            pw.print(pname + " = ");
            String pvalue = request.getParameter(pname);
            pw.println(pvalue);
        }
        pw.close();
    }

}
package chap5.includeSample.src;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import javax.servlet.annotation.*;
@WebServlet("/main")
public class MainServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>MainServlet</title></head>");
        out.println("<body>");
        ServletContext context = getServletContext();
        RequestDispatcher header = context.getRequestDispatcher("/src/header.html");
        RequestDispatcher greeting = context.getRequestDispatcher("/greet");
        RequestDispatcher footer = context.getRequestDispatcher("/src/footer.html");
        // 包含header.html
        header.include(request, response);
        // 包含greet
        greeting.include(request, response);
        // 包含footer.html
        footer.include(request, response);
        out.println("</body></html>");
        out.close();
    }
}

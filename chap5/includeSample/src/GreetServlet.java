package chap5.includeSample.src;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import javax.servlet.annotation.*;
@WebServlet(name = "GreetServlet", urlPatterns = {"/greet"})
public class GreetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        PrintWriter out = resp.getWriter();
        out.println("<p>Hi " + req.getParameter("name") + "</p>");
    }
}

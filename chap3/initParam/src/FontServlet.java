package chap3.initParam.src;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
public class FontServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        String word = request.getParameter("word");
        if(word == null){
            response.sendError(response.SC_BAD_REQUEST,"Missing parameter: word");
        } else {
            String size = getInitParameter("size");
            String color = getInitParameter("color");
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head><title>FontServlet</title></head>");
            out.println("<body>");
            out.println("<font size='" + size + "' color='" + color + "'>");
            out.println(word);
            out.println("</font>");
            out.println("</body>");
            out.println("</html>");
            out.close();
            // 检查是否commit
            if(response.isCommitted()){
                System.out.println("Response has been committed!");
            } else {
                System.out.println("Response has not been committed!");
            }
        }
    }
}

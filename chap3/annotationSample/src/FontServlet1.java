package chap3.annotationSample.src;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.*;
@WebServlet(name="FontServlet1", urlPatterns={"/font"},initParams = {
        @WebInitParam(name = "font-family", value = "SansSerif"),
        @WebInitParam(name = "font-size", value = "14")}
)
public class FontServlet1 extends HttpServlet {
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

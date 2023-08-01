package chap3.contextTest.src;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class ContextTesterServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        // 获取ServletContext对象
        ServletContext context = getServletContext();
        // 设置响应头
        response.setContentType("text/html;charset=UTF-8");
        // 输出文档
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>ContextTesterServlet</title></head>");
        out.println("<body>");
        out.println("<p>Email: "+ context.getInitParameter("email") + "</p>");
        out.println("<p>Path : "+context.getRealPath("/")+ "</p>");
        out.println("<p>Major Version: "+context.getMajorVersion()+ "</p>");
        out.println("<p>MIME Type: "+context.getMimeType("/WEB/web.xml")+ "</p>");
        out.println("<p>Server Info: "+context.getServerInfo()+ "</p>");
        out.println("</body>");
        out.println("</html>");
        context.log("ContextTesterServlet doGet() method");
        out.close();
    }
}

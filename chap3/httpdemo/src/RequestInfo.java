package chap3.httpdemo.src;
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
public class RequestInfo extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        response.setContentType("text/html"); // 使用setContentType()方法设置响应的MIME类型
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Request Info</title></head>");
        out.println("<body>");
        out.println("<h3>Request Info</h3>");
        out.println("Method: " + request.getMethod() + "<br>");
        out.println("Request URI: " + request.getRequestURI() + "<br>");
        out.println("Protocol: " + request.getProtocol() + "<br>");
        out.println("PathInfo: " + request.getPathInfo() + "<br>");
        out.println("Remote Address: " + request.getRemoteAddr() + "<br>");
        out.println("Remote Host: " + request.getRemoteHost() + "<br>");
        out.println("Character Encoding: " + request.getCharacterEncoding() + "<br>");
        out.println("Content Length: " + request.getContentLength() + "<br>");
        out.println("Content Type: " + request.getContentType() + "<br>");
        out.println("Server Name: " + request.getServerName() + "<br>");
        out.println("Server Port: " + request.getServerPort() + "<br>");
        out.println("Local Address: " + request.getLocalAddr() + "<br>");
        out.println("Local Name: " + request.getLocalName() + "<br>");
        out.println("</body></html>");
    }
}

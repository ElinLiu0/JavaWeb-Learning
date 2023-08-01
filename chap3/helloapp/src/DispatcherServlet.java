package chap3.helloapp.src;
import javax.servlet.*;
import java.io.*;
// 定义一个名为DispatcherServlet的类，用于处理请求
public class DispatcherServlet extends GenericServlet {
    // 指定请求hello.jsp
    private static final String HELLO_JSP = "/hello.jsp";
    public void service(ServletRequest request,ServletResponse response) throws ServletException,IOException{
        // 读取表单中的用户名
        String userName = request.getParameter("username");
        // 将用户名存储在请求属性中
        String passWord = request.getParameter("password");
        // 将用户名和密码存储为参数
        request.setAttribute("username",userName);
        request.setAttribute("password",passWord);
        request.setAttribute("remote-ip",request.getRemoteAddr());
        // 把请求发送给hello.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher(HELLO_JSP);
        dispatcher.forward(request,response);
    }
}

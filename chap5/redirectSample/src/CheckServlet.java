package chap5.redirectSample.src;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import javax.servlet.annotation.*;
@WebServlet(name = "CheckServlet", urlPatterns = {"/forwarding-check"})
public class CheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        // 获取用户名
        String userName = (String)req.getParameter("userName");
        String message = null;
        if(userName == null){
            message = "Please input your name!";
        } else {
            message = "Hello, " + userName;
        }
        // 设置属性，以便重定向到其他Servlet接口
        req.setAttribute("message", message);
        out.println("Output from  CheckServlet before redirecting to HelloServlet");
        System.out.println("Output from  CheckServlet before redirecting to HelloServlet");

        // 执行重定向
        resp.sendRedirect("/redirectSample/forwarding-output?msg="+message);
        // 重定向后，CheckServlet的输出将不会被显示
        out.println("Output from  CheckServlet after redirecting to HelloServlet");
        System.out.println("Output from  CheckServlet after redirecting to HelloServlet");
    }
}

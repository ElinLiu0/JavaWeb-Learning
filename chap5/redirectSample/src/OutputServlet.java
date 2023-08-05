package chap5.redirectSample.src;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import javax.servlet.annotation.*;
@WebServlet(name = "OutputServlet", urlPatterns = {"/forwarding-output"})
public class OutputServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        // 从CheckServlet中获取属性值
        String message = (String)req.getAttribute("message");
        // 读取请求参数
        message = req.getParameter("msg");
        PrintWriter out = resp.getWriter();
        out.println(message);
        out.close();
    }
}

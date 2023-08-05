package chap5.NonblockingIO.src;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
@WebServlet(name = "NonblockServlet", urlPatterns = {"/nonblock"},asyncSupported = true)
public class NonblockServlet extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("<html><head><title>非阻塞IO</title></head><body>");
        out.println("<h1>非阻塞IO</h1>");
        out.println("进入Servlet的时间：" + new Date() + "<br/>");
        // 创建异步上下文
        AsyncContext ctx = req.startAsync();
        // 设置异步上下文的超时时间
        ctx.setTimeout(30 * 1000);
        // 创建Servlet输入流
        ServletInputStream input = req.getInputStream();
        // 为输入流注册监听
        input.setReadListener(new MyReadListener(input, ctx));
        out.println("结束Servlet的时间：" + new Date() + "<br/>");
        out.println("</body></html>");
        out.flush();
    }
}

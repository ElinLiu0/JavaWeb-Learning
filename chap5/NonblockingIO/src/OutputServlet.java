package chap5.NonblockingIO.src;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
@WebServlet(name = "OutputServlet", urlPatterns = {"/output"},asyncSupported = true)
public class OutputServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        // 获取异步上下文
        AsyncContext ctx = servletRequest.getAsyncContext();
        // 获取请求对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 获取请求对象中的属性
        String msg = (String) request.getAttribute("msg");
        // 获取响应对象
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 设置响应类型
        response.setContentType("text/html;charset=UTF-8");
        // 获取响应输出流
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>非阻塞IO</title></head><body>");
        out.println("<h1>非阻塞IO</h1>");
        out.println("进入Servlet的时间：" + new java.util.Date() + "<br/>");
        out.println("Servlet中的数据：" + msg + "<br/>");
        out.println("结束Servlet的时间：" + new java.util.Date() + "<br/>");
        out.println("</body></html>");
        out.flush();
        // 通知容器异步上下文处理完毕
        ctx.complete();
    }
}

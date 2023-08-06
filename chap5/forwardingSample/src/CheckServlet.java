package chap5.forwardingSample.src;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import javax.servlet.annotation.*;
import com.alibaba.fastjson.*;
@WebServlet("/forwarding-check")
public class CheckServlet extends GenericServlet {
    private static JSONObject userMap = new JSONObject();
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        userMap.clear();
        // 读取用户名
        String userName = servletRequest.getParameter("username");
        servletResponse.setContentType("application/json;charset=UTF-8");
        PrintWriter out = servletResponse.getWriter();
        String message = null;
        if(userName == null){
            userMap.put("code",404);
            userMap.put("message","用户名不能为空");
            userMap.put("status","before forwarding");
            out.println(userMap.toJSONString());
        } else {
            userMap.put("code",200);
            userMap.put("message","用户名可用");
            // 在request中添加属性
            servletRequest.setAttribute("userMap",userMap);
            userMap.put("status","before forwarding");
            out.println(userMap.toJSONString());
            // 把请求发送给OutputServlet
            ServletContext context = getServletContext(); // 获取ServletContext对象
            /**
             * 使用RequestDispatcher中的forward是安全的，
             * 因为如果使用浏览器进行访问时，Servlet容器会直接像客户端发出500错误，从而阻止来自外部的跨域访问。
             * */
            RequestDispatcher dispatcher = context.getRequestDispatcher("/output"); // 获取RequestDispatcher对象
            dispatcher.forward(servletRequest,servletResponse);
        }
        out.close();
    }
}

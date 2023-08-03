package chap5.forwardingSample.src;
import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.*;
import com.alibaba.fastjson.*;
@WebServlet("/output")
public class OutputServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        // 从CheckServlet中读取属性
        JSONObject userMap = (JSONObject) servletRequest.getAttribute("userMap");
        PrintWriter out = servletResponse.getWriter();
        userMap.put("status","after forwarding");
        userMap.put("validUserName",servletRequest.getParameter("username"));
        out.println(userMap.toJSONString());
        out.close();
    }
}

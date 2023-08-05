package chap5.pushingSample.src;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.annotation.*;
import com.alibaba.fastjson.*;
@WebServlet("/push")
public class PushServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // 使用PushBuilder建立PushBuilder对象
        PushBuilder pb = req.newPushBuilder();
        // 检查是否支持Push
        if(pb != null){
            pb.path("images/1.jpg").push();
            out.println("<html>");
            out.println("<body>");
            out.println("<p>以下图片来自于服务器端推送</p>");
            out.println("<img src = " + req.getContextPath() + "/images/1.jpg />");
            out.println("</body>");
            out.println("</html>");
            out.flush();
        } else {
            JSONObject json = new JSONObject();
            json.put("code",resp.SC_NOT_IMPLEMENTED);
            json.put("msg","不支持Push");
            out.println(json.toJSONString());
        }
    }
}

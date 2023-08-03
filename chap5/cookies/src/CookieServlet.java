package chap5.cookies.src;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import com.alibaba.fastjson.*;
@WebServlet(name="CookieServlet",urlPatterns={"/cookie"})
public class CookieServlet extends HttpServlet{
    private int count = 0;
    private JSONArray json = new JSONArray();
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
        json.clear();
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 获取Cookies对象数组
        Cookie [] cookies = request.getCookies();
        // 如果Cookies对象数组不为空
        if(cookies != null){
            for(Cookie cookie : cookies){
                JSONObject temp = new JSONObject();
                temp.put("code",response.SC_OK);
                temp.put("msg","获取Cookies成功");
                temp.put("name",cookie.getName());
                temp.put("value",cookie.getValue());
                temp.put("maxAge",cookie.getMaxAge());
                json.add(temp);
            }
        } else {
            JSONObject temp = new JSONObject();
            temp.put("code",response.SC_OK);
            temp.put("msg","暂无Cookies");
            json.add(temp);
        }
        out.println(json.toJSONString());
        response.addCookie(new Cookie("cookieName" + count, "cookieValue" + count));
        count++;
        out.close();
    }
}

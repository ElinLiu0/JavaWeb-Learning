package chap3.simpleForbiden.src;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.HashMap;
import com.alibaba.fastjson.*;
public class SimpleForbiden extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // 初始化Buffer Reader
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        // 读取请求内容
        String str = br.readLine();
        // 初始化JSON对象
        JSONObject data = JSON.parseObject(str);
        // 获取请求的用户名
        if(data.getString("username") == null){
            res.sendError(res.SC_FORBIDDEN);
        } else {
            HashMap<String,Object> response = new HashMap<String,Object>();
            response.put("code",res.SC_OK);
            response.put("certified",true);
            response.put("message","Hello "+data.getString("username"));
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            PrintWriter out = res.getWriter();
            out.println(JSON.toJSONString(response));
            out.close();
        }
    }
}

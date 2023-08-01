package chap3.contextRange.src;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
public class CounterServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        // 获取ServletContext对象
        ServletContext context = getServletContext();
        // 从ServletContext中读取counter属性
        Counter counter = (Counter)context.getAttribute("counter");
        // 如果counter属性不存在，则创建该属性
        if( counter == null){
            counter = new Counter(1);
            context.setAttribute("counter",counter);
        }
        response.setContentType("text/html;charset=UTF-8");
        try{
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head><title>CounterServlet</title></head>");
            out.println("<body>");
            out.println("<h1>欢迎光临本站，你是第"+counter.getCount()+"位访问者！</h1>");
            out.println("</body>");
            out.println("</html>");
            counter.add(1);
            out.close();
            if(response.isCommitted()){
                System.out.println("Response has been committed!");
            } else {
                System.out.println("Response has not been committed!");
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}

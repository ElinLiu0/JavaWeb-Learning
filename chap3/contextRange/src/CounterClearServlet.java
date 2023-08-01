package chap3.contextRange.src;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
public class CounterClearServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        // 从ServletContext的引用
        ServletContext context = getServletContext();
        context.removeAttribute("counter"); // 从ServletContext中删除counter属性
        response.setContentType("text/html;charset=UTF-8");
        try{
            PrintWriter out = response.getWriter();
            out.println("The counter has been removed from the ServletContext!");
            out.close();
            if(response.isCommitted()){
                System.out.println("Response has been committed!");
            } else {
                System.out.println("Response has not been committed!");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

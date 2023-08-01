package chap3.lifeTime.src;
import javax.servlet.*;
import java.io.*;
public class LifeServlet extends GenericServlet{
    private int initVar = 0;
    private int serviceVar = 0;
    private int destroyVar = 0;
    private String name;
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        name = config.getInitParameter("name");
        initVar++;
        System.out.println(name+">"+this.getClass().getName()+",Servlet has been initialized "+initVar+" times");

    }
    public void destroy(){
        destroyVar++;
        System.out.println(name+">"+this.getClass().getName()+",Servlet has been destroyed "+destroyVar+" times");
    }
    public void service(ServletRequest request,ServletResponse response) throws ServletException, IOException{
        serviceVar++;
        System.out.println(name+">"+this.getClass().getName()+" has been response client "+serviceVar+" times");
        String content1 = getServletName() + "has been initialize " + initVar + " times";
        String content2 = "Response to client " + serviceVar + " times";
        String content3 = "has been destroyed " + destroyVar + " times";
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>LifeServlet</title></head>");
        out.println("<body>");
        out.println("<p>" + content1 + "</p>");
        out.println("<p>" + content2 + "</p>");
        out.println("<p>" + content3 + "</p>");
        out.println("</body>");
        out.println("</html>");
        out.close();
        if(response.isCommitted()){
            ;
        } else {
            System.out.println("Response has not been committed!");
        }
    }
}

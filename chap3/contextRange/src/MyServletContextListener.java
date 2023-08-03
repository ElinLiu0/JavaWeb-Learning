package chap3.contextRange.src;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
public class MyServletContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce){
        // 获取ServletContext对象
        ServletContext context = sce.getServletContext();
        try{
            // 从文件中读取计数器的值
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getResourceAsStream("../counter.txt")));
            int count = Integer.parseInt(reader.readLine());
            System.out.println("count = "+count);
            // 关闭文件流
            reader.close();
            // 创建Counter对象
            Counter counter = new Counter(count);
            // 将Counter对象作为属性添加到ServletContext中
            context.setAttribute("counter",counter);

        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void contextDestroyed(ServletContextEvent sce){
        // 获取ServletContext对象
        ServletContext context = sce.getServletContext();
        // 从ServletContext中读取counter属性
        Counter counter = (Counter)context.getAttribute("counter");
        // 如果counter属性存在，则将其值写入文件
        if(counter != null){
            String path = context.getRealPath("D:\\My Code\\Tomact Learning\\chap3\\contextRange\\counter.txt");
            try{
                PrintWriter writer = new PrintWriter(path);
                writer.println(counter.getCount());
                System.out.println(counter.getCount());
                System.out.println("Counter has been saved!");
                writer.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

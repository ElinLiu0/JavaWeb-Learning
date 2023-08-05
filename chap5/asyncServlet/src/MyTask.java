package chap5.asyncServlet.src;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class MyTask implements Runnable {
    private AsyncContext asyncContext;
    public MyTask(AsyncContext asyncContext) {
        this.asyncContext = asyncContext;
    }

    public void run() {
        try{
            // 睡眠5秒，模拟很耗时的一段业务操作
            Thread.sleep(5000);
            asyncContext.getResponse().getWriter().write("业务处理完毕！");
            asyncContext.complete();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

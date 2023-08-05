package chap5.asyncServlet.src;

import javax.servlet.GenericServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
@WebServlet(asyncSupported = true, urlPatterns = {"/asyncExcutor"},name = "AsyncExcutor")
public class AsyncExcutor extends GenericServlet {
    /**
     * 创建一个线程池
     * 参数为：
     * corePoolSize：核心线程数
     * maximumPoolSize：最大线程数
     * keepAliveTime：线程空闲时间，单位为毫秒，超过这个时间，空闲线程将被回收
     * unit：时间单位
     * workQueue：任务队列，用于存储提交的等待执行的任务
     */
    private static ThreadPoolExecutor executor =
            new ThreadPoolExecutor(
                    100,200,50000L,TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<Runnable>(100));

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        // 设置响应类型
        servletResponse.setContentType("text/html;charset=UTF-8");
        // 获取异步上下文
        AsyncContext asyncContext = servletRequest.startAsync(); // 使用startAsync()告知Tomcat服务器当前Servlet正在执行一个异步请求
        // 设定异步的timeout时间为60秒
        asyncContext.setTimeout(60 * 1000);
        // 为异步上下文添加监听器
        asyncContext.addListener(new AsyncListener() {
            public void onComplete(AsyncEvent asyncEvent) throws IOException {
                System.out.println("异步任务执行完毕！");
            }

            public void onTimeout(AsyncEvent asyncEvent) throws IOException {
                System.out.println("异步任务执行超时！");
            }

            public void onError(AsyncEvent asyncEvent) throws IOException {
                System.out.println("异步任务执行错误！");
            }

            public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
                System.out.println("异步任务开始执行！");
            }
        });

        // 提交异步任务
        executor.execute(new MyTask(asyncContext));
    }

    @Override
    public void destroy() {
        executor.shutdown();
    }
}

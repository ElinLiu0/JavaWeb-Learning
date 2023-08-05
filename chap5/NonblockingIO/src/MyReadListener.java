package chap5.NonblockingIO.src;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class MyReadListener implements ReadListener {
    private ServletInputStream input;
    private AsyncContext ctx;
    private StringBuilder sb = new StringBuilder();
    public MyReadListener(ServletInputStream input, AsyncContext ctx) {
        this.input = input;
        this.ctx = ctx;
    }

    public void onDataAvailable() throws IOException {
        System.out.println("Data Available");
        // 暂停五秒
        try {
            Thread.sleep(5000);
            int len = -1;
            byte[] buff = new byte[1024];
            // 读取从浏览器向Servlet提交的数据
            while(input.isReady() && (len = input.read(buff)) != -1) {
                String data = new String(buff, 0, len);
                sb.append(data);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onAllDataRead() throws IOException {
        System.out.println("Data Reading complete");
        // 将数据添加到异步上下文
        ctx.getRequest().setAttribute("msg", sb.toString());
        ctx.dispatch("/output");
    }

    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }
}

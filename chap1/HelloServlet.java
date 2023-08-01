package chap1;
import java.io.*;
public class HelloServlet implements Servlet {
    public void init() throws Exception{
        System.out.println("HelloServlet is initialized.");
    }
    public void service(byte[] requestBuffer, OutputStream out) throws Exception{
        String request = new String(requestBuffer); // 创建HTTP请求字符串
        // 获取请求的第一行
        String firstLineOfRequest = request.substring(0, request.indexOf("\r\n"));
        // 解析HTTP请求的第一行
        String parts[] = firstLineOfRequest.split(" ");
        // 获取请求方法
        String method = parts[0];
        // 获取请求资源的路径
        String uri = parts[1];

        // 获取请求参数
        String userName = null;
        // 如果请求方式为GET，则请求参数紧跟HTTP请求的第一行的URI后面
        if(method.equalsIgnoreCase("get") && uri.indexOf("username=") != -1){
            /*
            * 我们假定请求的URI为：/servlet/HelloServlet?username=Tom&password=1234
            * 那么，uri.indexOf("?")返回的值为15，表示'?'的位置
            * 而+1则表示'?'后面的第一个字符的位置，即'u'的位置
            * */
            String parmeters = uri.substring(uri.indexOf("?") + 1, uri.length());
            parts = parmeters.split("&");
            parts = parts[0].split("=");
            userName = parts[1];
        }

        // 创建HTTP响应结果
        out.write("HTTP/1.1 200 OK\r\n".getBytes());
        out.write("Content-Type:text/html;charset=utf-8\r\n".getBytes());
        out.write("\r\n".getBytes());
        String content = "<html><head><title>HTTP响应示例</title></head>" +
            "<body><h1>欢迎您：" + userName + "</h1></body></html>";
        out.write(content.getBytes());
    }
}

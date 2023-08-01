package chap1;
import java.io.*;
import java.net.*;
import java.util.*;
public class HTTPClient {
    public static void main(String[] args) {
        // 确定HTTP请求的内容
        String uri = "./index.html";
        String host = "localhost";
        int port = 8080;
        try{
            doGet(host,port,uri); // 发送HTTP请求
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void doGet(String host,int port,String uri) throws Exception{
        Socket socket = null;
        try{
            socket = new Socket(host,port); // 创建一个socket对象
            /*
            * 创建一个用于从客户端向服务器发送数据的请求头，用StringBuffer对象来存储
            * */
            StringBuffer stringBuffer = new StringBuffer("GET " + uri + " HTTP/1.1\r\n");
            stringBuffer.append("Accept: */*\r\n");
            stringBuffer.append("Accept-Language: zh-cn\r\n");
            stringBuffer.append("Accept-Encoding: gzip, deflate\r\n");
            stringBuffer.append("User-Agent: HTTPClient\r\n");
            stringBuffer.append("Host: localhost:8080\r\n");
            stringBuffer.append("Connection: Keep-Alive\r\n\r\n");
            // 发送HTTP请求
            /*
            *  在Socket中的定义如下：
            * 向外发出的使用OutputStream，向内读取的使用InputStream
            * */
            OutputStream socketOut = socket.getOutputStream();
            socketOut.write(stringBuffer.toString().getBytes()); // 将请求头发送到服务器
            Thread.sleep(2000); // 等待服务器响应
            // 接收HTTP响应
            InputStream socketIn = socket.getInputStream();
            int size = socketIn.available(); // 获取HTTP响应数据的大小
            byte[] buffer = new byte[size]; // 创建缓冲区
            socketIn.read(buffer); // 读取HTTP响应数据
            String response = new String(buffer); // 转换为字符串
            System.out.println("Server response: ");
            System.out.println(response); // 打印HTTP响应数据
        } catch (Exception e){
            System.out.println("Unable to connect to %s:%d\n" + host + port);
            System.out.println("This caused by: " + e.getMessage());
        } finally {
            try{
                socket.close(); // 关闭socket对象
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

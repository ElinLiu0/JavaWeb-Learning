package chap1;// 使用JavaIO套接字实现简易HTTP服务器
import java.io.*;
import java.net.*;
public class HTTPServer {
    public static void main(String[] args) {
        final int port = 8080;
        ServerSocket serverSocket; // 创建一个socket对象
        try{
            serverSocket = new ServerSocket(port); // 创建一个ServerSocket对象
            System.out.println("Server Listening on port : "+ port);
            while(true){ // 让服务器一直处于监听状态
                try{
                    // 等待客户端的TCP连接请求
                    final Socket socket = serverSocket.accept();
                    System.out.println("Received request from : " + socket.getInetAddress() + ":" + socket.getPort());
                    // 响应请求
                    service(socket);
                } catch (Exception e){
                    e.printStackTrace();
                    break;
                }
            }
        } catch (Exception e){
            System.out.println("Server failed to start!");
            System.out.printf("Reason: %s\n", e.getMessage());
        }
    }
    public static void service(Socket socket) throws Exception{
        // 读取HTTP请求信息
        InputStream socketIn = socket.getInputStream();
        Thread.sleep(500); //让当前进程暂停500ms，以等待HTTP请求信息发送完毕
        int size = socketIn.available(); // 获取HTTP请求信息数据的大小
        byte[] requestBuffer = new byte[size]; // 创建缓冲区
        socketIn.read(requestBuffer); // 读取HTTP请求信息数据
        String request = new String(requestBuffer); // 转换为字符串
        System.out.println("Client request: ");
        System.out.println(request); // 打印HTTP请求数据
        // 解析HTTP请求信息
        int endIndex = request.indexOf("\r\n"); // 获取第一行数据的结束位置
        if(endIndex == -1){
            endIndex = request.length();
        }
        String firstLineOfRequest = request.substring(0, endIndex); // 获取第一行数据
        String[] parts = firstLineOfRequest.split(" "); // 以空格分隔
        String uri = ""; // 请求资源的路径
        if(parts.length >= 2) {
            uri = parts[1];
        }
        // 添加对Servlet的支持
//        if(uri.indexOf("servlet") != -1){
//            // 获取Servlet名称
//            String servletName = null;
//            if(uri.indexOf("?") != -1){
//                servletName = uri.substring(uri.indexOf("servlet/") + 8, uri.indexOf("?"));
//            } else{
//                servletName = uri.substring(uri.indexOf("servlet/") + 8, uri.length());
//            }
//            // 尝试从Servlet缓存中获取Servlet对象
//            Servlet servlet = ServletCache.get(servletName);
//            /*如果Servlet缓存中不存在该对象，则创建*/
//            if(servlet == null){
//                // 创建Servlet对象
//                servlet = (Servlet)Class.forName("chap1." + servletName).newInstance();
//                // 初始化Servlet对象
//                servlet.init();
//                // 将Servlet对象添加到缓存中
//                ServletCache.put(servletName, servlet);
//                // 调用Servlet对象的service()方法
//                servlet.service(socketIn, socket.getOutputStream());
//                // 线程休眠1秒，等待客户端接收HTTP响应结果
//                Thread.sleep(1000);
//                socket.close(); // 关闭TCP连接
//                return;
//            }
//        }
        // 决定HTTP响应正文的类型，此处假设都是html
        String contentType;
        if(uri.indexOf("html") != -1 || uri.indexOf("htm") != -1){
            contentType = "text/html";
        } else if(uri.indexOf("jpg") != -1 || uri.indexOf("jpeg") != -1){
            contentType = "image/jpeg";
        } else if(uri.indexOf("gif") != -1){
            contentType = "image/gif";
        } else{
            contentType = "application/octet-stream";
        }

        // 创建HTTP响应结果
        // 响应的第一行
        String responseFirstLine = "HTTP/1.1 200 OK\r\n";
        // 响应头
        String responseHeader = "Content-Type:" + contentType + "\r\n\r\n";
        /*
        * 使用HTTPServer.class.getResourceAsStream()方法获取资源文件的输入流
        * 该方法接受两个参数，第一个参数是资源文件的路径，第二个参数请求文件名称
        * */
        InputStream in = HTTPServer.class.getResourceAsStream("./" + uri);
        // 发送HTTP响应结果
        OutputStream socketOut = socket.getOutputStream();
        // 发送响应的第一行
        socketOut.write(responseFirstLine.getBytes());
        // 发送响应头
        socketOut.write(responseHeader.getBytes());
        // 发送响应正文
        int len = 0;
        byte[] buffer = new byte[128];
        while((len = in.read(buffer)) != -1){ // 读取响应正文数据
            socketOut.write(buffer, 0, len);
        }
        Thread.sleep(1000); // 休眠1秒，等待客户端接收HTTP响应结果
        socket.close(); // 关闭TCP连接
    }
}

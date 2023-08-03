package chap5.downloadSample.src;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.*;
@WebServlet(name="DownloadServlet", urlPatterns={"/download"})
public class DownloadServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
        OutputStream out;
        InputStream in;
        // 获得filename请求参数
        String filename = request.getParameter("filename");
        // 检查filename是否为null或空串
        if(filename == null || filename.trim().equals("")){
            response.sendError(response.SC_BAD_REQUEST,"缺少filename参数");
        }
        // 获得filename在服务器上的绝对路径
        // 所有基于文件传输的任务都应以“WEB-INF”目录为根目录进行展开
        String filepath = getServletContext().getRealPath("/WEB-INF/content/" + filename);
        // 读取文件
        in = getServletContext().getResourceAsStream("/WEB-INF/content/" + filename);
        // 设置响应头
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition","attachment;filename=" + filename);
        // 创建发送至客户端的输出流
        out = response.getOutputStream();
        // 创建缓冲区
        byte[] buffer = new byte[1024];
        int len = 0;
        // 将文件内容写至缓冲区
        while((len = in.read(buffer)) > 0){
            // 将缓冲区内容输出至客户端
            out.write(buffer,0,len);
        }
        // 关闭输入流
        in.close();
        // 关闭输出流
        out.close();
    }
}

package chap5.uploadSample.src;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.*;
import com.alibaba.fastjson.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import java.util.*;
@WebServlet(
        name="UploadServlet"
        ,urlPatterns={"/upload"}
        ,initParams = {
                @WebInitParam(name="filePath",value="/WEB-INF/store")
                ,@WebInitParam(name="tempPath",value="/WEB-INF/temp")
        }
)
public class UploadServlet extends HttpServlet {
    private String filePath; // 上传文件存放目录
    private String tempPath; // 临时文件目录
    private JSONObject responseJson = new JSONObject();
    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        filePath = config.getInitParameter("filePath");
        tempPath = config.getInitParameter("tempPath");
        filePath = getServletContext().getRealPath(filePath);
        tempPath = getServletContext().getRealPath(tempPath);
        responseJson.clear();
    }
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
        responseJson.clear();
        PrintWriter out = response.getWriter();
        response.setContentType("application/json;charset=UTF-8");
        try{
            // 初始化一个基于硬盘的DiskFileItemFactory对象
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 设置向硬盘写数据时所用的缓冲区的大小，此处为1M
            factory.setSizeThreshold(1024*1024);
            // 设置临时目录
            factory.setRepository(new File(tempPath));

            // 初始化一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 不设置单个文件最大值，以免文件过大导致内存溢出
            // 创建一个FileItem的集合并从request中获取所有上传文件的FileItem对象
            List<FileItem> items = upload.parseRequest(request);

            // 遍历所有FileItem
            for(FileItem item:items){
                if(item.isFormField()){ // 如果是普通字段表单
                    processFormField(item,out);
                } else {
                    processUploadField(item,out);
                }
            }
            out.close();
        } catch (Exception e){
            responseJson.put("code",response.SC_INTERNAL_SERVER_ERROR);
            responseJson.put("message","服务器内部错误");
            responseJson.put("reason",e.getMessage());
            response.getWriter().println(responseJson.toJSONString());
        }
    }
    private void processFormField(FileItem item,PrintWriter out){
        String name = item.getFieldName(); // 从表单中获取字段名
        String value = item.getString(); // 从表单中获取字段值
        responseJson.put("code",HttpServletResponse.SC_OK);
        responseJson.put("type","formField");
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("name",name);
        map.put("value",value);
        responseJson.put("data",map);
        out.println(responseJson.toJSONString());
    }
    private void processUploadField(FileItem item,PrintWriter out) throws Exception{
        String filename = item.getName();
        int index = filename.lastIndexOf("\\"); // 处理文件名中的路径
        filename = filename.substring(index+1,filename.length()); // 获取文件名
        long fileSize = item.getSize(); // 获取文件大小
        if((filename.equals("") && fileSize==0)){ // 如果文件名为空且文件大小为0
            responseJson.put("code",HttpServletResponse.SC_BAD_REQUEST);
            responseJson.put("message","Invalid filename from post requests,got empty filename and file size is 0");
            out.println(responseJson.toJSONString());
        } else {
            File uploadFile = new File(filePath+"/"+filename);
            item.write(uploadFile);
            responseJson.put("code",HttpServletResponse.SC_OK);
            responseJson.put("type","uploadField");
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("name",filename);
            map.put("size",fileSize);
            responseJson.put("data",map);
            responseJson.put("message","File uploaded successfully");
            out.println(responseJson.toJSONString());
        }
    }
}

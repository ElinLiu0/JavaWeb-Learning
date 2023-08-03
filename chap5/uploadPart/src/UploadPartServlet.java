package chap5.uploadPart.src;
import java.io.File;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import com.alibaba.fastjson.*;
@MultipartConfig // 使用 @MultipartConfig 注解标注 Servlet使其支持文件上传
@WebServlet(name = "UploadPartServlet", urlPatterns = {"/uploadPart"},
    initParams = {
        @WebInitParam(name = "filePath", value = "/WEB-INF/store")
    }
)
public class UploadPartServlet extends HttpServlet{
    private String savePath = getInitParameter("filePath");
    private JSONArray responseJSON = new JSONArray();
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        responseJSON.clear();
        PrintWriter out = response.getWriter();
        // 获取正文表单数据，使用request.getParts()获取所有Part的集合
        Collection<Part> parts = request.getParts();
        // 遍历所有Part
        for(Part part:parts){
            String header = part.getHeader("content-disposition");
            HashMap<String,Object> map = new HashMap<String, Object>();
            map.put("code",response.SC_OK);
            map.put("message","success");
            map.put("header",header);
            map.put("type",part.getContentType());
            map.put("name",part.getName());
            map.put("size",part.getSize());

            // 判断表单中文本域
            if(part.getContentType()==null) {
                // 获取表单字段的值
                String value = request.getParameter(part.getName());
                map.put("value", value);
            } else if(part.getName().indexOf("file") != -1){
                // 如果为表单中的file1或者file2字段，就进行文件上传操作

                // 获取文件名
                String fileName = getFileName(header);
                // 将文件写入指定路径
                part.write(savePath + File.separator + fileName);
                map.put("isUpload",true);
                map.put("fileName",fileName);
            }
            responseJSON.add(map);
        }
        out.println(responseJSON.toJSONString());
        out.close();
    }
    public String getFileName(String header){
        // 获取fileName
        String fileName = header.substring(header.lastIndexOf("=")+2,header.length()-1);
        return fileName;
    }
}

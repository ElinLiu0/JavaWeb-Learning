package chap5.validCode.src;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
@WebServlet(name="ImageServlet",urlPatterns={"/image"})
public class ImageServlet extends HttpServlet {
    private Font font = new Font("Monaco", Font.PLAIN, 24);
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        // 得到随机字符
        String code = getRandomCode();
        System.out.println("code:"+code);
        response.setContentType("image/jpeg");
        ServletOutputStream sos = response.getOutputStream();
        // 设置浏览器不要缓存此图片
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 创建一个位于缓存中的图像，长度为60，宽度为20，高度为20，颜色为白色
        BufferedImage image = new BufferedImage(150, 30, BufferedImage.TYPE_INT_RGB);

        // 获得Graph画笔
        Graphics g = image.getGraphics();
        // 设置画笔颜色为黑色
        g.setColor(Color.BLACK);
        // 用画笔填充一个矩形，矩形左上角坐标为(0,0)，宽度为60，高度为20
        g.fillRect(0, 0, 150, 30);
        // 设置画笔颜色为白色
        g.setColor(Color.WHITE);
        // 设置画笔字体
        g.setFont(font);

        // 在画布上画出随机字符
        char c;
        for(int i=0;i<code.length();i++){
            c = code.charAt(i);
            g.drawString(c+"", 20+i*20, 25);
        }
        // 将图像输出到客户端
        ImageIO.write(image, "JPEG", sos);
        sos.close();
        // 将验证码存入session
        HttpSession session = request.getSession();
        session.setAttribute("code", code);
    }
    public String getRandomCode(){
        String code = "";
        char [] codes = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        Random random = new Random();
        for(int i=0;i<6;i++){
            code += codes[random.nextInt(codes.length)];
        }
        return code;
    }
}

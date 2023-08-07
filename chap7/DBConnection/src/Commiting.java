import java.io.*;
import java.util.*;
import java.sql.*;
public class Commiting {
    public static void main(String[] args) {
        // 添加mariaDB驱动
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 注册驱动
        try{
            DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 设置数据库连接信息
        String url = "jdbc:mariadb://192.168.180.81:3306";
        String user = "root";
        String password = "010823";
        String sql = "INSERT INTO BOOKS.BOOKS VALUES('203','孙卫琴','Tomcat与JavaWeb开发技术详解','45','2004','关于Tomcat与JavaWeb开发技术的最畅销技术书',80000);";
        // 连接数据库
        try{
            Connection con = DriverManager.getConnection(url, user, password);
            // 设置自动提交为false
            con.setAutoCommit(false);
            // 创建Statement对象
            Statement stmt = con.createStatement();
            // 执行SQL语句
            stmt.executeUpdate(sql);
            // 提交事务
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

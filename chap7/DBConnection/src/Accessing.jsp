<!--导入Package-->
<%@ page import="java.io.*" %>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>

<!--设置页面编码-->
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <title>动态数据表</title>
    </head>
    <body>
        <%
            try{
                Connection con;
                Statement stmt;
                String userName = "root";
                String passWord = "010823";
                String url = "jdbc:mariadb://192.168.180.81:3306/BOOKS";
                Class.forName("org.mariadb.jdbc.Driver");
                con = DriverManager.getConnection(url,userName,passWord);
                stmt = con.createStatement();
                String sql = "select * from BOOKS";
                ResultSet rs = stmt.executeQuery(sql);
                out.println("<table border=1 width=400>");
                out.println("<tr><td>书名</td><td>作者</td><td>价格</td></tr>");
                while(rs.next()){
                    out.println("<tr>");
                    out.println("<td>"+rs.getString("TITLE")+"</td>");
                    out.println("<td>"+rs.getString("NAME")+"</td>");
                    out.println("<td>"+rs.getString("PRICE")+"￥"+"</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                rs.close();
                stmt.close();
                con.close();

            } catch (Exception e){
                out.println("连接数据库：BOOKS时发生异常"+e.getMessage());
            }
        %>
    </body>
</html>
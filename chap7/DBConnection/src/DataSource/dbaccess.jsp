<%--
  Created by IntelliJ IDEA.
  User: 98246
  Date: 2023/8/7
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.*,java.sql.*" %>
<%@ page import="javax.sql.*,javax.naming.*" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        try{
            Connection conn=null;
            Statement stmt=null;
            ResultSet rs=null;
            // 从数据源中获得连接
            Context ctx=new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/BOOKS");
            conn = ds.getConnection();
            stmt = conn.createStatement();
            // 增加一条记录
            stmt.executeUpdate("insert into BOOKS values('999','Tom','Tomcat Bible',44.5,2023,'INSERT TEST',20000)");
            rs = stmt.executeQuery("SELECT * FROM BOOKS");
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
            conn.close();
        } catch (Exception e){
            out.println("Error:"+e.toString());
        }
    %>
</body>
</html>

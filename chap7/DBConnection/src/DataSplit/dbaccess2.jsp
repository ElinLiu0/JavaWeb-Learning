<%--
  Created by IntelliJ IDEA.
  User: 98246
  Date: 2023/8/7
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<html>
<head>
    <title>Pages Sample</title>
</head>
<body>
    <%!
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
    %>
    <%
        try{
            // 1. 获取连接
            Context ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/BOOKS");
            conn = ds.getConnection();
            // 2. 创建Statement对象
            stmt = conn.createStatement();
            
        } catch (Exception e){
            e.printStackTrace(new PrintWriter(out));
        }
    %>
    <%@ include file="pages.jsp"%>
    <%
        try{
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception ex){
            ex.printStackTrace(new PrintWriter(out));
        }
    %>
</body>
</html>

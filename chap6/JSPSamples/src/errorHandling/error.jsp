<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ page isErrorPage="true"%><!--使用isErrorPage设置当前页是否仅在jsp发生异常时触发-->
<html>
    <head>
        <title>错误页面</title>
    </head>
    <body>
        <p>输入的参数(num1=<%= request.getParameter("num1")%>,num2=<%= request.getParameter("num2")%>)</p>
        <p>错误信息：<% exception.printStackTrace(new PrintWriter(out));%></p>
    </body>
</html>

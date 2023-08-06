<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
    <head>
        <title>errorHandling</title>
    </head>
    <body>
        <%!
            private Object toInt(String str){
                try{
                    return Integer.parseInt(str);
                } catch (Exception e){
                    return e.getMessage();
                }
            }
        %>
        <%
            String str = request.getParameter("num");
            out.println(toInt(str));
        %>
    </body>
</html>
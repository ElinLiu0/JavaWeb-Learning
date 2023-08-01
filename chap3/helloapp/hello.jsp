<html>
    <!--加载标签库-->
    <%@ taglib uri="/mytaglib" prefix="mm" %>
    <head>
        <title>HelloAPP</title>
    </head>
    <body>
        <b><mm:hello/>: <%= request.getAttribute("username") %>,Welcome back!</b>
        <br/>
        <b>Request from : <%= request.getAttribute("remote-ip")%></b>
    </body>
</html>
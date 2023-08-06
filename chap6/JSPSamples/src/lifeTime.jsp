<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
    <head>
        <title>lifeTime</title>
    </head>
    <body>
        <%!
            private int initVar = 0;
            private int serviceVar = 0;
            private int destroyVar = 0;
        %>
        <%!
            public void jspInit(){
                initVar++;
                System.out.println("jspInit() : JSP初始化了"+initVar+"次");
            }
            public void jspDestroy(){
                destroyVar++;
                System.out.println("jspDestroy() : JSP销毁了"+destroyVar+"次");
            }
        %>
        <%
            serviceVar++;
            System.out.println("_jspService() : JSP响应了"+serviceVar+"次");

            String content1 = "初始化次数："+initVar;
            String content2 = "响应次数："+serviceVar;
            String content3 = "销毁次数："+destroyVar;
        %>
        <h1><%=content1%></h1>
        <h1><%=content2%></h1>
        <h1><%=content3%></h1>
    </body>
</html>
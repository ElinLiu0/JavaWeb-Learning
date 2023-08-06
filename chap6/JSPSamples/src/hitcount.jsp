<html>
    <head>
     <title>Hit Counter</title>
    </head>
    <body>
        <h1>
            You hit the page:
            <%! int hitCount = 1; %>
<%--            <%--%>
<%--                int count = 0;--%>
<%--                hitCount = count++;--%>
<%--            %>--%>
            <%= hitCount++ %>
            times
        </h1>
    </body>
</html>
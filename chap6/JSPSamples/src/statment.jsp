<%
    String gender = request.getParameter("gender");
    if(gender.equals("female")){
%>
    She is a girl.
<%
    }else{
%>
    He is a body.
<%
    }
%>
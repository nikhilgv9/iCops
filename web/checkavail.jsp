<%@page contentType="text/html" pageEncoding="UTF-8" import="register.ActivateKit"%>
<%
    ActivateKit a=new ActivateKit();
    int code=a.checkAvail(request.getParameter("usr"));
    if(code==1)
        {
        %>
        <span style="color: green">Available</span>
        <%
        }
    else if(code==0)
        {
        %>
        <span style="color: red">Not Available</span>
        <%
        }
    else
        {
        %>
        <span style="color: red">should be at least 6 Character long</span>
        <%
        }
%>


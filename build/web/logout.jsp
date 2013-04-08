<%-- 
    Document   : logout
    Created on : Apr 16, 2010, 6:39:40 PM
    Author     : Nikhil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="lg" class="login.Login" scope="session"/>
<jsp:setProperty name="lg" property="*" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        lg.init(request.getSession());
        lg.logout();
        response.sendRedirect("login.jsp");
        %>
    </body>
</html>

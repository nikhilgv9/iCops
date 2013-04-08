<%-- 
    Document   : citizen
    Created on : Jun 8, 2010, 10:11:13 AM
    Author     : appu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="javax.servlet.http.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1><%
        HttpSession s=request.getSession();
s.setAttribute("uid", 125);
%>
        <a href="givepet.jsp">Give petition</a><p>
         <a href="viewpet.jsp">view petition</a><p>
    </body>
</html>

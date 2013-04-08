<%@page language="java" import="java.sql.*,innerhtml.Tree"%>
<%
Tree t=new Tree();
t.leveldetails(request.getParameter("id"));
%>
<h4>Level Name: <%out.print(t.name);%> </h4>
<h4>Level Code: <%out.print(t.code);%> </h4>
<h4>Officers: <%out.print(t.officers);%> </h4>

<%@page language="java" import="java.sql.*"%>
<jsp:useBean id="lg" class="login.Login" scope="session"/>
<jsp:useBean id="us" class="usr.Users" scope="request"/>
<jsp:setProperty name="us" property="*" />
<jsp:setProperty name="lg" property="*" />
<%
lg.init(request.getSession());
int n=lg.checkSession();
if(n!=4 && n!=5 && n!=0)
{
    if(request.getParameter("select")!=null)
    {
        us.suspend(request.getParameter("select"), request.getParameter("en"));
        return;
    }
    us.getUserInfo(lg.getId(), lg.checkSession());
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="SHORTCUT ICON" href="images/icon.ico"/>
<title>icops</title>
<script type="text/javascript" src="chat.js"></script>
<script type="text/javascript">
    function maintainsession()
    {
        var d=new Date;
        var xmlhttp=new XMLHttpRequest();
        var time=d.getTime();
        url="blank.jsp?&time="+time;
        xmlhttp.open("GET",url,true);
        xmlhttp.send();
        setTimeout("maintainsession()", 30000);
    }
    function change(x,y)
    {
        var d=new Date;
        var xmlhttp=new XMLHttpRequest();
        var time=d.getTime();
        url="users.jsp?select="+y+"&en="+document.getElementById("sel_"+x).value+"&time="+time;
        xmlhttp.open("GET",url,true);
        xmlhttp.send();
    }
    function load()
    {
        document.getElementById("navUsers").className='act';
        document.getElementById("usrOfficers").className='act';
        setTimeout("maintainsession()", 30000);
        initchat();
    
    <%
        for(int i=0;i<us.usrnames.size();i++)
        {
            out.print("document.getElementById(\'sel_"+i+"\').selectedIndex="+us.suspended.get(i)+";");
        }
    %>
    }
</script>
<meta http-equiv="Content-Language" content="English" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="style.css" media="screen" />
</head>
    <body onload="load()">
<div class="rightmost">
<i><a href="logout.jsp">logout</a></i>
</div>
<div id="wrap">
<div id="header">
<div class="headings">
<h1><a href="#">iCops</a></h1>
<br />
<h2><% out.print(lg.heading); %></h2>
</div>
<div class="nav">
<ul>
<% for(int i=0;i<lg.nav.length;i++)
{
%>
<li id="nav<%= lg.nav[i][0] %>"><a href="<%= lg.nav[i][1] %>"><%= lg.nav[i][0] %></a></li>
<% } %>
</ul>
</div>
</div>

<div id="left">

<h1><a href="#"><% out.print(lg.welcome); %></a></h1>
<div class="tab">
<ul>
<% for(int i=0;i<lg.usrer.length;i++)
{
%>
<li id="usr<%= lg.usrer[i][0] %>"><a href="<%= lg.usrer[i][1] %>"><%= lg.usrer[i][0] %></a></li>
<% } %>
</ul>
</div>
<br />
<br />
<div id="users">
    
    <%
    for(int i=0;i<us.usrnames.size();i++)
        {
    %>
    <p>
<div style="width:80px;float:left;padding: 10px 0 0 10px;height:80px;"><img style="width:60px; height: 60px;" alt="propic" src="<%out.print(us.imageurl.get(i));%>" /></div>
<div style="width:400px;float:left;padding: 10px 0 0 10px;height:80px;">
    <% out.print(us.usrnames.get(i)); %>
Name:
</div>
<div style="width:50px;float:left;padding: 10px 0 0 10px;height:80px;">
    <select id="<% out.print("sel_"+i);%>" onchange="change('<% out.print(i); %>',<% out.print(us.ofid.get(i)); %>)">
        <option value="0">Enabled</option>
        <option value="1">Disabled</option>
    </select>
</div>  
    </p>
    <%
    }
    %>
</div>

</div>

<div id="right">

<div class="adleft">
<img id="propic" alt="icon" src="<% out.print(lg.imgurl);%>" />
</div>
<div class="proright">
<ul>
    <li><img src="images/p_profile.gif" alt=""/><a href="profile.jsp">Profile...</a></li>
    <li><img src="images/p_scrap.gif" alt=""/><a href="profile-edit.jsp">Edit Profile...</a></li>
    <li><img src="images/p_settings.png" alt=""/><a href="#">Settings...</a></li>
    <li><img src="images/p_chat.png" alt=""/><a href="#">Chats...</a></li>
    <li><img src="images/events_ico.png" alt=""/><a href="#">Schedules...</a></li>
</ul>
</div>

<div style="clear: both;"> </div>

<div class="latestarticles">
<h2>Latest Alerts</h2>
<ul>
<li><a href="alerts.jsp?box=1">Wanted...</a></li>
<li><a href="alerts.jsp?box=2">Missing Person...</a></li>
<li><a href="alerts.jsp?box=3">Road Accidents...</a></li>
<li><a href="alerts.jsp?box=4">Other Alerts...</a></li>
</ul>
</div>

<div class="menuleft">
<h2>Users :</h2>
<ul>
<li><a href="#">State</a></li>
<li><a href="#">Police Officers</a></li> 
<li><a href="#">Polce Stations</a></li> 
<li><a href="#">Citizens</a></li> 
<li><a href="#">Guests</a></li> 
</ul>

<h2>Updates</h2>
<ul>
<li><a href="#">January 2007</a></li> 
<li><a href="#">February 2007</a></li> 
<li><a href="#">March 2007</a></li> 
<li><a href="#">April 2007</a></li>
<li><a href="#">May 2007</a></li> 
<li><a href="#">June 2007</a></li> 
<li><a href="#">July 2007</a></li> 
<li><a href="#">August 2007</a></li> 
<li><a href="#">September 2007</a></li>
<li><a href="#">October 2007</a></li>
<li><a href="#">November 2007</a></li>
<li><a href="#">December 2007</a></li>
</ul>
</div>

<div class="menuright">
<img src="images/sidepic.jpg" alt="x" />
</div>
</div>
<div style="clear: both;"> </div>
<div id="footer">
Designed by <a href="#">Government Engineering College</a>, Thanks to <a href="#">Our Department</a>
</div>
</div>
<div id="onlinediv"></div>
<div id="chat"></div>
</body>
</html>
<%
}
else{
    response.sendRedirect("login.jsp");
}
%>
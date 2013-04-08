<%@page language="java" import="innerhtml.*, database.*,java.util.*" %>
<jsp:useBean id="lg" class="login.Login" scope="session"/>
<jsp:useBean id="us" class="register.ActivateKit" scope="request"/>
<jsp:setProperty name="us" property="*" />
<jsp:setProperty name="lg" property="*" />
<%
lg.init(request.getSession());
if(lg.checkSession()==3)
{
    if(request.getParameter("usrname")!=null)
        us.activate(request);
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
	function checkavail()
        {
		 var s="";
        var d=new Date;
        var xmlhttp=new XMLHttpRequest();
        var time=d.getTime();
        url="checkavail.jsp?usr="+document.getElementById("usrname").value+"&time="+time;
		
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
               document.getElementById("avail").innerHTML=xmlhttp.responseText;
            }
        }
        xmlhttp.open("GET",url,true);
        xmlhttp.send();
	}

    function load()
    {
        document.getElementById("navUsers").className='act';
        document.getElementById("usrActivateKits").className='act';
        setTimeout("maintainsession()", 30000);
        initchat();
    }
</script>
<meta http-equiv="Content-Language" content="English" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="style.css" media="screen" />
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
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
<form action="" method="post">
  <table width="100%" border="10" cellspacing="5">
    <tr>
      <td width="35%"><div align="right">Choose Kit* </div></td>
      <td colspan="2"><label>
        <div align="left">
          <select name="kit">
              <%
              List<table> col=new ArrayList<table>();
              List<String> val=new ArrayList<String>();
              Select sel=new Select();
              col.add(userkits.DELIVEREDTO);col.add(userkits.STATUS);
              val.add(lg.getId());val.add("0");
              out.print(sel.property("USERKITS", userkits.KITCODE, col, val));
              %>
          </select>
          </div>
      </label></td>
    </tr>
    <tr>
      <td><div align="right">User Name* </div></td>
      <td colspan="2"><label>
        <div align="left">
          <input id="usrname" type="text" name="usrname" />
          </div>
      </label></td>
    </tr>
    <tr>
      <td><label>
        <div align="right"></div>
      </label></td>
      <td width="22%"><div align="left">
        <input type="button" value="Check Availability" onclick="checkavail()" />
      </div></td>
      <td width="43%"><div id="avail" align="left"></div></td>
    </tr>

    <tr>
      <td><div align="right">First Name* </div></td>
      <td colspan="2"><label>
        <div align="left">
          <input type="text" name="fname" />
          </div>
      </label></td>
    </tr>
    <tr>
      <td><div align="right">Last Name* </div></td>
      <td colspan="2"><label>
        <div align="left">
          <input type="text" name="lname" />
          </div>
      </label></td>
    </tr>
    <tr>
      <td><div align="right">Address</div></td>
      <td colspan="2" rowspan="2"><div align="left">
        <div align="left">
          <textarea name="address" cols="50" rows="8"></textarea>
        </div>
      </div></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><div align="right">Occupation*</div></td>
      <td colspan="2"><label>
        </label>
        <input type="text" name="occupation" /></td>
    </tr>
    <tr>
      <td><div align="right">email ID* </div></td>
      <td colspan="2"><label>
        <div align="left">
          <input type="text" name="email" />
          </div>
      </label></td>
    </tr>
    <tr>
      <td colspan="3"><div align="center" class="style1">We shall sent your password to your email id </div></td>
      </tr>
    <tr>
      <td><div align="right">Phone Number* </div></td>
      <td colspan="2"><label>
        <div align="left">
          <input type="text" name="phno" />
          </div>
      </label></td>
    </tr>
    <tr>
      <td colspan="3"><label>
        <div align="center">
          <input type="submit" name="Submit" value="Activate" />
          </div>
      </label></td>
      </tr>
  </table>

</form>
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
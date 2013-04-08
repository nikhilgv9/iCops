<%@page language="java" import="java.util.*, database.*"%>
<jsp:useBean id="lg" class="login.Login" scope="session"/>
<jsp:useBean id="mg" class="posts.DeletePo" scope="page"/>
<jsp:useBean id="sel" class="innerhtml.Select" scope="page"/>
<jsp:setProperty name="mg" property="*" />
<jsp:setProperty name="lg" property="*" />
<jsp:setProperty name="sel" property="*" />

<%
String msg=" ";
lg.init(request.getSession());
if(lg.checkSession()==1)
{
    int retcode=mg.checkpost(request);
    if(retcode==1)
    {
        msg="\'Post deleted successfully\'";
    }
    else if(retcode==0)
    {
        msg="\'\'";
     }
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <script type="text/javascript" src="chat.js"></script>
    <script language="javascript" type="text/javascript">
// <!CDATA[

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
    function Select1_onchange()
    {
    var s="";
    var d=new Date;
    document.getElementById("Select2").innerHTML="";
    var xmlhttp=new XMLHttpRequest();
    var time=d.getTime();
    url="fill.jsp?depth="+document.getElementById("Select1").value+"&time="+time;
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {

            s=xmlhttp.responseText;
            var temp=s.split("*", 100);
            var a=temp[1].split("/", 100)
            for(i=0;a[i]!="";i++)
            {
                document.getElementById("Select2").options[i]=new Option(a[i],a[i]);

            }
            document.getElementById("Select2").options[i]=new Option('ALL','ALL');
            Select2_onchange();
        }
    }
    xmlhttp.open("GET",url,true);
    xmlhttp.send();

    }
    function Select2_onchange()
    {
    var s="";
    var d=new Date;
    document.getElementById("Select3").innerHTML="";
    var xmlhttp=new XMLHttpRequest();
    var time=d.getTime();
    url="fill.jsp?lname="+document.getElementById("Select2").value+"&time="+time;
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {

            s=xmlhttp.responseText;
            var temp=s.split("*", 100);
            var a=temp[1].split("/", 100)
            for(i=0;a[i]!="";i++)
            {
                document.getElementById("Select3").options[i]=new Option(a[i],a[i]);

            }
        }
    }
    xmlhttp.open("GET",url,true);
    xmlhttp.send();
    }
    function window_onload()
    {
        document.getElementById("navPosts").className='act';
        Select1_onchange();
        document.getElementById("msg").innerHTML=<%out.print(msg); %>;
        setTimeout("maintainsession()", 30000);
        initchat();
    }



// ]]>
    </script>

<link rel="SHORTCUT ICON" href="images/icon.ico"/>
<title>icops</title>
<meta http-equiv="Content-Language" content="English" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="style.css" media="screen" />
</head>
<body onload="window_onload()">
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
<li><a href="posts-create.jsp">Create</a></li>
<li><a href="posts-edit.jsp">Edit</a></li>
<li class="act"><a href="#">Delete</a></li>
</ul>
</div>
<br />
<br />
<div id="maindiv">
    <form id="form1" method="post" action="">
    <ul>
    <li><div id="msg"></div></li>
    <li>
    <span>Deleting a post will delete the post only, not the level</span>
    </li>
    <li>
    <span>Level Depth</span>
    <select id="Select1" name="ldepth" onchange="return Select1_onchange()">
    <%
    out.println(sel.property("HIERARCHY", hierarchy.LEVEL_DEPTH));
    %>
    </select>
    </li>
    <li>
    <span>Level Name</span>
    <select id="Select2" name="lparent" onchange="return Select2_onchange()">
    <!--Add options here-->
    </select>
    </li>
    <li>
    <span>Designation</span>
    <select id="Select3" name="oname">
    </select>
    </li>
    <li>
    <div class="center">
    <input id="Submit2" type="image"  value="submit" src="images_manage/deletebutton.png" />
    </div>
    </li>
    </ul>
    </form>
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
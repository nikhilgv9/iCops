<%@page language="java" import="java.sql.*, communication.Alerts "%>
<jsp:useBean id="lg" class="login.Login" scope="session"/>
<jsp:setProperty name="lg" property="*" />
<%
lg.init(request.getSession());
if(lg.checkSession()!=0)
{
    String box="0";
    String p="0";
    if(request.getParameter("box")!=null)
        box=(String)request.getParameter("box");
    if(request.getParameter("page")!=null)
        p=(String)request.getParameter("page");
    Alerts a=new Alerts();
    if(request.getParameter("text")!=null)
        a.makeAlert(request);
    if(request.getParameter("delete")!=null)
     {
        a.delete(request.getParameter("delete"));
        return;
     }
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="SHORTCUT ICON" href="images/icon.ico"/>
<title>icops</title>
<meta http-equiv="Content-Language" content="English" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style type="text/css">
    .imagebutton {height: 22px; width: 23px; border: solid 2px #C0C0C0; background-color: #C0C0C0}
    .image {position: relative; left: 1px; top: 1px; height:20px; width:21px; border:none;}
    .toolbar {height: 30px; background-color: #C0C0C0;}
</style>
<link rel="stylesheet" type="text/css" href="style.css" media="screen" />
<script type="text/javascript" src="chat.js"></script>
<script type="text/javascript" src="richedit_files/edit.js"></script>
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
    function load()
    {
        window.setTimeout("Start()", 1000);
        document.getElementById("navHome").className='act';
        document.getElementById("box").value='<% out.print(box);%>';
        setTimeout("maintainsession()", 30000);
        initchat();
    }

    function submitform()
    {
        document.getElementById("text").value=document.getElementById("edit").contentWindow.document.body.innerHTML;
        document.getElementById("Form1").submit();
    }
    function deletealert(x)
    {
        var d=new Date;
        var xmlhttp=new XMLHttpRequest();
        var time=d.getTime();
        url="alerts.jsp?"+"delete="+x+"&time="+time;
        xmlhttp.open("GET",url,false);
        xmlhttp.send();
        window.location="alerts.jsp?box="+document.getElementById("box").value;
    }

</script>

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
<li><a href="home-updates.jsp">Updates</a></li>
<li class="act"><a href="#">Alerts</a></li>
</ul>
</div>
<br />
<br />
<div id="alerts">
    <% out.println(a.prepAlert(lg.getId(), box, p)) ;%>
    <br/>
    
<form id="Form1" action="" method="post">
<ul>
<li>Give new Alert</li>
<li style="text-align: left;width: 595px">Subject:<input id="sub" name="sub"  style="width: 100%" type="text" /><br/></li>
<li>
<table style="visibility: visible; border-collapse: collapse;" id="toolbar1" bgcolor="#c0c0c0">
<tbody><tr>
<td>
<div style="border: 2px solid rgb(192, 192, 192);" class="imagebutton" id="cut"><img style="left: 1px; top: 1px;" class="image" src="richedit_files/cut.gif" alt="Cut" title="Cut"></div>
</td>
<td>
<div style="border: 2px solid rgb(192, 192, 192);" class="imagebutton" id="copy"><img style="left: 1px; top: 1px;" class="image" src="richedit_files/copy.gif" alt="Copy" title="Copy"></div>
</td>
<td>
<div style="border: 2px solid rgb(192, 192, 192);" class="imagebutton" id="paste"><img style="left: 1px; top: 1px;" class="image" src="richedit_files/paste.gif" alt="Paste" title="Paste"></div>
</td>
<td>
</td>
<td>
<div style="border: 2px solid rgb(192, 192, 192);" class="imagebutton" id="undo"><img class="image" src="richedit_files/undo.gif" alt="Undo" title="Undo"></div>
</td>
<td>
<div style="border: 2px solid rgb(192, 192, 192);" class="imagebutton" id="redo"><img class="image" src="richedit_files/redo.gif" alt="Redo" title="Redo"></div>
</td>
<td>
</td>
<td>
<div style="border: 2px solid rgb(192, 192, 192); left: 10px;" class="imagebutton" id="createlink"><img class="image" src="richedit_files/link.gif" alt="Insert Link" title="Insert Link"></div>
</td>
<td>
<div style="border: 2px solid rgb(192, 192, 192); left: 10px;" class="imagebutton" id="createimage"><img class="image" src="richedit_files/image.gif" alt="Insert Image" title="Insert Image"></div>
</td>
<td>
</td>
<td>
<div style="border: 2px solid rgb(192, 192, 192); left: 10px;" class="imagebutton" id="createtable"><img class="image" src="richedit_files/table.gif" alt="Insert Table" title="Insert Table"></div>
</td>
<td>
<div style="border: 2px solid rgb(192, 192, 192);" class="imagebutton" id="bold"><img style="left: 1px; top: 1px;" class="image" src="richedit_files/bold.gif" alt="Bold" title="Bold"></div>
</td>
<td>
<div style="border: 2px solid rgb(192, 192, 192);" class="imagebutton" id="italic"><img style="left: 1px; top: 1px;" class="image" src="richedit_files/italic.gif" alt="Italic" title="Italic"></div>
</td>
<td>
<div style="border: 2px solid rgb(192, 192, 192);" class="imagebutton" id="underline"><img style="left: 1px; top: 1px;" class="image" src="richedit_files/underline.gif" alt="Underline" title="Underline"></div>
</td>
<td>
</td>

<td>
<div style="border: 2px solid rgb(192, 192, 192); left: 10px;" class="imagebutton" id="forecolor"><img style="left: 1px; top: 1px;" class="image" src="richedit_files/forecolor.gif" alt="Text Color" title="Text Color"></div>
</td>
<td>
<div style="border: 2px solid rgb(192, 192, 192); left: 40px;" class="imagebutton" id="hilitecolor"><img class="image" src="richedit_files/backcolor.gif" alt="Background Color" title="Background Color"></div>
</td>
<td>
</td>

<td>
<div style="border: 2px solid rgb(192, 192, 192); left: 10px;" class="imagebutton" id="justifyleft"><img class="image" src="richedit_files/justifyleft.gif" alt="Align Left" title="Align Left"></div>
</td>
<td>
<div style="border: 2px solid rgb(192, 192, 192); left: 40px;" class="imagebutton" id="justifycenter"><img class="image" src="richedit_files/justifycenter.gif" alt="Center" title="Center"></div>
</td>
<td>
<div style="border: 2px solid rgb(192, 192, 192); left: 70px;" class="imagebutton" id="justifyright"><img class="image" src="richedit_files/justifyright.gif" alt="Align Right" title="Align Right"></div>
</td>
</tr>
</tbody></table>
<table style="visibility: visible;" id="toolbar2" bgcolor="#c0c0c0">
<tbody><tr>
<td>
<select id="formatblock" onchange="Select(this.id);">
  <option selected="selected" value="&lt;p&gt;">Normal</option>
  <option value="&lt;p&gt;">Paragraph</option>
  <option value="&lt;h1&gt;">Heading 1 </option>
  <option value="&lt;h2&gt;">Heading 2 </option>
  <option value="&lt;h3&gt;">Heading 3 </option>
  <option value="&lt;h4&gt;">Heading 4 </option>
  <option value="&lt;h5&gt;">Heading 5 </option>
  <option value="&lt;h6&gt;">Heading 6 </option>
  <option value="&lt;address&gt;">Address </option>
  <option value="&lt;pre&gt;">Formatted </option>
</select>
</td>
<td>
<select id="fontname" onchange="Select(this.id);">
  <option selected="selected" value="Font">Font</option>
  <option value="Arial">Arial</option>
  <option value="Courier">Courier</option>
  <option value="Times New Roman">Times New Roman</option>
</select>
</td>
<td>
<select unselectable="on" id="fontsize" onchange="Select(this.id);">
  <option selected="selected" value="Size">Size</option>
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  <option value="5">5</option>
  <option value="6">6</option>
  <option value="7">7</option>
</select>
</td>
<td>
</td>
<td>
<div style="border: 2px solid rgb(192, 192, 192); left: 10px;" class="imagebutton" id="insertorderedlist"><img class="image" src="richedit_files/orderedlist.gif" alt="Ordered List" title="Ordered List"></div>
</td>
<td>
<div style="border: 2px solid rgb(192, 192, 192); left: 40px;" class="imagebutton" id="insertunorderedlist"><img class="image" src="richedit_files/unorderedlist.gif" alt="Unordered List" title="Unordered List"></div>
</td>
<td>
</td>
<td>
<div style="border: 2px solid rgb(192, 192, 192); left: 10px;" class="imagebutton" id="outdent"><img class="image" src="richedit_files/outdent.gif" alt="Outdent" title="Outdent"></div>
</td>
<td>
<div style="border: 2px solid rgb(192, 192, 192); left: 40px;" class="imagebutton" id="indent"><img class="image" src="richedit_files/indent.gif" alt="Indent" title="Indent"></div>
</td>
</tr>
</tbody></table>
<input type="hidden" id="text" name="text"/>
<input type="hidden" id="box" name="box"/>
<iframe id="edit" height="200px" width="100%"></iframe>
<iframe id="colorpalette" src="richedit_files/colors.htm" style="visibility: hidden; position: absolute; left: 406px; top: 199px;" height="170" width="250"></iframe>
<input onclick="viewsource(this.checked)" type="checkbox"/>
View HTML Source
          </li>
                <li>
                    <input type="button" value="Alert" onclick="submitform()()"/>
                </li>
        </ul>
</form>
    <div id="fileupload">
    <p id="f1_upload_process" style="visibility: hidden">
        Loading...<img src="loader.gif" /></p>
    <form id="uploadform" action="uploadfile.jsp" method="post" enctype="multipart/form-data" target="upload_target" onsubmit="startUpload();">
    File:<input name="myfile" type="file"/>
    <input type="submit" name="submitBtn" value="Upload" />
    <input type="button" value="cancel" onclick="stopUpload(1,'')"/>
    </form>
    <iframe id="upload_target" name="upload_target" src="#" style="width: 0; height: 0; border: 0px solid #fff;">
    </iframe>
    </div>
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
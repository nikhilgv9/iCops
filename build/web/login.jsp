<%@page language="java" import="java.sql.*"%>
<jsp:useBean id="lg" class="login.Login" scope="session"/>
<jsp:setProperty name="lg" property="*" />
<%
String usr = request.getParameter("usrname");
String pass = request.getParameter("passwd");
lg.init(request.getSession());
int redir = lg.checkSession();
if (redir != 0) {
response.sendRedirect("home-updates.jsp");
}
int code = lg.loghimin(usr, pass);
if (code == 1) {
response.sendRedirect("login.jsp");
} else {

%>
<html>
<head>
<link rel="SHORTCUT ICON" href="images/icon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link href="login/style.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="login/virtualkb.js"></script>

<script language="javascript" type="text/javascript">
var message = "For security reasons this function is not allowed here.";
document.oncontextmenu = new Function("alert(message);return false;")
</script>

<script language="javascript" type="text/javascript">

function init() {

document.getElementById("chkbox").checked = false;
document.getElementById("username").focus();
constructKeyboard(false);
}
</script>

<title>iCops Login</title>
</head>
<body onLoad="init()">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tbody>
<tr>
<td rowspan="3" align="left" height="72" valign="top">&nbsp;
<img alt="" src="login/logo.png" /></td>
<td id="top_1" valign="middle" width="12">
<img src="login/right_link_curve.gif" alt="" height="24" width="12"/>
</td>
<td class="topNavText" id="top_1" nowrap="nowrap" height="24" valign="middle"><a href="#">About iCops </a>| <a href="#"accesskey="4" title="Press Alt + 4 and Enter">Register as Guest</a> </td>
</tr>
<tr>
<td colspan="2" align="center" height="38">&nbsp; </td>
</tr>
<tr>
<td colspan="3" class="wlcmname" align="center" height="10">&nbsp; </td>
</tr>
</tbody>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%" style="background-image: url('login/top.png'); background-repeat: repeat-x">
<tbody>
<tr height="22">
<td width="30"><a href="" class="topnavlink">&nbsp;Login&nbsp;</a> </td>
</tr>
</tbody>
</table>
<div id="logonBodyContent" style="">
<form name="quickLookForm" method="post" autocomplete="off">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
Your user name and password are highly confidential. Never part with them. iCops will never ask for this information.
<tbody>
<tr>
<td class="maincurvecls" scope="col">
<table id="tblPageContent" align="right" border="0" cellpadding="0" cellspacing="0">
<tbody>
<tr>
<td class="innerPageTopLeftBGWithText" align="right" nowrap="nowrap" height="31" valign="middle" width="81%"></td>
<td align="right" height="31" valign="middle" width="19%">&nbsp; </td>
</tr>
</tbody>
</table>
</td>
</tr>
<tr>
<td colspan="2" scope="row" style="border-left: 0pt none;" class="pageContentBG" align="left" valign="top">

<table class="tblbrdr" align="center" border="0" cellpadding="0" cellspacing="0" width="70%">
<tbody>
    <tr>
    <td style="width: 50%;padding-left: 25px; text-align: left">
        <div id="kbddiv" style="text-align: center;">
    <table class="vkb_content">
    <tbody>
    <tr>
    <td class="kbd_heading">On Screen Keyboard </td>
    </tr>
    <tr>
    <td><span id="kbplaceholder">
    </span></td>
    </tr>
    </tbody>
    </table>
</div>
    </td>
<td style="width: 50%; text-align: center;">
<div id="loginpage" style="text-align: center">
<table align="center" style="background-color: #B1DCFE; text-align: center" width="100%">
<tbody>
<tr>
<td class="vkb_aligntop" width="100%">
<form method="post" action="" style="width: 100%; text-align: center;">
<table class="vkb_tbl" border="0" cellpadding="0" cellspacing="0" width="40%">
<tbody>
<tr>
<td colspan="3">
<p align="center" class="vkb_login_heading">To access your account...<br><span>Login</span></p></td>
</tr>
<tr>
<td nowrap="nowrap" width="34%"><div align="center"><strong>User Name * </strong></div></td>
<td width="66%">
  
    <div align="center">
      <input value="" autocomplete="off" name="usrname" id="username" tabindex="1" size="20" maxlength="20" onFocus="disableautocompletion(this.id);getFocus(this.id);" onBlur="disableautocompletion(this.id);" oncopy="return false" onpaste="return false" onKeyPress="return disableCtrlKeyCombination(event);" onKeyDown="return disableCtrlKeyCombination(event);" type="text">
      </div></td>
</tr>
<tr>
<td><div align="center"><strong>Password * </strong></div></td>
<td nowrap="nowrap">
  
    <div align="center">
      <input name="passwd" id="label2" tabindex="2" title="password" size="20" onFocus="disableautocompletion(this.id);getFocus(this.id);" onBlur="disableautocompletion(this.id);" oncopy="return false" onpaste="return false" onKeyPress="return
disableCtrlKeyCombination(event);" onKeyDown="return
disableCtrlKeyCombination(event);" type="password">
      </div></td>
<!-- CR 2614 ends -->
</tr>
<tr>
<td> <div align="center"></div></td>
<td>
  
    <div align="center">
      <input checked="checked" id="chkbox" onClick="constructKeyboard();" type="checkbox">
      Show OnScreen Keyboard </div></td>
</tr>
<tr>
<td><div align="center"></div></td>
<td class="button_row_left" nowrap="nowrap">
  <div align="center">
  <input class="button_class" value="Login" title="Login" id="Button2" tabindex="3" type="submit">
  &nbsp;
  <input name="Cancel2" class="button_Class" value="Reset" title="Reset" id="Cancel2" tabindex="4" onClick="constructKeyboard()" type="reset">
  </div></td>
</tr>
<tr>
<td colspan="2" class="vkb_link"><div align="center">For better security use the Online Virtual Keyboard to login. </div></td>
</tr>
</tbody>
</table>
</form>
</td>
</tr>
<tr>
<td colspan="2">
<table align="center" border="0" cellpadding="0" cellspacing="0" width="75%">
<tbody>
</tbody>
</table>
</td>
</tr>
<tr align="center">
<td colspan="3" class="loginSubLinks">
<br>
<a href="">Trouble logging in</a> | <a href="">FAQ</a> | <a href=""><b>About Phishing</b></a> | <a href="">Report Phishing</a> </td>
</tr>
<tr align="center">
<td colspan="3" style="height: 51px"></td>
</tr>
</tbody>
</table>
</div>
</td>
</tr>
</tbody>
</table>
<div style="" id="logonRemark">
<br>
<table align="center" border="0" cellpadding="0" cellspacing="0" width="90%">
<tbody>
<tr>
<td class="notecls">
Mandatory fields are marked with an asterisk (*)
</tr>
</tbody>
</table>
</div>
<br>
</td>
</tr>
</tbody>
</table>
</form>
</div>
<br>
<table border="0" cellpadding="0" cellspacing="0" width="100%" style="background-image: url('login/top.png'); background-repeat: repeat-x">
<tbody>
<tr>
<td class="footerText" height="25" valign="middle">�&nbsp;Copyright iCops 2010 </td>
<td class="footerText" height="25" valign="middle">
<div align="right"><a href="#">Privacy Statementtion Terms of Use</a></div>
</td>
</tr>
</table>
</body>
</html>
<% }%>
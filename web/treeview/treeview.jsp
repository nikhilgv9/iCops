<%@page language="java" import="java.sql.*, java.util.*"%>
<jsp:useBean id="tr" class="innerhtml.Tree" scope="session"/>
<jsp:setProperty name="tr" property="*" />

<HTML>
 <HEAD>

  <TITLE>TreeView Demo: Frameless Layout with Highlighting</TITLE>
  <STYLE type="text/css">
   SPAN.TreeviewSpanArea A {
     font-size: 10pt; 
     font-family: verdana,helvetica; 
     text-decoration: none;
     color: teal;}
   SPAN.TreeviewSpanArea A:hover {
     color: #820082;}
   SPAN.TreeviewSpanArea A:active {
     color: #880;}

   BODY {
     background-color: white;}
   TD {
     font-size: 10pt; 
     font-family: verdana,helvetica;}


  </STYLE>

<link rel="stylesheet" type="text/css" href="tree.css" media="screen" />

  <SCRIPT>
  function getQueryString(index) {
    var paramExpressions;
    var param
    var val
    paramExpressions = window.location.search.substr(1).split("&");
    if (index < paramExpressions.length) {
      param = paramExpressions[index]; 
      if (param.length > 0) {
        return eval(unescape(param));
      }
    }
    return ""
  }
  </SCRIPT>

  <SCRIPT src="ua.js"></SCRIPT>

  <SCRIPT src="ftiens4.js"></SCRIPT>

  <SCRIPT language="javascript" type="text/javascript">
  
      USETEXTLINKS = 1;
      STARTALLOPEN = 0;
      USEFRAMES = 0
      USEICONS = 0
      WRAPTEXT = 1
      PRESERVESTATE = 1
      HIGHLIGHT = 1
      <%

      out.print(tr.getJs());

      %>

      foldersTree = gFld("View Hierarchy", "");
      foldersTree.addChildren([aux]);
      foldersTree.treeID = "FramelessHili";


        var IE = document.all?true:false
        if (!IE) document.captureEvents(Event.MOUSEMOVE)
        document.onmousemove = getMouseXY;
        var tempX = 0;
        var tempY = 0;
        var curX=0;
        var curY=0;

        if (document.addEventListener) {
            document.addEventListener("mousedown", dismiss, true);
        } else if (document.attachEvent) {
        document.attachEvent("onmousedown", dismiss, true);
        }


        function getMouseXY(e) {
            if (IE) {
                tempX = event.clientX + document.body.scrollLeft
                tempY = event.clientY + document.body.scrollTop
            } else {
                tempX = e.pageX
                tempY = e.pageY
            }
        }
        
        function dismiss()
        {
            if( tempX>curX+200 || tempX<curX || tempY> curY+200 || tempY<curY)
                document.getElementById("leveldetails").style.visibility='hidden';
        }

        function redirectparent(x)
            {
                window.top.window.location='../'+x;
            }

      function loaddetails(x)
      {
          var d=new Date;
        var xmlhttp=new XMLHttpRequest();
        var time=d.getTime();
        url="getleveldet.jsp?"+"id="+x+"&time="+time;
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
                document.getElementById("leveldetails").innerHTML=xmlhttp.responseText;
            }
        }
        xmlhttp.open("GET",url,true);
        xmlhttp.send();
        document.getElementById("leveldetails").style.visibility='visible';
        document.getElementById("leveldetails").style.left=tempX+100;
        curX=tempX+100;
        document.getElementById("leveldetails").style.top=tempY;
        curY=tempY;
      }

  </SCRIPT>

 </HEAD>


 <!------------------------------------------------------------->
 <BODY bgcolor="white" leftmargin="0" topmargin="0" marginheight="0" marginwidth="0"  onResize="if (navigator.family == 'nn4') window.location.reload()">
 <!-- dont remove following div even if it seems to be purpose less: nikhil -->
<div style="visibility:hidden;">
<TABLE border=0><TR><TD><FONT size=-2><A style="font-size:7pt;text-decoration:none;color:silver" href="http://www.treemenu.net/" target=_blank>Javascript Tree Menu</A></FONT></TD></TR></TABLE>
</div>

<div style="width: 500px; text-align: top; float: left">
<SPAN class=TreeviewSpanArea>
<SCRIPT>initializeDocument()</SCRIPT>
</SPAN>
</div>
<div id="leveldetails"></div>

 </BODY>

</HTML>
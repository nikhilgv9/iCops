package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import database.*;

public final class posts_002dcreate_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      login.Login lg = null;
      synchronized (session) {
        lg = (login.Login) _jspx_page_context.getAttribute("lg", PageContext.SESSION_SCOPE);
        if (lg == null){
          lg = new login.Login();
          _jspx_page_context.setAttribute("lg", lg, PageContext.SESSION_SCOPE);
        }
      }
      out.write('\r');
      out.write('\n');
      posts.ManagePo mg = null;
      synchronized (_jspx_page_context) {
        mg = (posts.ManagePo) _jspx_page_context.getAttribute("mg", PageContext.PAGE_SCOPE);
        if (mg == null){
          mg = new posts.ManagePo();
          _jspx_page_context.setAttribute("mg", mg, PageContext.PAGE_SCOPE);
        }
      }
      out.write('\r');
      out.write('\n');
      innerhtml.Select sel = null;
      synchronized (_jspx_page_context) {
        sel = (innerhtml.Select) _jspx_page_context.getAttribute("sel", PageContext.PAGE_SCOPE);
        if (sel == null){
          sel = new innerhtml.Select();
          _jspx_page_context.setAttribute("sel", sel, PageContext.PAGE_SCOPE);
        }
      }
      out.write('\r');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.introspect(_jspx_page_context.findAttribute("mg"), request);
      out.write('\r');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.introspect(_jspx_page_context.findAttribute("lg"), request);
      out.write('\r');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.introspect(_jspx_page_context.findAttribute("sel"), request);
      out.write("\r\n");
      out.write("\r\n");

String msg=" ";
lg.init(request.getSession());
if (lg.checkSession() == 1) {
                int retcode = mg.checkpost(request);
                if (retcode == 1) {
                    int err = mg.writeto();
                    if (err == 1) {
                        msg = "\'Post created successfully\'";
                    } else {
                        msg = "\'In valid post information\'";
                    }
                } else if (retcode == 2) {
                    msg = "\'Fill all fields\'";
                } else if (retcode == 0) {
                    msg = "\'\'";
                }

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("    <script language=\"javascript\" type=\"text/javascript\">\r\n");
      out.write("// <!CDATA[\r\n");
      out.write("    function Select1_onchange() {\r\n");
      out.write("        var s=\"\";\r\n");
      out.write("        var d=new Date;\r\n");
      out.write("        document.getElementById(\"Select2\").innerHTML=\"\";\r\n");
      out.write("        var xmlhttp=new XMLHttpRequest();\r\n");
      out.write("        var time=d.getTime();\r\n");
      out.write("        url=\"fill.jsp?depth=\"+document.getElementById(\"Select1\").value+\"&time=\"+time;\r\n");
      out.write("        xmlhttp.onreadystatechange=function()\r\n");
      out.write("        {\r\n");
      out.write("            if (xmlhttp.readyState==4 && xmlhttp.status==200)\r\n");
      out.write("            {\r\n");
      out.write("\r\n");
      out.write("                s=xmlhttp.responseText;\r\n");
      out.write("                var temp=s.split(\"*\", 100);\r\n");
      out.write("                var a=temp[1].split(\"/\", 100)\r\n");
      out.write("                for(i=0;a[i]!=\"\";i++)\r\n");
      out.write("                {\r\n");
      out.write("                    document.getElementById(\"Select2\").options[i]=new Option(a[i],a[i]);\r\n");
      out.write("\r\n");
      out.write("                }\r\n");
      out.write("            }\r\n");
      out.write("        }\r\n");
      out.write("        xmlhttp.open(\"GET\",url,true);\r\n");
      out.write("        xmlhttp.send();\r\n");
      out.write("\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("        function window_onload() {\r\n");
      out.write("            document.getElementById(\"navPosts\").className='act';\r\n");
      out.write("            Select1_onchange();\r\n");
      out.write("            document.getElementById(\"msg\").innerHTML=");
out.print(msg); 
      out.write(";\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        function imgclick(i)\r\n");
      out.write("        {\r\n");
      out.write("            var str=\"stars/level\"+i+\".png\";\r\n");
      out.write("            document.getElementById(\"image1\").src=str;\r\n");
      out.write("            document.getElementById(\"imgpath\").value=str;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("// ]]>\r\n");
      out.write("    </script>\r\n");
      out.write("\r\n");
      out.write("<link rel=\"SHORTCUT ICON\" href=\"images/icon.ico\"/>\r\n");
      out.write("<title>icops</title>\r\n");
      out.write("<meta http-equiv=\"Content-Language\" content=\"English\" />\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" media=\"screen\" />\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("<!--\r\n");
      out.write("#Layer1 {\r\n");
      out.write("\tposition:absolute;\r\n");
      out.write("\twidth:200px;\r\n");
      out.write("\theight:115px;\r\n");
      out.write("\tz-index:1;\r\n");
      out.write("\tleft: 526px;\r\n");
      out.write("\ttop: 1280px;\r\n");
      out.write("}\r\n");
      out.write("-->\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("        <body onload=\"window_onload()\">\r\n");
      out.write("<div class=\"rightmost\">\r\n");
      out.write("<i><a href=\"logout.jsp\">logout</a></i>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"wrap\">\r\n");
      out.write("<div id=\"header\">\r\n");
      out.write("<div class=\"headings\">\r\n");
      out.write("<h1><a href=\"#\">iCops</a></h1>\r\n");
      out.write("<br />\r\n");
      out.write("<h2>");
 out.print(lg.heading); 
      out.write("</h2>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"nav\">\r\n");
      out.write("<ul>\r\n");
 for(int i=0;i<lg.nav.length;i++)
{

      out.write("\r\n");
      out.write("<li id=\"nav");
      out.print( lg.nav[i][0] );
      out.write("\"><a href=\"");
      out.print( lg.nav[i][1] );
      out.write('"');
      out.write('>');
      out.print( lg.nav[i][0] );
      out.write("</a></li>\r\n");
 } 
      out.write("\r\n");
      out.write("</ul>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div id=\"left\">\r\n");
      out.write("\r\n");
      out.write("<h1><a href=\"#\">");
 out.print(lg.welcome); 
      out.write("</a></h1>\r\n");
      out.write("<div class=\"tab\">\r\n");
      out.write("<ul>\r\n");
      out.write("<li class=\"act\"><a href=\"#\">Create</a></li>\r\n");
      out.write("<li><a href=\"posts-edit.jsp\">Edit</a></li>\r\n");
      out.write("<li><a href=\"posts-delete.jsp\">Delete</a></li>\r\n");
      out.write("</ul>\r\n");
      out.write("</div>\r\n");
      out.write("<br />\r\n");
      out.write("<br />\r\n");
      out.write("<div id=\"maindiv\">\r\n");
      out.write("    <form id=\"form1\" method=\"post\" action=\"\">\r\n");
      out.write("    <ul>\r\n");
      out.write("    <li><div id=\"msg\"></div></li>\r\n");
      out.write("    <li>\r\n");
      out.write("    <span>Designation</span>\r\n");
      out.write("    <input name=\"oname\" id=\"Text3\" type=\"text\" />\r\n");
      out.write("    </li>\r\n");
      out.write("    <li>\r\n");
      out.write("    <span>Officer ID</span>\r\n");
      out.write("    <input name=\"ocode\" id=\"Text4\" type=\"text\" />\r\n");
      out.write("    </li>\r\n");
      out.write("    <li>\r\n");
      out.write("    <span>Profile Picture</span>\r\n");
      out.write("    </li>\r\n");
      out.write("    <li>\r\n");
      out.write("    <img alt=\"\" src=\"stars/level1.png\" style=\"width: 102px; height: 112px\" id=\"image1\" />\r\n");
      out.write("    <input id=\"imgpath\" name=\"opropic\" type=\"hidden\" value=\"stars/level1.png\" />\r\n");
      out.write("    </li>\r\n");
      out.write("    <li>\r\n");
      out.write("    <table id=\"imageselect\">\r\n");
      out.write("    <tr>\r\n");
      out.write("    <td>\r\n");
      out.write("    <a href=\"javascript:imgclick(1)\"><img alt=\"\" src=\"stars/level1.png\" /></a></td>\r\n");
      out.write("    <td>\r\n");
      out.write("    <a href=\"javascript:imgclick(2)\"><img alt=\"\" src=\"stars/level2.png\" /></a></td>\r\n");
      out.write("    <td>\r\n");
      out.write("    <a href=\"javascript:imgclick(3)\"><img alt=\"\" src=\"stars/level3.png\" /></a></td>\r\n");
      out.write("    <td>\r\n");
      out.write("    <a href=\"javascript:imgclick(4)\"><img alt=\"\" src=\"stars/level4.png\" /></a></td>\r\n");
      out.write("    <td>\r\n");
      out.write("    <a href=\"javascript:imgclick(5)\"><img alt=\"\" src=\"stars/level5.png\" /></a></td>\r\n");
      out.write("    <td>\r\n");
      out.write("    <a href=\"javascript:imgclick(6)\"><img alt=\"\" src=\"stars/level6.png\" /></a></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("    <td>\r\n");
      out.write("    <a href=\"javascript:imgclick(1)\"><img alt=\"\" src=\"stars/level1.png\" /></a></td>\r\n");
      out.write("    <td>\r\n");
      out.write("    <a href=\"javascript:imgclick(1)\"><img alt=\"\" src=\"stars/level1.png\" /></a></td>\r\n");
      out.write("    <td>\r\n");
      out.write("    <a href=\"javascript:imgclick(1)\"><img alt=\"\" src=\"stars/level1.png\" /></a></td>\r\n");
      out.write("    <td>\r\n");
      out.write("    <a href=\"javascript:imgclick(1)\"><img alt=\"\" src=\"stars/level1.png\" /></a></td>\r\n");
      out.write("    <td>\r\n");
      out.write("    <a href=\"javascript:imgclick(1)\"><img alt=\"\" src=\"stars/level1.png\" /></a></td>\r\n");
      out.write("    <td>\r\n");
      out.write("    <a href=\"javascript:imgclick(1)\"><img alt=\"\" src=\"stars/level1.png\" /></a></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    </table>\r\n");
      out.write("    </li>\r\n");
      out.write("    <li>\r\n");
      out.write("    <span>Level Depth</span>\r\n");
      out.write("    <select id=\"Select1\" name=\"ldepth\" onchange=\"return Select1_onchange()\">\r\n");
      out.write("    <!--Add options here-->\r\n");
      out.write("    ");

        out.println(sel.property("HIERARCHY", hierarchy.LEVEL_DEPTH));
    
      out.write("\r\n");
      out.write("    </select>\r\n");
      out.write("    </li>\r\n");
      out.write("    <li>\r\n");
      out.write("    <span>Level Name</span>\r\n");
      out.write("    <select id=\"Select2\" name=\"lparent\" style=\"width: 191px\">\r\n");
      out.write("     <!--Add options here-->\r\n");
      out.write("    </select>\r\n");
      out.write("    </li>\r\n");
      out.write("    <li>\r\n");
      out.write("    <div class=\"center\">\r\n");
      out.write("    <input id=\"Submit2\" type=\"image\"  value=\"submit\" src=\"images_manage/black button.png\" />\r\n");
      out.write("    </div>\r\n");
      out.write("    </li>\r\n");
      out.write("    </ul>\r\n");
      out.write("    </form>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div id=\"right\">\r\n");
      out.write("\r\n");
      out.write("<div class=\"adleft\">\r\n");
      out.write("<img id=\"propic\" alt=\"icon\" src=\"");
 out.print(lg.imgurl);
      out.write("\" />\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"proright\">\r\n");
      out.write("<ul>\r\n");
      out.write("    <li><img src=\"images/p_profile.gif\" alt=\"\"/><a href=\"profile.jsp\">Profile...</a></li>\r\n");
      out.write("    <li><img src=\"images/p_scrap.gif\" alt=\"\"/><a href=\"profile-edit.jsp\">Edit Profile...</a></li>\r\n");
      out.write("    <li><img src=\"images/p_settings.png\" alt=\"\"/><a href=\"#\">Settings...</a></li>\r\n");
      out.write("    <li><img src=\"images/p_chat.png\" alt=\"\"/><a href=\"#\">Chats...</a></li>\r\n");
      out.write("    <li><img src=\"images/events_ico.png\" alt=\"\"/><a href=\"#\">Schedules...</a></li>\r\n");
      out.write("</ul>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div style=\"clear: both;\"> </div>\r\n");
      out.write("\r\n");
      out.write("<div class=\"latestarticles\">\r\n");
      out.write("<h2>Latest Alerts</h2>\r\n");
      out.write("<ul>\r\n");
      out.write("<li><a href=\"alerts.jsp?box=1\">Wanted...</a></li>\r\n");
      out.write("<li><a href=\"alerts.jsp?box=2\">Missing Person...</a></li>\r\n");
      out.write("<li><a href=\"alerts.jsp?box=3\">Road Accidents...</a></li>\r\n");
      out.write("<li><a href=\"alerts.jsp?box=4\">Other Alerts...</a></li>\r\n");
      out.write("</ul>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div class=\"menuleft\">\r\n");
      out.write("<h2>Users :</h2>\r\n");
      out.write("<ul>\r\n");
      out.write("<li><a href=\"#\">State</a></li>\r\n");
      out.write("<li><a href=\"#\">Police Officers</a></li> \r\n");
      out.write("<li><a href=\"#\">Polce Stations</a></li> \r\n");
      out.write("<li><a href=\"#\">Citizens</a></li> \r\n");
      out.write("<li><a href=\"#\">Guests</a></li> \r\n");
      out.write("</ul>\r\n");
      out.write("\r\n");
      out.write("<h2>Updates</h2>\r\n");
      out.write("<ul>\r\n");
      out.write("<li><a href=\"#\">January 2007</a></li> \r\n");
      out.write("<li><a href=\"#\">February 2007</a></li> \r\n");
      out.write("<li><a href=\"#\">March 2007</a></li> \r\n");
      out.write("<li><a href=\"#\">April 2007</a></li>\r\n");
      out.write("<li><a href=\"#\">May 2007</a></li> \r\n");
      out.write("<li><a href=\"#\">June 2007</a></li> \r\n");
      out.write("<li><a href=\"#\">July 2007</a></li> \r\n");
      out.write("<li><a href=\"#\">August 2007</a></li> \r\n");
      out.write("<li><a href=\"#\">September 2007</a></li>\r\n");
      out.write("<li><a href=\"#\">October 2007</a></li>\r\n");
      out.write("<li><a href=\"#\">November 2007</a></li>\r\n");
      out.write("<li><a href=\"#\">December 2007</a></li>\r\n");
      out.write("</ul>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div class=\"menuright\">\r\n");
      out.write("<img src=\"images/sidepic.jpg\" alt=\"x\" />\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("<div style=\"clear: both;\"> </div>\r\n");
      out.write("<div id=\"footer\">\r\n");
      out.write("Designed by <a href=\"#\">Government Engineering College</a>, Thanks to <a href=\"#\">Our Department</a>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"chat\">\r\n");
      out.write("    <div class=\"chatbox\">\r\n");
      out.write("    <div class=\"fortest\">\r\n");
      out.write("      <div class=\"chathead\" onclick=\"this.parentNode.style.visibility = 'hidden';this.parentNode.parentNode.className='chatboxheadonly';this.parentNode.nextSibling.style.visibility = 'visible';\">\r\n");
      out.write("\t  \t\t<div style=\"float:left\">Chat with Nikhil</div>\r\n");
      out.write("          \t<div align=\"right\">X</div>\r\n");
      out.write("      </div>\r\n");
      out.write("        <div class=\"chattext\"></div>\r\n");
      out.write("\t   <textarea cols=\"22\" rows=\"3\" style=\"text-align: left\"></textarea>\r\n");
      out.write("    </div><div class=\"chathead\" style=\"visibility: hidden\" onclick=\"this.style.visibility = 'hidden';this.previousSibling.style.visibility = 'visible';this.parentNode.className='chatbox'\">\r\n");
      out.write("\t  \t\t<div style=\"float:left\">Chat with Nikhil</div>\r\n");
      out.write("          \t<div align=\"right\">X</div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <div class=\"chatbox\">\r\n");
      out.write("    <div class=\"fortest\">\r\n");
      out.write("      <div class=\"chathead\" onclick=\"this.parentNode.style.visibility = 'hidden';this.parentNode.parentNode.className='chatboxheadonly';this.parentNode.nextSibling.style.visibility = 'visible';\">\r\n");
      out.write("\t  \t\t<div style=\"float:left\">Chat with Nikhil</div>\r\n");
      out.write("          \t<div align=\"right\">X</div>\r\n");
      out.write("      </div>\r\n");
      out.write("        <div class=\"chattext\"></div>\r\n");
      out.write("\t   <textarea cols=\"22\" rows=\"3\" style=\"text-align: left\"></textarea>\r\n");
      out.write("    </div><div class=\"chathead\" style=\"visibility: hidden\" onclick=\"this.style.visibility = 'hidden';this.previousSibling.style.visibility = 'visible';this.parentNode.className='chatbox'\">\r\n");
      out.write("\t  \t\t<div style=\"float:left\">Chat with Nikhil</div>\r\n");
      out.write("          \t<div align=\"right\">X</div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    \r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");

}
else{
    response.sendRedirect("login.jsp");
}

    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

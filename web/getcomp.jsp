<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="lg" class="login.Login" scope="request"/>
<jsp:useBean id="comp" class="communication.Petitions" scope="page"/>
<jsp:useBean id="info" class="communication.Informations" scope="page"/>
<jsp:useBean id="cases" class="communication.Cases" scope="page"/>
<jsp:setProperty name="comp" property="*" />
<jsp:setProperty name="info" property="*" />
<jsp:setProperty name="cases" property="*" />
<jsp:setProperty name="lg" property="*" />
<%
lg.init(request.getSession());
if(lg.checkSession()!=0)
{
    if(request.getParameter("box")!=null)
        {
        if(request.getParameter("box").equals("petitions"))
            {
        %>
        <input type="hidden" id="pageno" value="0"/>
        Sort By
        <select id="Select1" >
            <option>Time</option>
        </select>
        <br />
        <a href="#">Redirect</a>
        <a href="javascript:selectAll()">Select All</a>
        <div id="compdiv">
        </div>
        <br />
        <a href="#">Redirect</a>
        <a href="javascript:selectAll()">Select All</a>
        <%}
        else if(request.getParameter("box").equals("informations"))
            {
        %>
        <input type="hidden" id="pageno" value="0"/>
        Sort By
        <select id="Select1" >
            <option>Time</option>
        </select>
        <br />
        <a href="javascript:deletemsg();load('informations')">Delete</a>
        <a href="#">Reply</a>
        <a href="#">Forward</a>
        <a href="javascript:selectAll()">Select All</a>
        <div id="compdiv">
        </div>
        <br />
        <a href="javascript:deletemsg();load('informations')">Delete</a>
        <a href="#">Reply</a>
        <a href="#">Forward</a>
        <a href="javascript:selectAll()">Select All</a>
        <%}
        else if(request.getParameter("box").equals("cases"))
            {
        %>
        <input type="hidden" id="pageno" value="0"/>
        Sort By
        <select id="Select1" >
            <option>Time</option>
        </select>
        <br />
        <a href="#">Forward</a>
        <a href="javascript:selectAll()">Select All</a>
        <div id="compdiv">
        </div>
        <br />
        <a href="#">Forward</a>
        <a href="javascript:selectAll()">Select All</a>
        <%
        }
       }
    else if(request.getParameter("get")!=null)
        {
            if(request.getParameter("get").equals("petitions"))
                out.print(comp.prepPeti(lg.getId(), "Time",lg.checkSession(),request.getParameter("get"),request.getParameter("page")));
            else if(request.getParameter("get").equals("informations"))
                out.print(info.prepInfo(lg.getId(), "Time",lg.checkSession(),request.getParameter("get"),request.getParameter("page")));
            else if(request.getParameter("get").equals("cases"))
                out.print(cases.prepCase(lg.getId(), "Time",lg.checkSession(),request.getParameter("get"),request.getParameter("page")));
        }
    else if(request.getParameter("open")!=null)
        {
            if(request.getParameter("open").equals("petitions"))
                out.print(comp.openPeti(lg.getId(), request.getParameter("no"),lg.checkSession(),request.getParameter("open")));
            else if(request.getParameter("open").equals("informations"))
                out.print(info.openInfo(lg.getId(), request.getParameter("no"),lg.checkSession(),request.getParameter("open")));
            else if(request.getParameter("open").equals("cases"))
                out.print(cases.openCase(lg.getId(), request.getParameter("no"),lg.checkSession(),request.getParameter("open")));
        }
}
%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<jsp:useBean id="lg" class="login.Login" scope="session"/>
<jsp:setProperty name="lg" property="*" />
<%
if(request.getParameter("get")!=null)
{
    String to=request.getParameter("to");
    out.print(lg.getChat(to));
}
else if(request.getParameter("getlist")!=null)
{
    List<String> l=lg.getOnlineList();
    out.println("<ul>");
    for(String i:l)
        {%>
        <li onclick="startchat('<%= i%>')"><%= i %>
        </li>
        
        <%}
    out.println("</ul>");
}
else if(request.getParameter("startchat")!=null)
{
    String to=request.getParameter("to");
    lg.startChat(to);
        %>
        <div class="chatbox"><input type="hidden" value="<%= to %>"/>
    <div style="height: 100%">
      <div class="chathead" onclick="this.parentNode.style.visibility = 'hidden';this.parentNode.parentNode.className='chatboxheadonly';this.parentNode.nextSibling.style.visibility = 'visible';">
	  		<div style="float:left;">Chat with <%= to %></div>
                        <div align="right"><a onclick="delnode(this.parentNode.parentNode.parentNode.parentNode)">X</a></div>
      </div>
        <div readonly="readonly" cols="22" rows="6" class="chattext"></div><textarea id="<%= to %>" cols="22" rows="3" style="text-align: left" onkeypress="sendchat(this,event)" onkeyup="clearchat(this)"></textarea><input type="hidden" value="<%= to %>"/>
    </div><div class="chathead" style="visibility: hidden" onclick="this.style.visibility = 'hidden';this.previousSibling.style.visibility = 'visible';this.parentNode.className='chatbox'">
	  		<div style="float:left">Chat with <%= to %></div>
          	<div align="right">X</div>
      </div>
    </div>
       
        <%
}
else if(request.getParameter("endchat")!=null)
{
    String to=request.getParameter("to");
    lg.stopChat(to);
}
else if(request.getParameter("checknew")!=null)
{
    String n=lg.checkNewChat();
    if(n!=null)
    {
        out.println("*"+n+"*");
    }
}
else if(request.getParameter("init")!=null)
{%>
<select id="chattingwith" style="visibility:hidden">
            <%
            for(String i:lg.chattingwith)
            {
            %>
            <option value="<%= i%>"><%= i %></option>
            <%
            System.out.println("Reached");
            }
            %>
	</select>
	<div class="chatlist">
    <div>
      <div class="chathead" onclick="this.parentNode.style.visibility = 'hidden';this.parentNode.parentNode.className='chatlistheadonly';this.parentNode.nextSibling.style.visibility = 'visible';">
	  		<div style="float:left">Online Officers</div>
          	<div align="right">-</div>
      </div>
        <div readonly="readonly" cols="22" rows="6" id="onlinelist"></div>
    </div><div class="chathead" style="visibility: hidden" onclick="this.style.visibility = 'hidden';this.previousSibling.style.visibility = 'visible';this.parentNode.className='chatlist'">
	  		<div style="float:left">Online Officers</div>
          	<div align="right">-</div>
      </div>
    </div>

<%}
else
{
    String to=request.getParameter("to");
    String msg=request.getParameter("msg");
    System.out.println(to+"::"+msg);
    lg.addChat(msg, to);
}
%>

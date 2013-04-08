<%@page language="java" import="java.sql.*, usr.Profile "%>
<jsp:useBean id="lg" class="login.Login" scope="request"/>
<jsp:setProperty name="lg" property="*" />
<%
lg.init(request.getSession());
if(lg.checkSession()!=0)
{
    Profile p=new Profile(request);
    p.getProfileInfo();
%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Info</title>
        <script type="text/javascript">
            function redirectparent(x)
            {
                window.top.window.location=x;
            }
        </script>
        <style type="text/css">
            root {
            display: block;
            }
            *{
            margin:0;
            padding:0;
            }

            a {
            text-decoration: none;
            color: #648CA5;
            }
            a:hover {
            text-decoration: underline;
            color: #111;
            }

            #profdiv {
            font-family: 'Courier New', Courier, monospace;
            font-weight: bold;
            font-size: small;
            color: #648CA5;
            border-color:#888;
            border-style: solid;
            float: left;
            width: 630px;
            }
            #profdiv td
            {
                    align: right;
                    color: #648CA5;
                    vertical-align: top;
            }

            .proleft{
            float: left;
            height: 50px;
            width: 150px;
            text-align: right;
            }

            .proright{
            float: left;
            border-color:#888;
            border-style: solid;
            text-align: left;
            padding: 20px;
            }

        </style>
    </head>
    <body>
        <div id="profdiv">
  
		  <table width="100%" cellspacing="10">
            <tr>
              <td><img src="stars/<% out.print(p.imgurl); %>" alt="profilepic" width="150" height="152"></td>
              <td><p>&nbsp;</p>
              <div class="viewproright">
              <img src="images/message.gif" alt="" /><a href="javascript:redirectparent('<% out.print("messages.jsp?box=compose&to="+p.name);%>')">Send Message</a>
              <p>&nbsp;</p>
              <img src="images/complaint.gif" alt="" /><a href="#">Give Compliant</a>
              <p>&nbsp;</p>
              <img src="images/information.gif" alt="" /><a href="#">Give an information </a>
              </div>
			  </td>
            </tr>
            <tr>
              <td width="44%"><div align="right">Name:</div></td>
              <td width="56%"><% out.print(p.fname+" "+p.lname); %></td>
            </tr>
            <tr>
              <td><div align="right">Address/ Location:</div></td>
              <td><% out.print(p.address); %></td>
            </tr>
            <tr>
              <td><div align="right">email ID: </div></td>
              <td><% out.print(p.email); %></td>
            </tr>
            <tr>
              <td><div align="right">Occupation/ Designation:</div></td>
              <td><% out.print(p.designation); %></td>
            </tr>
            <tr>
              <td><div align="right">Phone Number: </div></td>
              <td><% out.print(p.contactno); %></td>
            </tr>
            <tr>
              <td><div align="right">Staff Info: </div></td>
              <td><% out.print(p.staffinfo); %></td>
            </tr>
            <tr>
              <td><div align="right">Hospitals:</div></td>
              <td><% out.print(p.hospitals); %></td>
            </tr>
            <tr>
              <td><div align="right">Highways:</div></td>
              <td><% out.print(p.highway); %></td>
            </tr>
          </table>
        </div>

    </body>
</html>
<%
}
else
    {
response.sendRedirect("login.jsp");
}
%>
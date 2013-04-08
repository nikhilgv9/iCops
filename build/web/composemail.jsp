<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="lg" class="login.Login" scope="request"/>
<jsp:useBean id="msg" class="communication.Messages" scope="page"/>
<jsp:setProperty name="msg" property="*" />
<jsp:setProperty name="lg" property="*" />
<%
lg.init(request.getSession());
int n=lg.checkSession();
if(n!=0)
{
    if(request.getParameter("compose")!=null)
        {
        if(request.getParameter("compose").equals("true"))
            {%>
            <ul>
                <li style="text-align: left;width: 595px"><span>To</span><input id="to" style="width: 100%" type="text" /></li>
                <li style="text-align: left;width: 595px"><span>Subject</span><input id="sub"  style="width: 100%" type="text" /><br/></li>
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
<iframe id="edit" height="400px" width="100%"></iframe>
<iframe id="colorpalette" src="richedit_files/colors.htm" style="visibility: hidden; position: absolute; left: 406px; top: 199px;" height="170" width="250"></iframe>
<input onclick="viewsource(this.checked)" type="checkbox"/>
View HTML Source
                </li>
                <li>
                    <input type="button" value="Send" onclick="send()"/>
                    <input type="button" value="Save as draft" onclick="save()"/>
                </li>
            </ul>
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
            
<%          }
        }
        else if(request.getParameter("send")!=null)
            {
                if(request.getParameter("send").equals("true"))
                {
                out.println(msg.sendMessage(lg.getId(), request.getParameter("to"),
                        request.getParameter("sub"),request.getParameter("msg"),
                        lg.checkSession()));
                }
                else{
                    out.println(msg.saveMessage(lg.getId(), request.getParameter("to"),
                        request.getParameter("sub"),request.getParameter("msg"),
                        lg.checkSession()));
                    }
            }
    }
%>
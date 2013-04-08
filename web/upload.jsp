<%@page language="java" import="org.apache.tomcat.util.http.fileupload.*, java.util.*, java.io.*, tools.*"%>
 <%
            String result="0";
            String filename=" ";
            if(FileUpload.isMultipartContent(request))
            {
                String path=application.getRealPath("/");
                FileUpload f=new  FileUpload(new DefaultFileItemFactory());
                List<FileItem> l=f.parseRequest(request);
                for(FileItem file:l)
                {
                    String oriname=file.getName();
                    if(oriname.equals("")) break;
                    RandomString name=new RandomString(16);
                    filename="\\images\\users\\"+name.nextString()+oriname.substring(oriname.lastIndexOf("."),oriname.length());
                    File fi=new File(path+filename);
                    file.write(fi);
                    out.println(file.getContentType());
                    break;
                }
                filename=filename.replace('\\', '/');
                result="1";
            }

    %>
<html>
<head>
<title>Upload</title>
<style>
    #f1_upload_process
    {
        z-index: 100;
        position: absolute;
        visibility: hidden;
        text-align: center;
        width: 400px;
        margin: 0px;
        padding: 0px;
        background-color: #fff;
        border: 1px solid #ccc;
    }
    form
    {
        text-align: center;
        width: 390px;
        margin: 0px;
        padding: 5px;
        background-color: #fff;
        border: 1px solid #ccc;
    }
</style>
 <script language="javascript" type="text/javascript">
     if(window.top.window.stopUpload)
        {
        window.top.window.stopUpload(<% out.print(result); %>);
        window.top.window.document.getElementById("path").value=".."+"<%out.print(filename);%>";
        }
    </script>

<script type="text/javascript">

    function show(){
        document.getElementById('fileupload').style.visibility = 'visible';
    }

    function startUpload() {
        document.getElementById('f1_upload_process').style.visibility = 'visible'; return true;
    }

    function stopUpload(success)
    {
        var result = '';
        if (success == 1) { 
            document.getElementById('f1_upload_process').style.visibility = 'hidden';
            document.getElementById('fileupload').style.visibility = 'hidden';   
        }
        return true;
   }
</script>
</head>
<body>
    <input type="button" value="Upload" onclick="show()"/>
    <input type="text"/>
    <input type="hidden" id="path"/>
    <div id="fileupload" style="visibility: hidden; position: absolute; top: 300px; left: 300px;">
    <p id="f1_upload_process">
        Loading...<img src="loader.gif" /></p>
    <form action="upload.jsp" method="post" enctype="multipart/form-data" target="upload_target" onsubmit="startUpload();">
    File:<input name="myfile" type="file"/>
    <input type="submit" name="submitBtn" value="Upload" />
    <input type="button" value="cancel" onclick="stopUpload(1)"/>
    </form>
    <iframe id="upload_target" name="upload_target" src="#" style="width: 0; height: 0; border: 0px solid #fff;">
    </iframe>
    </div>
</body>
</html>
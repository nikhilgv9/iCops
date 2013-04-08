<%@page language="java" import="org.apache.tomcat.util.http.fileupload.*, java.util.*, java.io.*"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <%
            if(FileUpload.isMultipartContent(request))
            {
                String path=application.getRealPath("/");
                FileUpload f=new  FileUpload(new DefaultFileItemFactory());
                List l=f.parseRequest(request);
                FileItem file = (FileItem)l.get(0);
                File fi=new File(path+"\\images\\users\\nikhil.jpg");
                file.write(fi);
                out.println(file.getContentType());
            }

    %>
        <form action="" method="post" enctype="multipart/form-data">
            <input type="file" name="upload"/>
            <input type="Submit" value="upload"/>
        </form>
    </body>
</html>

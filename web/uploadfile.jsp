<%@page language="java" import="org.apache.tomcat.util.http.fileupload.*, java.util.*, java.io.*, tools.*"%>
<%            String result="0";
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
                String s=request.getRequestURL().toString();
                String[] a=s.split("/");
                filename="http://"+a[2]+application.getContextPath()+filename;
                System.out.println(filename);
                result="1";
            }

            %>
            <html>
                <head>
                    <script type="text/javascript">
                        if(window.top.window.stopUpload)
                            {
                            window.top.window.stopUpload(<% out.print(result); %>,"<% out.print(filename); %>");
                            }
                    </script>
                </head>
            </html>
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package communication;
import java.util.*;
import database.*;

/**
 *
 * @author Nikhil
 */
public class Informations {
 MsgDetails info=null;
    int done=0;

    public void getMessages(String id,String sortby,int type)
    {
        info=new MsgDetails("informations");
        Db db;
        db=new Db();
       db.selectTable("INFORMATIONS");
       db.setCond(informations.OFFICER_ID, id);
       info.title=db.select(informations.SUBJECT);
       info.msg=db.select(informations.TEXT);
       info.from=db.select(informations.CITIZEN_ID);
       info.to=db.select(informations.OFFICER_ID);
       info.msgid=db.select(informations.ID);
       info.status=db.select(informations.STATUS);

    }

    public String prepInfo(String id,String sortby,int type,String box,String p)
    {
        int page=Integer.parseInt(p);
        if(done==0)
        {
                getMessages(id,sortby,type);
                done=1;
        }
        if(box.equals("informations"))
            return prepInformations(id,sortby,type,info,page);
        return "";
    }

     public String prepInformations(String id,String sortby,int type,MsgDetails box,int page)
    {
           String ret="";
           int nopages=30;
           int limit=(box.title.size()>page*nopages+nopages)?(page*nopages+nopages):(box.title.size());
           ret+="<div style=\'text-align: right; background:#333;\'><div style=\'float:left;\'>&nbsp&nbsp&nbsp<a href=\"#\">"+box.box.toUpperCase()+"</a></div>";
           if(page>=1)
            ret+="<a href=\"javascript:prevnext(\'"+box.box+"\',"+(page-1)+")\">&lt;&lt;Newer</a>&nbsp;&nbsp;";
           if(limit<box.title.size())
               ret+="<a href=\"javascript:prevnext(\'"+box.box+"\',"+(page+1)+")\">Older&gt;&gt;</a>";
           else ret+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
           ret+="</div>";
           ret+="<table>";
           for(int i=page*nopages;i<limit;i++)
           {
               ret+="<tr>";
               String m=box.msg.get(i);
               if(m.length()>54)
                   m=m.substring(0,54);
               m=m.replaceAll(">", "&gt;");
               m=m.replaceAll("<", "&lt;");
               String t=box.title.get(i);
               if(t.length()>10)
                   t=t.substring(0,10);
               ret+="<table class=\'msgitem\' style=\"border-collapse: collapse;\"><tr>";
               ret+="<td style=\"text-align:left;width: 20px;\"><input id=\'chk"+i+"\' value=\'"+box.msgid.get(i)+"\' type=\'checkbox\'/></td>";
               ret+="<td onclick=\"javascript:openmsg("+box.msgid.get(i)+",\'"+box.box+"\')\" style=\"text-align:left; width:100px;\">"+t+"</td>";
               ret+="<td onclick=\"javascript:openmsg("+box.msgid.get(i)+",\'"+box.box+"\')\" style=\"text-align:left; width: 100px;\">";
               if(box.box.equals("inbox")||box.box.equals("trash"))
                    ret+="From:"+box.from.get(i);
               else ret+="To:"+box.to.get(i);
               ret+="</td><td onclick=\"javascript:openmsg("+box.msgid.get(i)+",\'"+box.box+"\')\" style=\"text-align:right;\">"+m+"</td>";
               ret+="</tr></table>";
               ret+="</tr>";
           }
               ret+="</table>";

        return ret;
    }

     public String openInfo(String id,String number,int type,String box)
    {

            getMessages(id,"Time",type);
            done=1;
        
            return openInformations(id,number,type,info);
    }
    public String openInformations(String id,String number,int type,MsgDetails box)
    {
        int i=box.msgid.indexOf(number);
        String ret="";
        String prev=null,next=null;
        if(i>=0)
        {
            try
            {
            prev=box.msgid.get(i-1);
            next=box.msgid.get(i+1);
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
              prev=null;
              try{
              next=box.msgid.get(i+1);
              }
              catch(Exception ex){
                  next=null;
              }
            }
            catch(IndexOutOfBoundsException e)
            {
                next=null;
            }



            ret+="<div style=\'text-align: right; background:#333;\'><div style=\'float:left;\'>&nbsp&nbsp&nbsp<a href=\"#\">"+box.box.toUpperCase()+"</a></div><br /></div>";
            ret+="<div class=\"msgitem\"><ul><li>";
            if(box.box.equals("inbox")||box.box.equals("trash"))
                    ret+="From:"+box.from.get(i);
            else
                ret+="To:"+box.to.get(i);
            ret+="</li><li style=\'float: right\'>";
            if(prev!=null)
                ret+="<a class=\'blue\' href=\"javascript:openmsg("+prev+",\'"+box.box+"\')\">"+"Previous </a>";
            if(next!=null)
                ret+="<a class=\'blue\' href=\"javascript:openmsg("+next+",\'"+box.box+"\')\">"+"Next </a>";
            ret+="</li></ul>";
            ret+="<br /><br /><span class=\'sub\'>Subject:"+box.title.get(i)+"</span><br /><br />";
            ret+="&nbsp&nbsp&nbsp&nbsp"+box.msg.get(i)+"<br /><br /><br />";
            ret+="<a class=\'blue\' href=\"javascript:load(\'"+box.box+"\')\">Back To "+box.box+"</a>";
            ret+="<br /></div>";

        }
        return ret;
    }
}

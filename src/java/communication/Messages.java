/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package communication;
import java.util.*;
import database.*;
import java.sql.*;
import tools.Time;


public class Messages
{
    MsgDetails all=null;
    MsgDetails inbox=null;
    MsgDetails sent=null;
    MsgDetails draft=null;
    MsgDetails trash=null;
    int done=0;

    public void getMessages(String id,String sortby,int type)
    {
        all=new MsgDetails("all");
        inbox=new MsgDetails("inbox");
        sent=new MsgDetails("sent");
        draft=new MsgDetails("drafts");
        trash=new MsgDetails("trash");
        Db db;
        db=new Db();
        List<String> name=null;
       db.selectTable("USERS");
       name=db.select(users.OFFICER_NAME, users.ID, id);
       db.selectTable("MESSAGES");
       db.setCond(messages.MSG_TO, name.get(0));
       db.setOrCond(messages.MSG_FROM, name.get(0));
       //String cond=messages.MSG_TO.str+"=\'"+name.get(0)+"\' OR "+messages.MSG_FROM.str+"=\'"+name.get(0)+"\'";

       all.title=db.select(messages.MSG_TITLE);
       all.msg=db.select(messages.MSG_DETAILS);
       all.from=db.select(messages.MSG_FROM);
       all.to=db.select(messages.MSG_TO);
       all.msgid=db.select(messages.ID);
       all.status=db.select(messages.STATUS);

       for(int i=0;i<all.title.size();i++)
       {
           if(all.from.get(i).equals(name.get(0)))
           {
               if(all.status.get(i).equals("1"))
               {
                   sent.title.add(all.title.get(i));
                   sent.msg.add(all.msg.get(i));
                   sent.to.add(all.to.get(i));
                   sent.from.add(all.from.get(i));
                   sent.msgid.add(all.msgid.get(i));
                   sent.status.add(all.status.get(i));
               }
               else if(all.status.get(i).equals("2"))
               {
                   draft.title.add(all.title.get(i));
                   draft.msg.add(all.msg.get(i));
                   draft.to.add(all.to.get(i));
                   draft.from.add(all.from.get(i));
                   draft.msgid.add(all.msgid.get(i));
                   draft.status.add(all.status.get(i));
               }
           }
           if(all.status.get(i).equals("0"))
           {
               trash.title.add(all.title.get(i));
               trash.msg.add(all.msg.get(i));
               trash.to.add(all.to.get(i));
               trash.from.add(all.from.get(i));
               trash.msgid.add(all.msgid.get(i));
               trash.status.add(all.status.get(i));
           }
           else if(all.to.get(i).equals(name.get(0)) && all.status.get(i).equals("1"))
           {
                   inbox.title.add(all.title.get(i));
                   inbox.msg.add(all.msg.get(i));
                   inbox.from.add(all.from.get(i));
                   inbox.msgid.add(all.msgid.get(i));
                   inbox.status.add(all.status.get(i));
           }
       }
    }
    public String prepMsg(String id,String sortby,int type,String box,String p)
    {
        int page=Integer.parseInt(p);  
        if(done==0)
        {
                getMessages(id,sortby,type);
                done=1;
        }
        if(box.equals("inbox"))
            return prepMessages(id,sortby,type,inbox,page);
        else if(box.equals("sent"))
            return prepMessages(id,sortby,type,sent,page);
        else if(box.equals("drafts"))
            return prepMessages(id,sortby,type,draft,page);
        else if(box.equals("trash"))
            return prepMessages(id,sortby,type,trash,page);
        else return "";
    }
    public String prepMessages(String id,String sortby,int type,MsgDetails box,int page)
    {
           String ret="";
           int nopages=30;
           int limit=(box.title.size()>page*nopages+nopages)?(page*nopages+nopages):(box.title.size());
           ret+="<div style=\'text-align: right; background:#333;\'><div style=\'float:left;\'>&nbsp;&nbsp;&nbsp;<a href=\"#\">"+box.box.toUpperCase()+"</a></div>";
           if(page>=1)
            ret+="<a href=\"javascript:prevnext(\'"+box.box+"\',"+(page-1)+")\">&lt;&lt;Newer</a>&nbsp;&nbsp;";
           if(limit<box.title.size())
               ret+="<a href=\"javascript:prevnext(\'"+box.box+"\',"+(page+1)+")\">Older&gt;&gt;</a>";
           else ret+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
           ret+="</div>";
           
           ret+="<table style=\'width: 100%\'>";
           
           
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
               ret+="<table class=\'msgitem\' style=\"border-collapse: collapse; width:100%;\"><tr>";
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
    public String openMsg(String id,String number,int type,String box)
    {
        if(done==0)
        {
            getMessages(id,"Time",type);
            done=1;
        }
        if(box.equals("inbox"))
            return openMessage(id,number,type,inbox);
        else if(box.equals("sent"))
            return openMessage(id,number,type,sent);
        else if(box.equals("drafts"))
            return openMessage(id,number,type,draft);
        else if(box.equals("trash"))
            return openMessage(id,number,type,trash);
        else return "";
    }
    public String openMessage(String id,String number,int type,MsgDetails box)
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
            ret+="<div style=\'visibility: hidden; position: absolute;>\'<input type=\"hidden\" id=\"reply\" value=\""+box.from.get(i)+"\"/>";
            ret+="<textarea id=\"fwd\" style=\'visibility: hidden\'>"+box.msg.get(i)+"</textarea>";
            ret+="<textarea id=\"subject\" style=\'visibility: hidden;\'"+box.title.get(i)+"</textarea></div>";
            
        }
        return ret;
    }
    public String sendMessage(String id,String to,String sub,String msg,int type)
    {
        Db db;
        db=new Db();
           System.out.println(msg);
           msg=msg.replaceAll("..amb", "&");
           msg=msg.replaceAll("..sem", ";");
           msg=msg.replaceAll("nbsp;", "nbs0p;");
           msg=msg.replaceAll("nbsp", "nbsp;");
           msg=msg.replaceAll("nbs0p;", "nbsp;");
           System.out.println(msg);
           db.selectTable("USERS");
           List<String> name=db.select(users.OFFICER_NAME, users.ID, id);
           db.selectTable("MESSAGES");
           List<String> l=new ArrayList<String>();
           l.add(name.get(0));
           l.add(to);
           l.add(sub);
           l.add(msg);
           l.add("1");
           l.add(Time.getTimeStamp());
           if(db.insert(l)!=0)
           {
               done=0;
               return "Message sent Successfully<br /><a href=\'javascript:compose()\'>Back</a>";
           }
        
        return "Message sending failed<br /><a href=\'javascript:compose()\'>Back</a>";
    }
    public String saveMessage(String id,String to,String sub,String msg,int type)
    {
        Db db;
        db=new Db();
        Calendar c=new GregorianCalendar();
        System.out.println(c.getTime().toGMTString());

           msg=msg.replaceAll("..amb", "&");
           msg=msg.replaceAll("..sem", ";");
           msg=msg.replaceAll("nbsp;", "nbs0p;");
           msg=msg.replaceAll("nbsp", "nbsp;");
           msg=msg.replaceAll("nbs0p;", "nbsp;");
           System.out.println(msg);
           db.selectTable("USERS");
           List<String> name=db.select(users.OFFICER_NAME, users.ID, id);
           db.selectTable("MESSAGES");
           List<String> l=new ArrayList<String>();
           l.add(name.get(0));
           l.add(to);
           l.add(sub);
           l.add(msg);
           l.add("2");
           l.add(Time.getTimeStamp());
           System.out.print(l);
           if(db.insert(l)!=0)
           {
               done=0;
               return "Message saved Successfully<br /><a href=\'javascript:compose()\'>Back</a>";
           }
        
        return "Message saving failed <br /><a href=\'javascript:compose()\'>Back</a>";
    }
    public String deleteMessage(String id,String list,int type)
    {
        System.out.println(list);
        String del[]=list.split("/");
        String query;
        List<String> qlist=new ArrayList<String>();
        Db db=new Db();
        db.selectTable("MESSAGES");
        List<String> l=new ArrayList<String>();
        l.add(null);
        l.add(null);
        l.add(null);
        l.add(null);
        l.add("0");
        l.add(null);
        for(int i=0;i<del.length;i++)
        {
            db.setCond(messages.ID, del[i]);
            query=db.makeUpdatequery(l);
            qlist.add(query);
        }
        int ret=db.batchExec(qlist);
        if(ret!=0)
        {
            done=0;
            return "Successful";
        }
        else
            return "Not successfull";
    }

    public String deleteForever(String id,String list,int type)
    {
        System.out.println(list);
        String del[]=list.split("/");
        String query;
        Db db=new Db();
        db.selectTable("MESSAGES");
        db.setCond(messages.ID, del[0]);
        for(int i=1;i<del.length;i++)
        {
            db.setOrCond(messages.ID, del[i]);
        }
        int ret=db.delete();

        if(ret!=0)
        {
            done=0;
            return "Successful";
        }
        else
            return "Not successfull";
    }

     public String restoreMessage(String id,String list,int type)
    {
        System.out.println(list);
        String del[]=list.split("/");
        String query;
        List<String> qlist=new ArrayList<String>();
        Db db=new Db();
        db.selectTable("MESSAGES");
        List<String> l=new ArrayList<String>();
        l.add(null);
        l.add(null);
        l.add(null);
        l.add(null);
        l.add("1");
        l.add(null);
        for(int i=0;i<del.length;i++)
        {
            db.setCond(messages.ID, del[i]);
            query=db.makeUpdatequery(l);
            qlist.add(query);
        }
        int ret=db.batchExec(qlist);
        if(ret!=0)
        {
            done=0;
            return "Successful";
        }
        else
            return "Not successfull";
    }
    public static void main(String args[])
    {
        Calendar c=new GregorianCalendar();
        Timestamp t=new Timestamp(c.getTimeInMillis());
        String s=t.toString();
        String a[]=s.split(" ");
        System.out.println(a[1]);
    }

}

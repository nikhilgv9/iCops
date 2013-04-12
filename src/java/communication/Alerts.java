/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package communication;
import java.util.*;
import database.*;
import tools.*;
import javax.servlet.http.*;

/**
 *
 * @author Nikhil
 */
public class Alerts 
{
 static MsgDetails alert=null;
    static int done=0;

public void getAlerts(String id,String box)
    {
        if(box.equals("0"))
            alert=new MsgDetails("all alerts");
        else if(box.equals("1"))
            alert=new MsgDetails("wanted");
         else if(box.equals("2"))
            alert=new MsgDetails("Missing Persons");
         else if(box.equals("3"))
            alert=new MsgDetails("road accidents");
         else if(box.equals("4"))
            alert=new MsgDetails("Other alerts");
       alert.type=box;
       Db db;
       db=new Db();
       db.selectTable("ALERTS");
       db.setOrderByDesc(alerts.TIME);
       if(!box.equals("0"))
            db.setCond(alerts.TYPE, box);
       if(box.equals("4"))
       {
           ;
       }
       alert.title=db.select(alerts.TITLE);
       alert.msg=db.select(alerts.TEXT);
       alert.from=db.select(alerts.FROM);
       alert.time=db.select(alerts.TIME);
       alert.time=alert.formatTime();
       alert.msgid=db.select(alerts.ID);

    }

    public String prepAlert(String id,String box,String p)
    {
        int page=Integer.parseInt(p);
        getAlerts(id,box);
        done=1;
        return prepAlerts(alert,page);
    }

     public String prepAlerts(MsgDetails box,int page)
    {
           String ret="";
           int nopages=10;
           int limit=(box.title.size()>page*nopages+nopages)?(page*nopages+nopages):(box.title.size());
           ret+="<div style=\'text-align: right; background:#333;\'><div style=\'float:left;\'>&nbsp&nbsp&nbsp<a href=\"#\">"+box.box.toUpperCase()+"</a></div>";
           if(page>=1)
            ret+="<a href=\"alerts.jsp?box="+box.type+"&page="+(page-1)+"\">&lt;&lt;Newer</a>&nbsp;&nbsp;";
           if(limit<box.title.size())
               ret+="<a href=\"alerts.jsp?box="+box.type+"&page="+(page+1)+"\">Older&gt;&gt;</a>";
           else ret+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
           ret+="</div>";
           for(int i=page*nopages;i<limit;i++)
           {
                ret+="<table style=\'background:#ccd0de;\' width=\"100%\" border=\"8\" cellspacing=\"2\" bordercolor=\"#666633\">";
               ret+="<tr>";
               ret+="<td height=\"32px\"><p>Subject:"+box.title.get(i)+"</p>";
               ret+="<p>Date:"+box.time.get(i)+"</p>";
               ret+="<p>From:"+box.from.get(i)+"</p>";
               ret+="</td>";

               ret+="<td width=\"32px\"><a href=\"javascript:deletealert(\'"+box.msgid.get(i)+"\')\">Delete</a></td>";

               ret+="</tr>";
               ret+="<tr>";
               ret+="<td>"+box.msg.get(i)+"</td>";
               ret+="</tr>";
               ret+="</table>";
           }
        return ret;
    }

     public void makeAlert(HttpServletRequest r)
     {
         Db db=new Db();
         List<String> l=new ArrayList<String>();
         l.add(r.getParameter("box"));
         if(r.getParameter("box").equals("4"))
         {
            l.add(r.getParameter("to"));
         }
         else
         {
             l.add(null);
         }
         l.add(r.getParameter("text"));
         l.add(Time.getTimeStamp());
         db.selectTable("USERS");
         String id=(String)r.getSession().getAttribute("id");
         db.setCond(users.ID, id);
         l.add(db.select(users.OFFICER_NAME).get(0));
         l.add(r.getParameter("sub"));
         db.selectTable("ALERTS");
         db.insert(l);
     }
     public void delete(String id)
     {
         Db db=new Db();
         db.selectTable("ALERTS");
         db.setCond(alerts.ID, id);
         db.delete();
     }

}

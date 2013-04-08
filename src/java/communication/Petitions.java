/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package communication;
import database.*;
import java.util.*;

/**
 *
 * @author Nikhil
 */
public class Petitions {

    MsgDetails peti=null;
    int done=0;

    public void getMessages(String id,String sortby,int type)
    {
        peti=new MsgDetails("petitions");
        Db db;
        db=new Db();
       db.selectTable("PETITIONS");
       //String cond=petitions.OFFICER_ID.str+"="+id;
       db.setCond(petitions.OFFICER_ID, id);
       peti.title=db.select(petitions.SUBJECT);
       peti.msg=db.select(petitions.TEXT);
       peti.from=db.select(petitions.CITIZEN_ID);
       peti.to=db.select(petitions.OFFICER_ID);
       peti.msgid=db.select(petitions.ID);
       peti.status=db.select(petitions.STATUS);

    }

    public String prepPeti(String id,String sortby,int type,String box,String p)
    {
        int page=Integer.parseInt(p);
        getMessages(id,sortby,type);
        done=1;
        
        return prepPetitions(id,sortby,type,peti,page);
    }

     public String prepPetitions(String id,String sortby,int type,MsgDetails box,int page)
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
           for(int i=0;i<box.title.size();i++)
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

     public String openPeti(String id,String number,int type,String box)
    {
        if(done==0)
        {
            getMessages(id,"Time",type);
            done=1;
        }
        if(box.equals("petitions"))
            return openPetitions(id,number,type,peti);
        return " ";
    }
    public String openPetitions(String id,String number,int type,MsgDetails box)
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

                    ret+="<div id=\"cmpitem\">"+
                    "<table><tr>"+
                    "<td style=\"height: 77px; width: 171px\"><h5>Regstered By:</h5>"+box.from.get(i)+"</td>"+
                    "<td style=\"height: 77px; width: 266px\"></td>"+
                    "<td style=\"height: 77px; text-align: center;\">";
                    if(prev!=null)
                            ret+="<a class=\'blue\' href=\"javascript:openmsg("+prev+",\'"+box.box+"\')\">Previous</a>";
                    ret+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ";
                    if(next!=null)
                            ret+="<a class=\'blue\' href=\"javascript:openmsg("+next+",\'"+box.box+"\')\">Next</a>";
                    ret+="</td></tr><tr>"+
                    "<td colspan=\"3\" style=\"vertical-align: top;\"><h5>Petition Details</h5>"+
                            box.msg.get(i)+"<br /><h5>Date:</h5></td>"+
                    "</tr><tr><td style=\"width: 171px\"><a class=\'blue\' href=\"javascript:load(\'"+box.box+"\')\">Back&nbsp; To "+
                            box.box+"</a></td>"+
                    "<td style=\"width: 266px\">&nbsp;</td><td style=\"text-align: center\">";
                    if(box.status.get(i).equals("1"))
                        ret+="<input id=\"Button1\" type=\"button\" value=\"Take Action\" onclick=\"takeaction("+box.msgid.get(i)+")\"/>";
                    else
                        ret+="<h3>Action Taken</h3>";
                    ret+=" </td></tr> </table></div>";


           /* ret+="<div class=\"msgitem\"><ul><li>";
            ret+="From:"+box.from.get(i);

            ret+="</li><li style=\'float: right\'>";
            if(prev!=null)
                ret+="<a class=\'blue\' href=\"javascript:openmsg("+prev+",\'"+box.box+"\')\">"+"Previous </a>";
            if(next!=null)
                ret+="<a class=\'blue\' href=\"javascript:openmsg("+next+",\'"+box.box+"\')\">"+"Next </a>";
            ret+="</li></ul>";
            ret+="<br /><br /><span class=\'sub\'>Subject:"+box.title.get(i)+"</span><br /><br />";
            ret+="&nbsp&nbsp&nbsp&nbsp"+box.msg.get(i)+"<br /><br /><br />";
            ret+="<a class=\'blue\' href=\"javascript:load(\'"+box.box+"\')\">Back To "+box.box+"</a>";
            ret+="<br /></div>";*/

        }
        return ret;
    }

}

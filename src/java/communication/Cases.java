/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package communication;
import database.*;
import java.util.*;
import javax.servlet.http.*;
import tools.*;
/**
 *
 * @author Nikhil
 */
public class Cases {

    MsgDetails cas=null;
    int done=0;
    public String caseno,filedon,createdon,registeredby,redirectedfrom,caseprog,text,weight;

    public int registerCase(String compno,String id,int type)
    {
        Db db=new Db();
        db.selectTable("PETITIONS");
        List<String> l=new ArrayList<String>();
        db.setCond(petitions.ID, compno);
        l.add((db.select(petitions.ID)).get(0));
        l.add((db.select(petitions.SUBJECT)).get(0));
        l.add((db.select(petitions.OFFICER_ID)).get(0));
        l.add((db.select(petitions.CITIZEN_ID)).get(0));
        l.add("0");
        l.add((db.select(petitions.TIME)).get(0));
        l.add(Time.getTimeStamp());
        l.add((db.select(petitions.REDIRECTEDFROM)).get(0));
        l.add("");
        l.add("2");
        List<String> l2=new ArrayList<String>();
        l2.add(null);l2.add(null);l2.add(null);l2.add(null);l2.add(null);
        l2.add("2");
        l2.add(null);l2.add(null);
        db.update(l2);
        db.selectTable("CASES");
        db.insert(l);
        return 1;
    }

    public int updateCase(HttpServletRequest r)
    {
        
        try
        {
            List<String> l=new ArrayList<String>();
            List<String> l2=new ArrayList<String>();
            l2.add(null); l2.add(null); l2.add(null); l2.add(null);
            l2.add(null); l2.add(null); l2.add(null); l2.add(null);
            l2.add(null); l2.add(null);
            l.add(null);l.add(null);l.add(null);l.add(null);
            l.add(r.getParameter("prog"));
            l.add(null);l.add(null);l.add(null);
            String text=r.getParameter("text");
            text=text.replaceAll("\n", "");
            text=text.replaceAll("\r", "");
            l.add(text);
            l.add(r.getParameter("weight"));
            if(l.equals(l2))
            {
                System.out.println("Reached");
                return 0;
            }

            System.out.println(l);
            Db db=new Db();
            db.selectTable("CASES");
            db.setCond(cases.ID, r.getParameter("id"));
            db.update(l);
            return 1;
        }
        catch(Exception e)
        {
             System.out.println("Exception");
            return 0;
        }
    }

    public void getCaseDetails(String compno)
    {
        try
        {
        Db db=new Db();
        Db db2=new Db();
        db.selectTable("CASES");
        db.setCond(cases.ID, compno);
        caseno=db.select(cases.ID).get(0);
        filedon=db.select(cases.FILED_TIME).get(0);
        createdon=db.select(cases.ACCEPTED_TIME).get(0);
        db2.selectTable("USERS");
        db2.setCond(users.ID, db.select(cases.CITIZEN).get(0));
        registeredby=db2.select(users.OFFICER_NAME).get(0);
        db2.selectTable("USERS");
        db2.setCond(users.ID, db.select(cases.OFFICER_IN_CHARGE).get(0));
        redirectedfrom=db2.select(users.OFFICER_NAME).get(0);
        caseprog=db.select(cases.PROGRESS).get(0);
        text=db.select(cases.TEXT).get(0);
        weight=db.select(cases.WEIGHTAGE).get(0);
        }
        catch(IndexOutOfBoundsException e)
        {
        
        }
    }

    public void getMessages(String id,String sortby,int type)
    {
        cas=new MsgDetails("cases");
        Db db;
        db=new Db();
       db.selectTable("CASES");
       String cond=" WHERE "+cases.OFFICER_IN_CHARGE.str+"="+id;

       cas.title=db.selectCond(cases.SUBJECT,cond);
       cas.msg=db.selectCond(cases.TEXT, cond);
       cas.from=db.selectCond(cases.CITIZEN, cond);
       cas.to=db.selectCond(cases.OFFICER_IN_CHARGE, cond);
       cas.msgid=db.selectCond(cases.ID, cond);
       cas.status=db.selectCond(cases.PROGRESS, cond);

    }

    public String prepCase(String id,String sortby,int type,String box,String p)
    {
                int page=Integer.parseInt(p);
                getMessages(id,sortby,type);
                done=1;
                return prepCases(id,sortby,type,cas,page);
    }

     public String prepCases(String id,String sortby,int type,MsgDetails box,int page)
    {
           String ret="";
           int nopages=30;
           int limit=(box.title.size()>page*nopages+nopages)?(page*nopages+nopages):(box.title.size());
           ret+="<div style=\'text-align: right; background:#333;\'><div style=\'float:left;\'>&nbsp;&nbsp;&nbsp;<a href=\"#\">"+box.box.toUpperCase()+"</a></div>";
           if(page>=1)
            ret+="<a href=\"javascript:prevnext(\'"+box.box+"\',"+(page-1)+")\">&lt;&lt;Newer</a>&nbsp;&nbsp;";
           if(limit<box.title.size())
               ret+="<a href=\"javascript:prevnext(\'"+box.box+"\',"+(page+1)+")\">Older&gt;&gt;</a>";
           else ret+="&nbsp;&nbsp;&nbsp;&nbsp;&nbs;p&nbsp;";
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
               ret+="<td onclick=\"javascript:openmsg("+box.msgid.get(i)+",\'"+box.box+"\')\" style=\"text-align:left; width:100px;\"><b>"+t+"</b></td>";
               ret+="<td onclick=\"javascript:openmsg("+box.msgid.get(i)+",\'"+box.box+"\')\" style=\"text-align:left; width: 100px;\">";
               if(box.box.equals("inbox")||box.box.equals("trash"))
                    ret+="From:"+box.from.get(i);
               else ret+="To:"+box.to.get(i);
               ret+="</td><td onclick=\"javascript:openmsg("+box.msgid.get(i)+")\" style=\"text-align:right;\">"+m+"</td>";
               ret+="</tr></table>";
               ret+="</tr>";
           }
               ret+="</table>";

        return ret;
    }

     public String openCase(String id,String number,int type,String box)
    {
            getMessages(id,"Time",type);
            done=1;
        if(box.equals("petitions"))
            return openCases(id,number,type,cas);
        return " ";
    }
    public String openCases(String id,String number,int type,MsgDetails box)
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

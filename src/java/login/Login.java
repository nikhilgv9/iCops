/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package login;
import database.*;
import java.util.*;
import tools.*;
import javax.servlet.http.*;
import communication.ChatDetails;

/**
 *
 * @author Nikhil
 */
public class Login {
    HttpSession ses;
    public String imgurl="";
    public String heading="";
    public String welcome="";
    public String nav[][]={};
    public String hie[][]={};
    public String usrer[][]={};
    public int type=0;
    public String username="";

    public static List<ChatDetails> chats=new ArrayList<ChatDetails>();
    public Set<String> chattingwith=new TreeSet<String>();
    
    public void init(HttpSession r)
    {
        ses=r;
    }
    public int loghimin(String usr,String pass)
    {
        Db db=new Db();
        db.selectTable("USERS");
        db.setCond(users.OFFICER_NAME, usr);
        List<String> l=db.select(users.PASSWORD);
        List<String> id=db.select(users.ID);
        List<String> type=db.select(users.TYPE);
        List<String> img=db.select(users.IMGURL);
        //System.out.println(id);
        if(l==null)
        {
            db.disconnect();
            return 0;
        }
            if(l.contains(pass))
            {
            ses.setAttribute("sesobj", new Session(usr));
            db.selectTable("LOG");
            l.clear();
            l.add(usr);
            l.add(id.get(0));
            l.add(Time.getTimeStamp());
            db.insert(l);
            if(type.contains("1"))
            {
                username=usr;
                ses.setAttribute("name", "admin");
                ses.setAttribute("id",id.get(0) );
                ses.setAttribute("img", img.get(0));
                heading="Adminisrator";
                welcome="Welcome Adminisrator";
                String[][] nav={{"Home","home-updates.jsp"},
                {"Hierarchy","hierarchy-create.jsp"},
                {"Posts","posts-create.jsp"},
                {"Users","users.jsp"},
                {"Complaints","complaints.jsp"},
                {"Messages","messages.jsp"}};
                this.nav=nav.clone();
                String hie[][]={{"Create","hierarchy-create.jsp"},
                {"Edit","hierarchy-edit.jsp"},
                {"Delete","hierarchy-delete.jsp"},
                {"View","hierarchy-view.jsp"}};
                this.hie=hie.clone();
                String usrer[][]={{"Officers","users.jsp"},
                {"UserKits","userkits.jsp"}};
                this.usrer=usrer.clone();
            }
            else if(type.contains("2"))
            {
                username=usr;
                ses.setAttribute("name","officer");
                ses.setAttribute("id",id.get(0));
                ses.setAttribute("img", img.get(0));
                heading="Officer";
                welcome="Welcome Officer";
                String[][] nav={{"Home","home-updates.jsp"},
                {"Hierarchy","hierarchy-view.jsp"},
                {"Users","users.jsp"},
                {"Complaints","complaints.jsp"},
                {"Messages","messages.jsp"}};
                this.nav=nav.clone();
                String hie[][]={
                {"View","hierarchy-view.jsp"}};
                this.hie=hie.clone();
                String usrer[][]={{"Officers","users.jsp"}};
                this.usrer=usrer.clone();
            }
            else if(type.contains("3"))
            {
                username=usr;
                ses.setAttribute("name","station");
                ses.setAttribute("id",id.get(0));
                ses.setAttribute("img", img.get(0));
                heading="Police Station";
                welcome="Welcome Inspector";
                String[][] nav={{"Home","home-updates.jsp"},
                {"Hierarchy","hierarchy-view.jsp"},
                {"Users","users.jsp"},
                {"Complaints","complaints.jsp"},
                {"Messages","messages.jsp"}};
                this.nav=nav.clone();
                String hie[][]={
                {"View","hierarchy-view.jsp"}};
                this.hie=hie.clone();
                String usrer[][]={{"Officers","users.jsp"},
                {"ActivateKits","activatekits.jsp"}};
                this.usrer=usrer.clone();
            }
            else if(type.contains("4"))
            {
                username=usr;
                ses.setAttribute("name","citizen");
                ses.setAttribute("id",id.get(0));
                ses.setAttribute("img", img.get(0));
                heading="Citizen";
                welcome="Welcome Citizen";
                String[][] nav={{"Home","home-updates.jsp"},
                {"Hierarchy","hierarchy-view.jsp"},
                {"Users","users.jsp"},
                {"Complaints","givepet.jsp"},
                {"Messages","messages.jsp"}};
                this.nav=nav.clone();
                String hie[][]={
                {"View","hierarchy-view.jsp"}};
                this.hie=hie.clone();
            }
            else if(type.contains("5"))
            {
                username=usr;
                ses.setAttribute("name","guest");
                ses.setAttribute("id",id.get(0));
                ses.setAttribute("img", img.get(0));
                heading="Guest";
                welcome="Welcome Guest";
                String[][] nav={{"Home","home-updates.jsp"},
                {"Hierarchy","hierarchy-view.jsp"},
                {"Users","users.jsp"},
                {"Complaints","givepet.jsp"},
                {"Messages","messages.jsp"}};
                this.nav=nav.clone();
                String hie[][]={
                {"View","hierarchy-view.jsp"}};
                this.hie=hie.clone();
            }
            db.disconnect();
            return 1;
            }
        db.disconnect();
        return 0;
    }
    public int checkSession()
    {
        if(ses==null)
            return -1;
        String name=(String) ses.getAttribute("name");
        if(name==null) return 0;
        imgurl=(String)ses.getAttribute("img");
        if(name.equals("admin"))
        {
            type=1;
        }
        else if(name.equals("officer"))
        {
            type=2;
        }
        else if(name.equals("station"))
        {
            type=3;
        }
        else if(name.equals("citizen"))
        {
            type=4;
        }
        else if(name.equals("guest"))
        {
            type=5;
        }
        else
        {
            type=0;
        }
        return type;
    }
    public String getId()
    {
        return (String)ses.getAttribute("id");
    }
  
    public void logout()
    {
        Db db=new Db();
        db.selectTable("LOG");
        db.setCond(log.USER_NAME, username);
        db.delete();
        db.disconnect();
        ses.removeAttribute("name");
        ses.removeAttribute("sesobj");
    }

    public void addChat(String msg, String to)
    {
        ChatDetails c=new ChatDetails(msg, username, to);
        chats.add(c);
    }

    public String getChat(String to)
    {
        String ret="";
        System.out.println(username+":"+to);
        for(int i=0;i<chats.size();i++)
        {
            if(chats.get(i).from.equals(to)&&chats.get(i).to.equals(username))
            {
                ret+="<b>"+chats.get(i).from+"</b>:"+chats.get(i).msg+"<br/>";
            }
            else if(chats.get(i).from.equals(username)&&chats.get(i).to.equals(to))
            {
                ret+="<b>"+chats.get(i).from+"</b>:"+chats.get(i).msg+"<br/>";
            }
        }
        return ret;
    }
    public String checkNewChat()
    {
        for(int i=0;i<chats.size();i++)
        {
            String n=null;
            if(chats.get(i).to.equals(username))
            {
                System.out.print("HERE:"+username);
                n=chats.get(i).from;
            }
            else if(chats.get(i).from.equals(username))
            {
                n=chats.get(i).to;
            }
            if(chattingwith.contains(n))
                continue;
            else
                return n;
        }
        return null;
    }
    public void startChat(String s)
    {
        chattingwith.add(s);
    }

    public void stopChat(String s)
    {

        for(int i=0;i<chats.size();i++)
        {
            if(chats.get(i).from.equals(s)||chats.get(i).to.equals(s))
            {
                chats.remove(i);
                i--;
            }
        }
        chattingwith.remove(s);
    }

    public List<String> getOnlineList()
    {
        Db db=new Db();
        db.selectTable("LOG");
        List<String> l=db.select(log.USER_NAME);
        return l;
    }

    public static void main(String[] args)
    {
        String[][] nav={{"Home","home-updates.jsp"},
                {"Hierarchy","hierarchy-create.jsp"},
                {"Posts","posts-create.jsp"},
                {"Users","users.jsp"},
                {"Complaints","complaints.jsp"},
                {"Messages","messages.jsp"}};
        System.out.println(nav.length);

    }

}





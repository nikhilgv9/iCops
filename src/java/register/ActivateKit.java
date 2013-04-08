/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package register;

import database.*;
import java.util.*;
import communication.Smtp;
import javax.servlet.http.*;
import tools.*;

/**
 *
 * @author Nikhil
 */
public class ActivateKit {

    public int checkAvail(String usr)
    {
        if(usr.equals("")||usr.length()<6)
            return 2;
        Db db=new Db();
        db.selectTable("USERS");
        db.setCond(users.OFFICER_NAME, usr);
        List<String> l=db.select(users.ID);
        if(l.isEmpty())
            return 1;
        else return 2;
 
    }

    public int activate(HttpServletRequest r)
    {
       
        Db db=new Db();
        String kit=r.getParameter("kit");
        if(kit==null||kit.equals("")) return 0;
        db.selectTable("USERS");
        db.setCond(users.ID, (String)r.getSession().getAttribute("id"));
        String level=db.select(users.LEVEL_ID).get(0);
        db.selectTable("USERKITS");
        db.setCond(userkits.ID, kit);
        String pass=db.select(userkits.PASSWORD).get(0);
        String email=r.getParameter("email");
        if(email==null) return 0;
        db.selectTable("USERS");
        List<String> l=new ArrayList<String>();
        l.add(level);
        l.add(r.getParameter("usrname"));
        l.add("");
        l.add(pass);
        l.add("0");
        l.add("stars/citizen.jpg");
        l.add("4");
        if(db.insert(l)==1) 
        {
            db.selectTable("USERS");
            db.setCond(users.OFFICER_NAME,r.getParameter("usrname"));
            l.clear();
            l.add(db.select(users.ID).get(0));
            l.add("4");
            l.add(r.getParameter("faname"));l.add(r.getParameter("lname"));l.add(r.getParameter("address"));
            l.add(Time.getTimeStamp());l.add(email);
            l.add(r.getParameter("occupation"));l.add(r.getParameter("phno"));l.add("");l.add("");l.add("");
            db.selectTable("PROFILE");
            db.insert(l);
            String msg="Your Account has been activated. Your User Acccount Details are\n\n\nUSER NAME:"
                    +r.getParameter("usrname")+"\nPASSWORD:"+pass+"\n\n\nLogin to access your account.";
            l.clear();
            l.add(null);
            l.add(null);
            l.add("1");
            l.add(null);
            db.selectTable("USERKITS");
            db.setCond(userkits.ID, kit);
            db.update(l);
            Smtp mail=new Smtp();
            mail.sendmail("ICOPS", msg, email);
            return 1;
        }
        return 0;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package manage;
import database.*;
import javax.servlet.http.*;
import java.util.*;
import tools.*;
/**
 *
 * @author Nikhil
 */
public class ManageHi {
    Db db=new Db();
    List<String> l;
    List<String> m;
    List<String> queries=new ArrayList<String>();
    String type="";
    
    public int writeto()
    {
        db.selectTable("HIERARCHY");
        queries.add(db.makequery(l));
        int r=db.batchExec(queries);
        if(r!=0 && type.equals("Police Station"))
        {
            db.selectTable("USERS");
            db.setCond(users.OFFICER_NAME, m.get(1));
            List<String> p=new ArrayList<String>();
            p.add(db.select(users.ID).get(0));
            p.add(db.select(users.TYPE).get(0));
            p.add("");p.add("");p.add("");p.add(Time.getTimeStamp());p.add("");
            p.add("");p.add("");p.add("");p.add("");p.add("");p.add("");
            db.selectTable("PROFILE");
            db.insert(p);
        }
        return r;
     }
    public int checkpost(HttpServletRequest r)
    {
            l=new ArrayList();
            m=new ArrayList();
            l.add(r.getParameter("lname"));
            l.add(r.getParameter("lparent"));
            l.add(r.getParameter("lcode"));
            l.add(r.getParameter("ldescri"));
            if(l.contains(null))
            {
                return 0;
            }
            else if(l.contains(""))
            {
                return 2;
            }
            else
            {
                Integer temp=Integer.parseInt(r.getParameter("ldepth"))+1;
                l.add(3,temp.toString());
                type=r.getParameter("ltype");
                if(type.equals("Police Station"))
                {
                    RandomString ran=new RandomString(10);
                    m.add(r.getParameter("lname"));
                    m.add(r.getParameter("lname"));
                    m.add(r.getParameter("lcode"));
                    m.add(ran.nextString());
                    m.add("0");
                    m.add("stars/station.png");
                    m.add("3");
                    db.selectTable("USERS");
                    queries.add(db.makequery(m));

                    l.add("1");
                }
                else
                {
                    l.add("0");
                }
                return 1;
            }
    }

    public static void main(String args[]) {

    }
}



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package posts;
import database.*;
import javax.servlet.http.*;
import java.util.*;
import tools.*;
/**
 *
 * @author Nikhil
 */
public class ManagePo {
    
    List<String> l;
    List<String> queries=new ArrayList<String>();

     public int writeto()
    {
        Db db=new Db();
        db.selectTable("USERS");
        queries.add(db.makequery(l));
        db.batchExec(queries);
        db.setCond(users.OFFICER_NAME, l.get(1));
        List<String> p=new ArrayList<String>();
        p.add(db.select(users.ID).get(0));
        p.add(db.select(users.TYPE).get(0));
        p.add("");p.add("");p.add("");p.add(Time.getTimeStamp());p.add("");
        p.add("");p.add("");p.add("");p.add("");p.add("");p.add("");
        db.selectTable("PROFILE");
        db.insert(p);
        return 1;
    }
    public int checkpost(HttpServletRequest r)
    {
        try
        {
            l=new ArrayList<String>();
            HttpSession ses=r.getSession();
            RandomString random=new RandomString(10);
            l.add(r.getParameter("lparent"));
            Db db=new Db();
            db.selectTable("HIERARCHY");
            db.setCond(hierarchy.LEVEL_NAME, r.getParameter("lparent"));
            l.add(db.select(hierarchy.LEVEL_CODE).get(0)+"-"+r.getParameter("oname"));
            l.add(db.select(hierarchy.LEVEL_CODE).get(0)+"-"+r.getParameter("ocode"));
            l.add(random.nextString());
            l.add("1");
            l.add(r.getParameter("opropic"));
            l.add("2");
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
                /*Db db=new Db();
                db.selectTable("HIERARCHY");
                List<String> l2=db.select(hierarchy.LEVEL_CODE, hierarchy.LEVEL_NAME, l.get(0));
                l.remove(0);
                l.add(0,l2.get(0));*/
                System.out.println(l);
                return 1;
            }
        }
        catch(Exception e)
        {
            return 0;
        }
    }

    public static void main(String args[]) {

    }

}

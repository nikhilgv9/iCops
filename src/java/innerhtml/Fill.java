/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package innerhtml;
import database.*;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 *
 * @author Nikhil
 */
public class Fill {
    
    public String get(HttpServletRequest r)
    {
        Db db=new Db();
        String ret="";
        String s=r.getParameter("depth");
        if(s!=null)
        {
            db.selectTable("HIERARCHY");
            List<String> l=db.select(hierarchy.LEVEL_NAME,hierarchy.LEVEL_DEPTH,s);
            for(String i:l)
                ret+=i+"/";
            db.disconnect();
        }
        else if((s=r.getParameter("lname"))!=null)
        {
            if(s.equals("ALL"))
            {
                db.selectTable("USERS");
                List<String> l=db.select(users.OFFICER_NAME);
                for(String i:l)
                {
                    if(!i.equals("admin"))
                        ret+=i+"/";
                }
                db.disconnect();
                return ret;
            }
            db.selectTable("USERS");
            db.setCond(users.LEVEL_ID,s);
            List<String> l=db.select(users.OFFICER_NAME);
            for(String i:l)
                ret+=i+"/";
            db.disconnect();
        }
        else if((s=r.getParameter("station"))!=null)
        {
            db.selectTable("USERKITS");
            db.setCond(userkits.DELIVEREDTO,s);
            List<String> l=db.select(userkits.ID);
            ret+=l.size();
            db.disconnect();
        }
        return ret;
    }

    public static void main(String args[]) {

    }

}

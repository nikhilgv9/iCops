/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package manage;
import database.*;
import javax.servlet.http.*;
import java.util.*;

/**
 *
 * @author Nikhil
 */
public class DeleteHi extends EditHi {

    @Override
    public int checkpost(HttpServletRequest r)
    {
        if(r.getParameter("lname")==null)
        {
            return 0;
        }
        else
        {
            db=new Db();
            db.selectTable("HIERARCHY");
            int ret=delete(r.getParameter("lname"));
            db.disconnect();
            return ret;
        }

    }

    public int delete(String value)
    {
        db.selectTable("HIERARCHY");
        db.setCond(hierarchy.PARENT_LEVEL, value);
        l=db.select(hierarchy.LEVEL_NAME);
        for(String i:l)
            delete(i);
        db.selectTable("HIERARCHY");
        db.setCond(hierarchy.LEVEL_NAME, value);
        return db.delete();
    }

    public static void main(String args[]) {

    }

}

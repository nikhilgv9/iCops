/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package posts;

import javax.servlet.http.HttpServletRequest;
import database.*;

/**
 *
 * @author Nikhil
 */
public class DeletePo extends EditPo{
    @Override
    public int checkpost(HttpServletRequest r)
    {
        Db db=new Db();
        if(r.getParameter("oname")==null)
        {
            return 0;
        }
        else
        {
            return delete(r.getParameter("oname"));
        }

    }

    public int delete(String value)
    {
        Db db=new Db();
        db.selectTable("USERS");
        db.setCond(users.OFFICER_NAME, value);
        String id=db.select(users.ID).get(0);
        db.delete();
        db.selectTable("PROFILE");
        db.setCond(profile.ID, id);
        db.delete();
        return 1;
    }

    public static void main(String args[]) {

    }

}

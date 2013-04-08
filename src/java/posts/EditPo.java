/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package posts;
import database.*;
import javax.servlet.http.*;

/**
 *
 * @author Nikhil
 */
public class EditPo extends ManagePo
{
    HttpServletRequest req;

    public int edit()
    {
       Db db=new Db();
       db.selectTable("USERS");
       db.setCond(users.OFFICER_NAME,req.getParameter("ooldname"));
       queries.add(db.makeUpdatequery(l));
       return db.batchExec(queries);
    }

    @Override
    public int checkpost(HttpServletRequest r)
    {
        req=r;
        if(r.getParameter("ooldname")==null||super.checkpost(r)==0)
        {
            return 0;
        }
        else if(super.checkpost(r)==2)
        {
            return 2;
        }
       else
        {
            return super.checkpost(r);
        }

    }

}

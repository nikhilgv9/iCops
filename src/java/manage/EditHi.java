package manage;

import database.*;
import javax.servlet.http.*;
import java.util.*;

public class EditHi extends ManageHi
{
    HttpServletRequest req;

    public int edit()
    {
       db.selectTable("HIERARCHY");
       db.setCond(hierarchy.LEVEL_NAME,req.getParameter("loname"));
       queries.add(db.makeUpdatequery(l));
       return db.batchExec(queries);
    }

    @Override
    public int checkpost(HttpServletRequest r)
    {
        req=r;
        if(r.getParameter("loname")==null||super.checkpost(r)==0)
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

    public static void main(String args[]) {

    }
}
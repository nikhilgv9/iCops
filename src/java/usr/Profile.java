/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package usr;
import database.*;
import javax.servlet.http.*;
import java.util.*;
import tools.*;
/**
 *
 * @author Nikhil
 */
public class Profile extends ProfileInfo {
    
    HttpServletRequest r;
    public String viewertype="";

    public Profile(HttpServletRequest r)
    {
        this.r=r;
        HttpSession ses=r.getSession();
        viewertype=(String)ses.getAttribute("name");
    }

    public void getProfileInfo()
    {
        try
        {
        String id=r.getParameter("id");
        if(id==null||id.equals("null"))
        {
            HttpSession ses=r.getSession();
            id=(String)ses.getAttribute("id");
        }
        System.out.println(id);
        Db db=new Db();
        db.selectTable("USERS");
        db.setCond(users.ID, id);
        name=db.select(users.OFFICER_NAME).get(0);
        String s=db.select(users.IMGURL).get(0);
        String a[]=s.split("/");
        imgurl=a[a.length-1];
        db=new Db();
        db.selectTable("PROFILE");
        db.setCond(profile.ID, id);
        address=db.select(profile.ADDRESSBOUNDARIES).get(0);
        contactno=db.select(profile.CONTACTNO).get(0);
        designation=db.select(profile.OCCUPATION).get(0);
        email=db.select(profile.EMAIL).get(0);
        fname=db.select(profile.FIRSTNAME).get(0);
        highway=db.select(profile.HIGHWAYINCLUDE).get(0);
        hospitals=db.select(profile.HOSPITALS).get(0);
        this.id=db.select(profile.ID).get(0);
        lname=db.select(profile.LASTNAME).get(0);
        staffinfo=db.select(profile.STAFFINFO).get(0);
        time=db.select(profile.DATEOFEDITING).get(0);
        type=db.select(profile.TYPE).get(0);
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("Indexout of bound");
        }
    }
    public void setProfileInfo()
    {
        HttpSession ses=r.getSession();
        String id=(String)ses.getAttribute("id");
        Db db=new Db();
        db.selectTable("PROFILE");
        List<String> l=new ArrayList<String>();
        l.add(null);
        l.add(null);
        l.add(r.getParameter("fname"));
        l.add(r.getParameter("lname"));
        l.add(r.getParameter("address"));
        l.add(Time.getTimeStamp());
        l.add(r.getParameter("email"));
        l.add(r.getParameter("designation"));
        l.add(r.getParameter("phno"));
        l.add(r.getParameter("staffinfo"));
        l.add(r.getParameter("hospitals"));
        l.add(r.getParameter("highways"));     
        db.setCond(profile.ID, id);
        db.update(l);
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package usr;
import javax.servlet.http.*;
import database.*;
import tools.Time;
import java.util.*;
/**
 *
 * @author Nikhil
 */
public class Citizen extends dbb
{
    String uid;
    public Citizen()
    {
        super();
    }
    public void init(HttpSession s)
    {
        uid=s.getAttribute("id").toString();
    }
    public void givePetition(String to,String sub,String pet)
    {
        Db db=new Db();
        List<String> l=new ArrayList<String>();
        l.add(sub);l.add(Time.getTimeStamp());
        l.add(pet);l.add(to);l.add(to);
        l.add(1+"");
        l.add(0+"");
        l.add(uid);
        db.selectTable("PETITIONS");
        db.insert(l);
    }

public void viewPetitions()
{
    this.SelectQuery("SELECT * FROM SQLJ.PETITIONS WHERE CITIZEN_ID="+uid);
}

public static void main(String[] args)
{
    
}
}

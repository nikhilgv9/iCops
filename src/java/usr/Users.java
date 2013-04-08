/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package usr;
import database.*;
import java.util.*;
import tools.*;

/**
 *
 * @author Nikhil
 */
public class Users {

    public List<String> usrnames=new ArrayList<String>();
    public List<String> imageurl=new ArrayList<String>();
    public List<String> suspended=new ArrayList<String>();
    public List<String> ofid=new ArrayList<String>();

    public void getUserInfo(String id,int type)
    {
        Db db=new Db();
        db.selectTable("USERS");
        db.setCond(users.ID, id);
        String level=db.select(users.LEVEL_ID).get(0);
        List<String> l=new ArrayList<String>();
        db.selectTable("HIERARCHY");
        db.setCond(hierarchy.PARENT_LEVEL, level);
        l=db.select(hierarchy.LEVEL_NAME);
        for(String i:l)
        {
            db.selectTable("USERS");
            db.setCond(users.LEVEL_ID, i);
            ofid=db.select(users.ID);
            usrnames=db.select(users.OFFICER_NAME);
            imageurl=db.select(users.IMGURL);
            suspended=db.select(users.SUSPENDED);
        }
        System.out.println(usrnames);
    }

    public void suspend(String id,String value)
    {
        Db db=new Db();
        db.selectTable("USERS");
        db.setCond(users.ID, id);
        List<String> l=new ArrayList<String>();
        l.add(null);l.add(null);l.add(null);l.add(null);
        l.add(value);
        l.add(null);l.add(null);
        db.update(l);
        db.disconnect();
    }
    public void sendUserkit(String id,String value)
    {
        RandomString rs=new RandomString(8);
        List<String> l=new ArrayList<String>();
        int n=Integer.parseInt(value);
        Db db=new Db();
        db.selectTable("USERKITS");
        for(int i=0;i<n;i++)
        {
            l.clear();
            l.add(rs.nextString());
            l.add(rs.nextString());
            l.add("0");
            l.add(id);
            db.insert(l);
        }

    }

}

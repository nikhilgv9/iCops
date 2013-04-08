/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package communication;
import java.util.*;

/**
 *
 * @author Nikhil
 */
public class MsgDetails {
    public List<String> msg=new ArrayList<String>();
    public List<String> from=new ArrayList<String>();
    public List<String> to=new ArrayList<String>();
    public List<String> title=new ArrayList<String>();
    public List<String> msgid=new ArrayList<String>();
    public List<String> status=new ArrayList<String>();
    public List<String> stationid=new ArrayList<String>();
    public List<String> officerid=new ArrayList<String>();
    public List<String> time=new ArrayList<String>();
    public String box;
    public String type;
    MsgDetails(String s)
    {
        box=s;
    }
    public List<String> formatTime()
    {
        List<String> l=new ArrayList<String>();
        for(String i:time)
        {
            if(i!=null)
                l.add(i.split(" ")[0]);
            else
                l.add(i);
        }
        return l;

    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package communication;
import tools.*;

/**
 *
 * @author Nikhil
 */
public class ChatDetails {
    public String from;
    public String to;
    public String msg;
    public String time;
    public ChatDetails(String m,String fr,String t)
    {
        msg=m;
        from=fr;
        to=t;
        time=Time.getTimeStamp();
    }
}

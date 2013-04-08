/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package usr;
import java.util.*;
import login.Login;
import javax.servlet.http.*;
/**
 *
 * @author Nikhil
 */
public class User
{
    List<String> html;
    User usr;
    public void init(HttpServletRequest r)
    {
        Login lg=new Login();
        lg.init(r.getSession());
        int i=lg.checkSession();
        if(i==-1) usr=null;
        else if(i==0) usr=null;
        else if(i==1) usr=new Admin();
        else if(i==2) usr=new Officer();
        else if(i==3) usr=new Station();
        else if(i==5) usr=new Guest();
    }
    
}
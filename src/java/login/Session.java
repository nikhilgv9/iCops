/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package login;
import javax.servlet.http.*;
import database.*;

/**
 *
 * @author Nikhil
 */


public class Session implements HttpSessionListener,HttpSessionBindingListener{
    String usrname;
    public Session(String usr)
    {
        usrname=usr;
    }

    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println(usrname+" Logged in");
    }

    public void valueUnbound(HttpSessionBindingEvent event) {

        Db db=new Db();
        db.selectTable("LOG");
        db.setCond(log.USER_NAME, usrname);
        db.delete();
        db.disconnect();
        System.out.println(usrname+" Logged out");
    }

    public void sessionCreated(HttpSessionEvent se) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println(usrname+" Logged out");
    }


    public static void main(String args[]) {

    }
}

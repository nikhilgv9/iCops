/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tools;
import java.util.*;
import java.sql.*;
/**
 *
 * @author Nikhil
 */
public class Time {
    public static String getDate()
    {
        Calendar c=new GregorianCalendar();
        Timestamp t=new Timestamp(c.getTimeInMillis());
        String s=t.toString();
        String a[]=s.split(" ");
        return a[0];
    }
    public static String getTime()
    {
        Calendar c=new GregorianCalendar();
        Timestamp t=new Timestamp(c.getTimeInMillis());
        String s=t.toString();
        String a[]=s.split(" ");
        return a[1];
    }
    public static String getTimeStamp()
    {
        Calendar c=new GregorianCalendar();
        Timestamp t=new Timestamp(c.getTimeInMillis());
        return t.toString();
    }


    public static void main(String args[]) {

    }
}

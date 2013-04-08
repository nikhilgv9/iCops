package database;
import java.sql.*;
import java.util.*;
/**
 *
 * @author appu
 */
public class dbb {
    private Connection conn;
    private Statement stt;
    private ResultSet rs;
   public dbb()
    {
        try
        {
        Class.forName("COM.ibm.db2.jdbc.app.DB2Driver");
            conn = DriverManager.getConnection("jdbc:db2:ICOP",null, null);
            stt = conn.createStatement();
        }
        catch(Exception e)
        {
            System.err.print(e.getMessage());
        }
    }
    public byte SelectQuery(String que)
    {
        try{
        rs=stt.executeQuery(que);
        return 1;
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
            return 0;
        }
        }
    public boolean query(String qry)
    {
        try
        {
        return stt.execute(qry);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
            return false;
        }
        }
    public boolean next()
    {
        boolean t=false;
        try
        {
        t= rs.next();
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        return t;
    }

    public String get(String field)
    {
        String t="";
        try
        {
            t= rs.getString(field);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        return t;
    }

    }





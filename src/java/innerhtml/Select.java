/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package innerhtml;
import java.util.*;
import database.*;
/**
 *
 * @author Nikhil
 */
public class Select {
    
    String table="";


    public String property(String table,table col, List<table> field, List<String> value)
    {
        Db db=new Db();
        String inhtml="";
        db.selectTable(table);
        for(int i=0;i<field.size();i++)
            db.setCond(field.get(i), value.get(i));
        List<String> l=db.select(col);
        List<String> id=db.select(users.ID);
        for(int i=0;i<l.size();i++)
        {
            inhtml+="<option value=\""+id.get(i)+"\">"+l.get(i)+"</option>";
        }
        return inhtml;
    }



    public String property(String table,table col, table field, String value)
    {
        Db db=new Db();
        String inhtml="";
        db.selectTable(table);
        db.setCond(field, value);
        List<String> l=db.select(col);
        List<String> id=db.select(users.ID);
        for(int i=0;i<l.size();i++)
        {
            inhtml+="<option value=\""+id.get(i)+"\">"+l.get(i)+"</option>";
        }
        return inhtml;
    }
    
    public String property(String table,table col)
    {
        Db db=new Db();
        String inhtml="";
        db.selectTable(table);
        //System.out.println(col);
        Set<String> l=db.selectSet(col);

        for(String i: l)
        {
            inhtml+="<option value=\""+i+"\">"+i+"</option>";
        }
        return inhtml;
    }
    public String propertyjs(String table,table col)
    {
        Db db=new Db();
        String innerhtml="";
        db.selectTable(table);
        List<String> l=db.select(col);
        int n=0;
        for(String i:l)
        {
               innerhtml+="document.getElementById(\'Select2\').options["+n+"] = new Option(\'"+i+"\', \'"+i+"\');";
               n++;
        }
        return innerhtml;
    }
    public String propertySkip(String table,table col,String skip)
    {
        Db db=new Db();
        String inhtml="";
        db.selectTable(table);
        Set<String> l=db.selectSet(col);
        for(String i: l)
        {
            if(!i.equals(skip))
            {
                inhtml+="<option value=\""+i+"\">"+i+"</option>";
            }
        }
        return inhtml;
    }

    public static void main(String args[]) {

    }

}

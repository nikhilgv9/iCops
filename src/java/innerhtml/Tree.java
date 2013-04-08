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
public class Tree
{
    static String js="";
    Db db;
    public String name,code,officers;
    
    public void generate(String level,String var)
    {
        db.selectTable("HIERARCHY");
        db.setCond(hierarchy.LEVEL_NAME, level);
        String id=db.select(hierarchy.ID).get(0);
        String ret= var+"=gFld(\'<b><a onmouseover=\"loaddetails("+id+")\">"+level+ "</a></b>\',\'"+"\');" ;
        ret+=var+".addChildren([";
        db.selectTable("HIERARCHY");
        db.setCond(hierarchy.PARENT_LEVEL, level);
        List<String> l=db.select(hierarchy.LEVEL_NAME);
        int n=l.size()-1;
        int j=0;
        for(String i:l)
        {
            generate(i,var+(j+1));
            ret+=var+(j+1);
            if(j!=n)
            {
                ret+=",";
            }
            j++;
        }
        ret+="]);\n\r";
        js+=ret;

    }
    public String getJs()
    {
        db=new Db();
        js="";
        generate("STATE","aux");
        db.disconnect();
        return js;
    }

    public void leveldetails(String id)
    {
        Db db=new Db();
        db.selectTable("HIERARCHY");
        db.setCond(hierarchy.ID, id);
        name=db.select(hierarchy.LEVEL_NAME).get(0);
        code=db.select(hierarchy.LEVEL_CODE).get(0);
        db.selectTable("USERS");
        db.setCond(users.LEVEL_ID, name);
        db.setCond(users.TYPE, "2");
        db.setOrCond(users.LEVEL_ID, name);
        db.setCond(users.TYPE, "3");
        List<String> l=db.select(users.OFFICER_NAME);
        List<String> m=db.select(users.ID);
        officers="";
        for(int i=0;i<l.size();i++)
            officers+="<a href=\"javascript:redirectparent(\'"+"profile.jsp?id="+m.get(i)+"\')\">"+l.get(i)+"</a>"+". ";
        db.disconnect();
    }

    public static void main(String args[])
    {
        Tree t=new Tree();
        System.out.print(t.getJs());
    }

}

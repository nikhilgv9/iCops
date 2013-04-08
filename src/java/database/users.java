/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

/**
 *
 * @author Nikhil
 */
public enum users implements table
{
    ID(1,"ID"),LEVEL_ID(2,"LEVEL_ID"),OFFICER_NAME(3,"OFFICER_NAME"),
    OFFICER_CODE(4,"OFFICER_CODE"),PASSWORD(5,"PASSWORD"),
    SUSPENDED(6,"SUSPENDED"),IMGURL(7,"IMGURL"),TYPE(8,"TYPE");
    public int value;
    public String str;
    users(int i,String j)
    {
        value=i;
        str=j;
    }
    public int getvalue() {
        return value;
    }

    public String getString() {
        return str;
    }
}
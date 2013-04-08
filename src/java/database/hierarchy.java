/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

/**
 *
 * @author Nikhil
 */
public enum hierarchy implements table
{
    ID(1,"ID"),LEVEL_NAME(2,"LEVEL_NAME"),PARENT_LEVEL(3,"PARENT_LEVEL"),
    LEVEL_CODE(4,"LEVEL_CODE"),LEVEL_DEPTH(5,"LEVEL_DEPTH"),
    DESCRIPTION(6,"DESCRIPTION"),TYPE(7,"TYPE");
    public int value;
    public String str;
    hierarchy(int i,String j)
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
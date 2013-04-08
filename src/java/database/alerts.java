/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

/**
 *
 * @author Nikhil
 */
public enum alerts implements table
{
    ID(1,"ID"),
    TYPE(2,"TYPE"),
    TO(3,"TO"),
    TEXT(4,"TEXT"),
    TIME(5,"TIME"),
    FROM(6,"FROM"),
    TITLE(7,"TITLE");

    public int value;
    public String str;
    alerts(int i,String j)
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
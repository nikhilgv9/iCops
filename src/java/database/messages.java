/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

/**
 *
 * @author Nikhil
 */
public enum messages implements table
{
    ID(1,"ID"),MSG_FROM(2,"MSG_FROM"),MSG_TO(3,"MSG_TO"),
    MSG_TITLE(4,"MSG_TITLE"),MSG_DETAILS(5,"MSG_DETAILS"),
    STATUS(6,"STATUS"),TIME(7,"TIME");
    public int value;
    public String str;
    messages(int i,String j)
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
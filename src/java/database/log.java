/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

/**
 *
 * @author Nikhil
 */
public enum log implements table
{
    ID(1,"ID"),USER_NAME(2,"USER_NAME"),USER_ID(3,"USER_ID"),
    TIME(4,"TIME");
    public int value;
    public String str;
    log(int i,String j)
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
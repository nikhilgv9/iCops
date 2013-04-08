/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

/**
 *
 * @author Nikhil
 */
public enum userkits implements table
{
    ID(1,"ID"),KITCODE(2,"KITCODE"),PASSWORD(3,"PASSWORD"),
    STATUS(4,"STATUS"),DELIVEREDTO(5,"DELIVEREDTO");
    public int value;
    public String str;
    userkits(int i,String j)
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
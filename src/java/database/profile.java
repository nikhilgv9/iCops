/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

/**
 *
 * @author Nikhil
 */
public enum profile implements table
{
    ID(1,"ID"),
    TYPE(2,"TYPE"),
    FIRSTNAME(3,"FIRSTNAME"),
    LASTNAME(4,"LAST NAME"),
    ADDRESSBOUNDARIES(5,"ADDRESS/BOUNDARIES"),
    DATEOFEDITING(6,"DATE OF EDITING"),
    EMAIL(7,"EMAIL"),
    OCCUPATION(8,"OCCUPATION"),
    CONTACTNO(9,"CONTACTN"),
    STAFFINFO(10,"STAFFINFO"),
    HOSPITALS(11,"HOSPITALS"),
    HIGHWAYINCLUDE(12,"HIGHWAYINCLUDE");
    public int value;
    public String str;
    profile(int i,String j)
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
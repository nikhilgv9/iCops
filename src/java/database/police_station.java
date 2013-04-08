/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

/**
 *
 * @author Nikhil
 */
public enum police_station implements table
{
    ID(1,"ID"),PARENT_ID(2,"PARENT_ID"),STATION_NAME(3,"STATION_NAME"),
    STATION_CODE(4,"STATION_CODE"),PASSWORD(5,"PASSWORD"),
    SUSPENDED(6,"SUSPENDED");
    public int value;
    public String str;
    police_station(int i,String j)
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


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

/**
 *
 * @author Nikhil
 */
public enum informations implements table
{
    ID(1,"ID"),SUBJECT(2,"SUBJECT"),TIME(3,"TIME"),
    TEXT(4,"TEXT"),OFFICER_ID(5,"OFFICER_ID"),
    STATION_ID(6,"STATION_ID"),STATUS(7,"STATUS"),REDIRECTEDFROM(8,"REDIRECTEDFROM"),CITIZEN_ID(9,"CITIZEN_ID");
    public int value;
    public String str;
    informations(int i,String j)
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
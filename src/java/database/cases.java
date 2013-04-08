/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

/**
 *
 * @author Nikhil
 */
public enum cases implements table
{
    ID(1,"ID"),
    SUBJECT(2,"SUBJECT"),
    OFFICER_IN_CHARGE(3,"OFFICER_IN_CHARGE"),
    CITIZEN(4,"CITIZEN"),
    PROGRESS(5,"PROGRESS"),
    FILED_TIME(6,"FILED_TIME"),
    ACCEPTED_TIME(7,"ACCEPTED_TIME"),
    REDIRECTED_FROM(8,"REDIRECTED_FROM"),
    TEXT(9,"TEXT"),
    WEIGHTAGE(10,"WEIGHTAGE");
    public int value;
    public String str;
    cases(int i,String j)
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
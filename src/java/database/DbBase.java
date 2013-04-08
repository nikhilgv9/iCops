/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikhil
 */
public class DbBase {

    String url = "jdbc:db2:ICOPS";
    String uname = null, psswrd = null;
    ResultSetMetaData rm=null;
    String query;
    String order;
    String cond;
    String predicate, prevpredi;
    Connection conn;
    String table = "";
    List<String>[] result;
    Set<String>[] set;

    public DbBase() {
        try {
            Class.forName("COM.ibm.db2.jdbc.app.DB2Driver");
            conn = DriverManager.getConnection(url, uname, psswrd);
        } catch (SQLException e) {
            System.out.println("SQL Exception: ");
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void selectTable(String tab) {
        table = tab;
        rm = getmeta();
        order = "";
        cond = " ";
    }

    public void setOrderBy(table fld) {
        String field=fld.getString();
        order = "ORDER BY " + "\""+field+"\"";
    }

    public void setOrderByDesc(table fld) {
        String field=fld.getString();
        order = "ORDER BY " + "\""+field+"\" DESC";
    }

    public void setCond(table fld, String value) {
        int field=fld.getvalue();
        String s="null";
        if(value!=null)
            s=value.replaceAll("\'", "\'\'");
        try {
            if(cond.equals(" "))
                cond+="WHERE ";
            else
                cond+="AND ";
            cond += "\""+rm.getColumnName(field)+"\"" + "=";
            if (rm.getColumnType(field) == 12 || rm.getColumnType(field) == 93 || rm.getColumnType(field) == 2005) {
                cond += "\'";
                cond += s;
                cond += "\'";
            } else {
                cond += s;
            }
            cond += " ";
        } catch (SQLException ex) {
            Logger.getLogger(DbBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setOrCond(table fld, String value) {
        int field=fld.getvalue();
        String s="null";
        if(value!=null)
        s=value.replaceAll("\'", "\'\'");
        try {
            if(cond.equals(" "))
                cond+="WHERE ";
            else
                cond+="OR ";
            cond +="\""+ rm.getColumnName(field)+"\"" + "=";
            if (rm.getColumnType(field) == 12 || rm.getColumnType(field) == 93 || rm.getColumnType(field) == 2005) {
                cond += "\'";
                cond += s;
                cond += "\'";
            } else {
                cond += s;
            }
            cond += " ";
        } catch (SQLException ex) {
            Logger.getLogger(DbBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<String> select(table fld) {
        int field=fld.getvalue();
        predicate = cond + order;
        if (!predicate.equals(prevpredi)) {
            prevpredi = predicate;
            try {
                result = new ArrayList[20];
                set = new TreeSet[20];
                for (int i = 0; i < 20; i++) {
                    result[i] = new ArrayList<String>();
                    set[i] = new TreeSet<String>();
                }

                query = "SELECT * FROM SQLJ." + table  + predicate;
                ResultSet rs;
                Statement stmt;
                stmt = conn.createStatement();
                System.out.println(query);
                rs = stmt.executeQuery(query);

                String temp;
                while (rs.next()) {
                    for (int i = 1; i <= rm.getColumnCount(); i++) {
                        temp=rs.getString(i);
                        if(temp==null)
                            temp="";
                        set[i].add(temp);
                        result[i].add(temp);
                        
                    }
                }
                rs.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DbBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result[field];
    }

    public Set<String> selectSet(table fld) {
        int field=fld.getvalue();
        predicate = cond + order;
        if (!predicate.equals(prevpredi)) {
            select(fld);
        }
        return set[field];
    }

    public List<String> select(table field, table fld, String value) {
        setCond(fld, value);
        return select(field);
    }

    public Set<String> selectSet(table field, table fld, String value) {
        setCond(fld, value);
        return selectSet(field);
    }

    public List<String> selectOnId(Integer id, table field) {
        setCond(field, id.toString());
        return select(field);
    }

    public Set<String> selectOnIdSet(Integer id, table field) {
        setCond(field, id.toString());
        return selectSet(field);
    }

    public List<String> selectAll(table field) {
        return select(field);
    }

    public Set<String> selectAllSet(table field) {
        return selectSet(field);
    }

    public List<String> selectCond(table col, String cond) {
        this.cond = cond + " ";
        return select(col);
    }

    public Set<String> selectCondSet(table col, String cond) {
        this.cond = cond + " ";
        return selectSet(col);
    }

    public ResultSetMetaData getmeta() {
        try {
            Statement stmt;
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SQLJ." + table+" WHERE 1=2");
            ResultSetMetaData rm=rs.getMetaData();
            return rm;
        } catch (SQLException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int disconnect() {
        try {
            conn.close();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(DbBase.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public static void main(String args[]) {
        
    }
}

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
public class Db extends DbBase {

    public Db() {
        super();
    }

    public int insert(List<String> l) {
        makequery(l);
        try {
            Statement stmt = null;
            stmt = conn.createStatement();
            System.out.println(query);
            stmt.executeUpdate(query);
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int batchExec(List<String> l) {
            Statement stmt = null;
        try {
            stmt = conn.createStatement();
            conn.setAutoCommit(false);
            for (String i : l) {
                System.out.println(i);
                stmt.addBatch(i);
            }
            int[] res = stmt.executeBatch();
            conn.setAutoCommit(true);
            return 1;
        } catch (BatchUpdateException e) {
            System.out.print("Batch exception");
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        } catch (SQLException ex) {
            System.out.print("sql exception");
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
            try {
                stmt.close();
            } catch (SQLException ex1) {
                Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return 0;
        }
    }

    public String makequery(List<String> l) {
        try {
            query = "INSERT INTO SQLJ." + table + " (";
            ResultSetMetaData rm = getmeta();
            int n = rm.getColumnCount();
            int j=0;
            for (int i = 1; i <= n; i++) {
                try {
                    if (!rm.isAutoIncrement(i)) {
                        if (l.get(j) != null) {
                            query += "\""+rm.getColumnName(i)+"\"";
                            
                                query += ",";
                            
                        }
                        j++;
                   }
                } catch (SQLException ex) {
                    Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            query = query.substring(0, query.length() - 1);
            query += ") VALUES (";
            j = 0;
            for (int i = 1; i <= n; i++) {
                if (rm.isAutoIncrement(i)) {
                    continue;
                }
                if(l.get(j)==null)
                {
                    j++;
                    continue;
                }
                String s=null;
                if(l.get(j)!=null)
                    s=l.get(j).replaceAll("\'", "\'\'");
                if (rm.getColumnType(i) == 12 || rm.getColumnType(i) == 93 || rm.getColumnType(i) == 2005) {
                    query += "\'";
                    query += s;
                    query += "\'";
                } else {
                    query += s;
                }
                
                    query += ",";
                
                j++;
            }
            query = query.substring(0, query.length() - 1);
            query += ")";
            return query;
        } catch (SQLException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String makeUpdatequery(List<String> l) {
        try {
            query = "UPDATE SQLJ." + table + " SET ";
            ResultSetMetaData rm = getmeta();
            int n = rm.getColumnCount();
            int k = l.size() - 1;
            int j = 0;
            for (int i = 1; i <= n; i++) {
                try {
                    if (!rm.isAutoIncrement(i)) {
                        if (l.get(j) != null) {
                            query += "\""+rm.getColumnName(i)+"\"";
                            String s=l.get(j).replaceAll("\'", "\'\'");
                            if (rm.getColumnType(i) == 12 || rm.getColumnType(i) == 93 || rm.getColumnType(i) == 2005) {
                                query += "=\'";
                                query += s;
                                query += "\'";
                            } else {
                                query += "=" + s;
                            }
                            query += ",";
                        }
                        j++;
                    }


                } catch (SQLException ex) {
                    Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            query = query.substring(0, query.length() - 1);
            /*if (field != 0) {
                query += " WHERE " + rm.getColumnName(field) + "=";
                if (rm.getColumnType(field) == 12) {
                    query += "\'";
                    query += value;
                    query += "\'";
                } else {
                    query += value;
                }
            }*/
            query+=cond;
            cond=" ";
            System.out.println(query);
            return query;
        } catch (SQLException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int update(List<String> l) {
        makeUpdatequery(l);
        try {
            Statement stmt;
            stmt = conn.createStatement();
            if (stmt.execute(query)) {
                return 0;
            } else {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    public String makeDelquery() {

            ResultSetMetaData rm = getmeta();
            query = "delete from SQLJ." + table + cond;
            cond=" ";
            return query;

    }

    public int delete() {
        try {
            Statement stmt;
            stmt = conn.createStatement();
            makeDelquery();
            System.out.println(query);
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    public static void main(String args[]) {

            Db d = new Db();
            d.selectTable("PROFILE");
            List<String> p=new ArrayList<String>();
            p.add("10");
            p.add(null);p.add(null);p.add(null);p.add(null);p.add(null);p.add(null);
            p.add(null);p.add(null);p.add(null);p.add(null);p.add(null);p.add(null);
            System.out.println(d.makequery(p));
            
            /* try {
            d.makeUpdatequery(l,hierarchy.LEVEL_CODE.value,"LC1");
            System.out.println(d.query);
            } catch (Exception ex) {
            }*/
      
    }
}

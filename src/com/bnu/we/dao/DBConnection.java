package com.bnu.we.dao;
import java.sql.*;
import java.util.Date ;
import java.text.*;
import java.awt.*;
import javax.swing.*;
import java.util.StringTokenizer;

public class DBConnection
{
    private static String        UserName = "root";
    private static String        UserPassword = "admin";
    private static String        connectionDriver = null;
    private static Connection con;
  
    public DBConnection()
    {
	 
    }
//==============================================================================
   public static void checkDriverType()
  {
          UserName = "admin";
          UserPassword = "admin";
         connectionDriver = "jdbc:mysql://localhost:3306/studentdb" ;
  }
//==============================================================================
  public static Connection makeConnection()
  {
    if(con == null)
    {
      try
        {
          checkDriverType();
          Class.forName("com.mysql.jdbc.Driver");
          con = DriverManager.getConnection(connectionDriver,UserName,UserPassword);
          System.out.println("DBConnection -- Connection is established");
          System.out.println("Connection is established with " + connectionDriver);
          return con;
       }//end of try block

      catch(ClassNotFoundException cnf)
      {
            JOptionPane.showMessageDialog(null,"Check your JdbcOdbcDriver Drivers ");
      }
      catch(SQLException sqle)
      {
            JOptionPane.showMessageDialog(null,"Check your Data Source Name in ODBC ");
      }
    }//end of if(con == null)

   return con;
 }//end of method getConnection()

 public static void setConnection(Connection newCon)
 {
    con = newCon;
 }
 
 public long getMaxId(String sqlQuery,String strFeild)
 {
    long Temp = 0;
     try
       {
           con  = makeConnection();
           Statement stmt = con.createStatement();
           ResultSet rs = stmt.executeQuery(sqlQuery);
           if (rs.next())
           {
               Temp = rs.getInt(strFeild);
           }
       }//try
    catch(SQLException e1)
       {
             System.out.println("Exception during Creating ResultSet Object ");
             e1.printStackTrace();
       }//catch
  return Temp + 1;
 }//getMaxClientId
//==============================================================================
 public Connection getConnection()
 {
      setConnection(makeConnection());
      return con;
 }
//==============================================================================
 public static void setUserName(String name)
 {
    UserName += name;
 }
 //==============================================================================
 
 public static String getUserName()
 {
    return UserName;
 }
//==============================================================================
private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

    try {
        if (myRs != null) {
            myRs.close();
        }

        if (myStmt != null) {
            myStmt.close();
        }

        if (myConn != null) {
            myConn.close();   // doesn't really close it ... just puts back in connection pool
        }
    }
    catch (Exception exc) {
        exc.printStackTrace();
    }
}
}// end of class



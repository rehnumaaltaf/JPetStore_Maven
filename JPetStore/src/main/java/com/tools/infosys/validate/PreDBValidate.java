package com.tools.infosys.validate;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class PreDBValidate {
   public static void main(String args[]) throws Exception
   {
	   String userName = "cisqladmin";
	   String password = "infy1234+";
	   Connection conn = null;
	   Statement stmt = null;
	   ResultSet rsColumns = null, rsTables = null;
	   String url = "jdbc:sqlserver://mystgserv02\\SQLEXPRESS:1433;databaseName=ICIP_DB_Dev";

	   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	   conn = DriverManager.getConnection(url, userName, password);
	   System.out.println("connected");
       stmt = conn.createStatement();
       String queryString = "select * from job_details";
       DatabaseMetaData md2 = conn.getMetaData();
       rsColumns = md2.getColumns(null, null, "job_details", "job_name");
       if (rsColumns.next()) {
           System.out.println(" Column Exists !");
      }
       else
       {
    	   throw new Exception();
       }
       rsTables = md2.getTables(null, null, "users2", null);
       if (rsTables.next()) {
           System.out.println(" Table Exists !");
      }
       else
       {
    	   throw new Exception();
       }
        
       /*ResultSet rs = stmt.executeQuery(queryString);
       while (rs.next()) {
          System.out.println(rs.getString(1));

   }*/
}
}


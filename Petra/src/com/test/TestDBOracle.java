package com.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.genere.orders.config.WSConfigurer;

public class TestDBOracle {

	public static String urllea = "jdbc:oracle:thin:@//172.30.60.8:1521/GNX";
	public static String url = WSConfigurer.INSTANCE.getProperty("base_url");
	public static String user = WSConfigurer.INSTANCE.getProperty("base_user");
	public static String mdp = WSConfigurer.INSTANCE.getProperty("base_mdp");;
	
	public static void main(String[] args)
		      throws ClassNotFoundException, SQLException
		  {
		    Class.forName("oracle.jdbc.driver.OracleDriver");
		    //
		    // or
		    // DriverManager.registerDriver
		    //        (new oracle.jdbc.driver.OracleDriver());

		        //String url = "jdbc:oracle:thin:@//srv-rect-sgbd-ux.scaso.fr:1521/MILDEV";
		    //               jdbc:oracle:thin:@//host:port/service
		    // or
		    // String url = "jdbc:oracle:thin:@server.local:1521:prodsid";
		    //               jdbc:oracle:thin:@host:port:SID
		    //
		    //  SID  - System ID of the Oracle server database instance.
			//         By default, Oracle Database 10g Express Edition
			//         creates one database instance called XE.
			//         ex : String url = "jdbc:oracle:thin:@myhost:1521:xe";



		    Connection conn =
		         DriverManager.getConnection(url,user, mdp);

		    conn.setAutoCommit(false);
		    //Statement stmt = conn.createStatement();
//		    ResultSet rset = stmt.executeQuery("select BANNER from SYS.V_$VERSION");
//		    while (rset.next()) {
//		         System.out.println (rset.getString(1));
//		    }
		    
//		    ResultSet rset2 = stmt.executeQuery("select * from eve where typeve='EMB'");
//		    while (rset2.next()) {
//		    	System.out.println("nouv eve");
//		        System.out.println (rset2.getString("numeve"));
////		        System.out.println (rset2.getString(2));
////		        System.out.println (rset2.getString(3));
////		        System.out.println (rset2.getFetchSize());
//		    }
		    
		    DatabaseMetaData dmd = conn.getMetaData();

		    String nomDeLaTable = "UT_CONFIG";
		    ResultSet resultat = dmd.getColumns(conn.getCatalog(),null,nomDeLaTable, "%");
		     
		    //affichage des informations
		    //ResultSetMetaData rsmd = resultat.getMetaData(); 
		    //COLUMN_NAME
		    //TYPE_NAME
		    
		    int nbCols = resultat.getFetchSize();
		    
		    System.out.println("nbCols:"+nbCols);
		    //resultat.get
		    int i=1;
		    while(resultat.next()){
		    	System.out.println(i++);
		    	System.out.println("nom table:" +resultat.getObject(3).toString());
		    	System.out.println("nom col:" +resultat.getObject("COLUMN_NAME").toString());
		    	//resultat.getObject("CODTBL");
		    	//resultat.get
		    	//rsmd.getColumnName()
		    	
		    	//System.out.println("nom table:" +resultat.getObject(3).toString());
		    	
//		        for(int i=1; i<=rsmd.getColumnCount(); i++){
//		        	System.out.println(i+"--"+rsmd.getColumnCount());
//			    	String col = rsmd.getColumnName(i);
//			    	Object val = resultat.getObject(i);
//			    	int type = rsmd.getColumnType(i);
//			    	System.out.println(col+" = "+val+" type: "+type);
//		    	}
		        System.out.println();
		    }
		    
		    
		    //stmt.close();
		    System.out.println ("Ok.");
		  }


}

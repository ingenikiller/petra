package com.genere.orders.bdd;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.genere.orders.config.WSConfigurer;

public class ConnexionBDD {

	private static ConnexionBDD connexion;
	
	private static String url = WSConfigurer.INSTANCE.getProperty("base_url");
	private static String user = WSConfigurer.INSTANCE.getProperty("base_user");
	private static String mdp = WSConfigurer.INSTANCE.getProperty("base_mdp");;
	
	private Connection connOracle;
	
	private ConnexionBDD(){
		try {
			System.out.println("Connexion BDD "+url);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connOracle = DriverManager.getConnection(url,user, mdp);
			connOracle.setAutoCommit(false);
			System.out.println("Connexion BDD OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * méthode d'accès singleton
	 * @return
	 */
	public static ConnexionBDD getInstance(){
		if(connexion==null){
			connexion = new ConnexionBDD();
		}
		return connexion;
	}
	
	/**
	 * retourne MetaData de la table
	 * @param nomTable nom de la table
	 * @return
	 * @throws SQLException
	 */
	public ResultSet getTableDefinition(String nomTable) throws SQLException{
		DatabaseMetaData dmd = connOracle.getMetaData();
		return dmd.getColumns(connOracle.getCatalog(),null,nomTable, "%");
	}
	
	/**
	 * crée un statement pour requete
	 * @return
	 * @throws SQLException
	 */
	public Statement createStatement() throws SQLException{
		int type = ResultSet.TYPE_SCROLL_INSENSITIVE;
		int mode = ResultSet.CONCUR_UPDATABLE;
		//stmt = cnx.createStatement(type,mode); 
		return this.connOracle.createStatement(type,mode);
	}
	
}

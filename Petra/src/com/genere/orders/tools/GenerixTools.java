package com.genere.orders.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.genere.orders.bdd.ConnexionBDD;

public class GenerixTools {

	public static String getMevCodsocPhy(String codsoc, String codent, String segment) throws SQLException, GenereOrdersException{
		String retour="";
		String requete = "SELECT codsoc FROM mev WHERE codsoc_phy="+codsoc+" AND codent='"+codent+ "' AND segment='"+segment+"'";
		System.out.println("req recherche lien mev:"+requete);
		Statement stmt = ConnexionBDD.getInstance().createStatement();
		ResultSet rset = stmt.executeQuery(requete);
	    
		while (rset.next()) {
			retour = rset.getString("CODSOC");
		}
		
		if(retour==null || retour.equals("")){
			throw new GenereOrdersException("pas de lien mev pour "+codsoc+"/"+codent+"/"+segment);
		}
		
		return retour;
	}
	
}

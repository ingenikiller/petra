package com.genere.orders.tools;

import java.sql.ResultSet;
import java.sql.Statement;

import com.genere.orders.bdd.ConnexionBDD;
import com.genere.orders.objets.Requete;
import com.genere.orders.objets.Table;
import com.genere.orders.tools.fichiers.FichierEcritureSingleton;

public class LanceurTools extends Requete{

	private String codfct;
	private String codpled;
	
	public LanceurTools(String codfct){
		this.codfct = codfct;
	}
	
	public LanceurTools(String codfct, String codpled){
		this.codfct = codfct;
		this.codpled = codpled;
	}
	
	public boolean execute() throws Exception{
		FichierEcritureSingleton fichierSortie = FichierEcritureSingleton.INSTANCE;
		
		//constitution de la requete
		this.clause = "codsoc=~societe AND codfct='"+this.codfct+"'";
		if(codpled!=null && !codpled.equals("")){
			this.clause += " AND codpled='"+this.codpled+"'";
		}
		
		Table table = TableTools.INSTANCE.getTableDefinition(this.nomTable);
		if(table==null){
			System.out.println("Table inexistante");
			return false;
		}
		
		String listeChamps = table.getListeChampsChaine();
		
		//affiche requete de delete
		fichierSortie.ecrire(this.getRequeteDelete());
		
		Statement stmt = ConnexionBDD.getInstance().createStatement();
		ResultSet rset = stmt.executeQuery(this.getRequeteSelectValorisee());
	    
	    //on parcourt le resultset
	    while (rset.next()) {
	    	genereLigne(table, listeChamps, rset, false);
	    	String codpled = rset.getString("CODPLED"); 
	    	
	    	Requete req = new Requete();
	    	req.setTable("UT_PLEDC");
	    	req.setClause("codsoc=~societe AND codpled='"+codpled+"'");
	    	req.execute();
	    }
		
		return true;
	}
}

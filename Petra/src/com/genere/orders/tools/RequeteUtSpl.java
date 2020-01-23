package com.genere.orders.tools;

import java.sql.ResultSet;
import java.sql.Statement;

import com.genere.orders.bdd.ConnexionBDD;
import com.genere.orders.objets.Requete;
import com.genere.orders.objets.Table;
import com.genere.orders.tools.fichiers.FichierEcritureSingleton;

public class RequeteUtSpl extends Requete {

	private String elementActif="N";
	
	public RequeteUtSpl(){
		super();
		super.setTable("UT_SPL");
	}
	
	public void setElementActif(String elementActif){
		this.elementActif = elementActif;
	}
	
	public boolean execute(boolean commente) throws Exception{
		FichierEcritureSingleton ficSortie = FichierEcritureSingleton.INSTANCE;
		
		//extraction des lignes de ut_spl
		Table table = TableTools.INSTANCE.getTableDefinition(this.nomTable);
		if(table==null){
			throw new GenereOrdersException("Table inexistante");
		}
		
		String listeChamps = table.getListeChampsChaine();
		
		//affiche requete de delete
		ficSortie.ecrire(this.getRequeteDelete());
		
		if(this.elementActif!=null && this.elementActif.equals("O")){
			this.clause += " AND eshs='ES'";
		}
		
		
		Statement stmt = ConnexionBDD.getInstance().createStatement();
		ResultSet rset = stmt.executeQuery(this.getRequeteSelectValorisee());
		
		//on sauvegarde la valeur de numéro si elle existait au paravant
		String sauvegardeCouple = "NUMERO="+VariableRequeteRemplacement.INSTANCE.getRemplacement("NUMERO");
		String rempCodsoc = ParamRequeteReplacement.INSTANCE.getValeur("~societe");
		//on parcourt le resultset
	    while (rset.next()) {
	    	//on génère la ligne de ut_spl
	    	String numero = rset.getString("NUMERO");
	    	String ord = rset.getString("ORD");
	    	String nomproc =rset.getString("NOMPRC");
	    	ficSortie.ecrire("--element de procedure numero "+ ord);
	    	genereLigne(table, listeChamps, rset, commente);
	    	
	    	
			if(ParamMoteur.INSTANCE.getValorisationRequete()){
				VariableRequeteRemplacement.INSTANCE.addCouple("NUMERO=(SELECT numero FROM "+nomSchema+"ut_spl " +
	    			"WHERE codsoc="+rempCodsoc+" AND nomprc='"+nomproc+"' AND ord="+ord+" AND numpere=0)");
			} else {
				VariableRequeteRemplacement.INSTANCE.addCouple("NUMERO=(SELECT numero FROM "+nomSchema+"ut_spl " +
		    			"WHERE codsoc=~societe AND nomprc='"+nomproc+"' AND ord="+ord+" AND numpere=0)");
			}
	    	ficSortie.ecrire("--generation ut_par pour element "+ord);
	    	Requete ut_par = new Requete();
	    	ut_par.setTable("UT_PAR");
	    	ut_par.setClause("codsoc=~societe AND numero="+numero);
	    	ut_par.execute(false, false);
	    	//reprise du replace de numero
	    	VariableRequeteRemplacement.INSTANCE.addCouple(sauvegardeCouple);
	    }
	    
	    
		
		
		return true;
	}
	
}

package com.genere.orders.objets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Set;

import com.genere.orders.bdd.ConnexionBDD;
import com.genere.orders.config.WSConfigurer;
import com.genere.orders.tools.ParamMoteur;
import com.genere.orders.tools.ParamRequeteReplacement;
import com.genere.orders.tools.TableTools;
import com.genere.orders.tools.VariableRequeteRemplacement;
import com.genere.orders.tools.fichiers.FichierEcritureSingleton;

public class Requete {
	protected String nomTable;
	protected String clause;
	
	protected String nomSchema = "";
	
	private int nombreLignes=0;
	
	public int getNombreLignes() {
		return nombreLignes;
	}

	public Requete(){
		if(ParamMoteur.INSTANCE.getSchemaBase()){
			this.nomSchema = WSConfigurer.INSTANCE.getProperty("base_user").toUpperCase() + ".";
		}
	}
	
	public Requete(String nomTable){
		this.nomTable=nomTable;
	}
	
	public String getTable() {
		return nomTable;
	}
	public void setTable(String table) {
		this.nomTable = this.nomSchema+table.toUpperCase();
	}
	public String getClause() {
		return clause;
	}
	public void setClause(String clause) {
		this.clause = clause;
	}
	
	public String getRequeteSelect(){
		String requete = "SELECT * FROM "+nomTable;
		if(clause!=null && !clause.equals("")){
			requete+=" WHERE "+clause;
		}
		return requete;
	}
	
	public String getRequeteCount(boolean valorisation){
		String requete = "SELECT count(1) FROM "+nomTable;
		if(clause!=null && !clause.equals("")){
			if(valorisation){
				requete+=" WHERE "+ParamRequeteReplacement.INSTANCE.valoriseClause(clause);
			} else {
				requete+=" WHERE "+clause;
			}
		}
		return requete;
	}
	
	/**
	 * Retourne la requete valorisée avec les directives RPA 
	 * @return
	 */
	public String getRequeteSelectValorisee(){
		String requete = "SELECT * FROM "+nomTable;
		if(clause!=null && !clause.equals("")){
			System.out.println("requete av valo:"+requete+" WHERE "+clause);
			requete+=" WHERE "+ParamRequeteReplacement.INSTANCE.valoriseClause(clause);
		}
		System.out.println("requete ap valo:"+requete);
		return requete;
	}
	
	/**
	 * genere la requete de DELETE avec ; à la fin
	 * @return
	 */
	public String getRequeteDelete(){
		String requete = "DELETE FROM "+nomTable;
		if(clause!=null && !clause.equals("")){
			if(ParamMoteur.INSTANCE.getValorisationRequete()==true){
				requete+=" WHERE "+ParamRequeteReplacement.INSTANCE.valoriseClause(clause);
			} else {
				requete+=" WHERE "+clause;
			}
		}
		return requete+";";
	}
	
//	public String getRequeteDeleteValorisee(){
//		String requete = "DELETE FROM "+nomTable;
//		if(clause!=null && !clause.equals("")){
//			requete+=" WHERE "+ParamRequeteReplacement.INSTANCE.valoriseClause(clause);
//		}
//		return requete+";";
//	}
	
	public boolean execute() throws Exception{
		this.execute(false);
		return true;
	}
	
	/**
	 * génère une ligne d'insert
	 * @param table table SQL
	 * @param listeChamps liste des champs de la table
	 * @param rset rowset de donnée
	 * @param commente commentaire des insert
	 * @throws Exception
	 */
	protected void genereLigne(Table table, String listeChamps, ResultSet rset, boolean commente) throws Exception{
		FichierEcritureSingleton fic = FichierEcritureSingleton.INSTANCE;
		StringBuffer values = new StringBuffer();
    	Set<String> cle = table.getChamps().keySet();
    	// on crée un Iterator pour parcourir notre HashSet
		Iterator<String> i=cle.iterator();
		while(i.hasNext()){
			Champ champ = table.getChamps().get(i.next());
			//si le buffer n'est pas vide, on n'est pas sur le premier champs 
			if(values.length()!=0){
				values.append(", ");
			}
			//recherche une valeur de remplacement
			String valeurRemplacement = VariableRequeteRemplacement.INSTANCE.getRemplacement(champ.getNom());
			//System.out.println("champ:"+champ.getNom());
			String valeurBase=rset.getString(champ.getNom());
			if(valeurRemplacement!=null){
				values.append(valeurRemplacement);
			} else if(rset.wasNull()){
				values.append("null");
			} else if(champ.getType().equals(Champ.TYPE_NUMBER)){
				values.append(valeurBase);//.replace(",", "."));
			}else {
				values.append("'"+valeurBase.replace("'", "''") + "'");
			}
			
		}
		if(commente){
			fic.ecrire("--INSERT INTO "+table.getNom()+"("+listeChamps+") VALUES("+values+");");
		} else {
			fic.ecrire("INSERT INTO "+table.getNom()+"("+listeChamps+") VALUES("+values+");");
		}
		
	}
	
	/**
	 * exécute la requête
	 * @param commente 
	 * @return retour de l'exécution
	 * @throws Exception
	 */
	public boolean execute(boolean commente) throws Exception{
		return this.execute(commente, true);
	}
	
	/**
	 * Genere les files d'insertion a partir de la requete de  select
	 * @param commente commente les lignes d'insert
	 * @throws IOException
	 * @throws SQLException
	 */
	public boolean execute(boolean commente, boolean genereDelete) throws Exception{
		Table table = TableTools.INSTANCE.getTableDefinition(this.nomTable.trim());
		if(table==null){
			System.out.println("Table inexistante");
			return false;
		}
		
		String listeChamps = table.getListeChampsChaine();
		FichierEcritureSingleton ficSortie = FichierEcritureSingleton.INSTANCE;
		
		//affiche requete de delete
		if(genereDelete){
			ficSortie.ecrire(this.getRequeteDelete());
		}
		
		
		
		Statement stmt = ConnexionBDD.getInstance().createStatement();
		ResultSet rset = stmt.executeQuery(this.getRequeteSelectValorisee());
	    
		//on place le curseur sur le dernier tuple
		rset.last();
		//on récupère le numéro de la ligne
		this.nombreLignes = rset.getRow();
		//on repace le curseur avant la première ligne
		rset.beforeFirst();
		
		System.out.println("Lignes selectionnees:"+this.nombreLignes);
	    //on parcourt le resultset
	    while (rset.next()) {
	    	genereLigne(table, listeChamps, rset, commente);
	    }
		stmt.close();
		//affiche requete de count
		ficSortie.ecrire("--");
		if(genereDelete){
			ficSortie.ecrire(this.getRequeteCount(ParamMoteur.INSTANCE.getValorisationRequete())+";");
		}
		ficSortie.ecrire();
		return true;
	}
	
}

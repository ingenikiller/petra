package com.genere.orders.tools;

import com.genere.orders.objets.Requete;
import com.genere.orders.tools.fichiers.FichierEcritureSingleton;

public class CommunTools {
	
	/**
	 * Génère un bloc d'instruction sql autour d'une requête
	 * @param table
	 * @param clause
	 * @throws Exception
	 */
	protected void genBloc(String table, String clause) throws Exception{
		FichierEcritureSingleton fichierSortie = FichierEcritureSingleton.INSTANCE;
		fichierSortie.ecrire("-- table "+table);
		Requete req = new Requete();
		req.setTable(table);
		req.setClause(clause);
		req.execute();
	}
	
	/**
	 * lève une exception si la variable n'est pas valorisée
	 * @param type nom de la directive fichier
	 * @param donnee valeur à controler
	 * @throws GenereOrdersException
	 */
	protected void verifEntree(String type, String donnee) throws GenereOrdersException{
		if(donnee == null || donnee.equals("")){
			throw new GenereOrdersException("Balise "+type+" vide ou absente");
		}
		
	}
	
	/**
	 * retourne une liste de champs utilisable dans une clause IN
	 * @param chaine
	 * @return
	 */
	public String replaceVirgule(String chaine){
		String retour = chaine.replaceAll(" ", "");
		retour = retour.replaceAll(",", "','");
		return "'"+retour+"'";
	}
	
}

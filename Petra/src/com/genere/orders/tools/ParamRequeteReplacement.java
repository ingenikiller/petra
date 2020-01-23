package com.genere.orders.tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Regroupe toutes les directives RPA 
 * @author oljean
 *
 */
public class ParamRequeteReplacement extends AbstractMapGestion{
	
	public static final ParamRequeteReplacement INSTANCE = new ParamRequeteReplacement();
	
	/**
	 * Constructeur
	 */
	private ParamRequeteReplacement(){
		this.mapReplace = new HashMap<String, String>();
	}
	
	/**
	 * valorise la clause à partir des donnees de conf
	 * @param clause
	 * @return
	 */
	public String valoriseClause(String clause){
		Set<String> cle = this.mapReplace.keySet();
		// on cree un Iterator pour parcourir notre HashSet
		Iterator<String> i=cle.iterator();
		while(i.hasNext()){
			String chaine=i.next();
			clause=clause.replaceAll(chaine, ""+this.mapReplace.get(chaine)+"");
		}
		return clause;
	}
	
	/**
	 * retourne une valeur par rapport à une clé
	 * @param cle
	 * @return
	 */
	public String getValeur(String cle){
		return this.mapReplace.get(cle);
	}
	
}

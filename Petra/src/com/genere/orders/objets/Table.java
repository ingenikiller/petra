package com.genere.orders.objets;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Table implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String nom;
	
	private Map<String, Champ> champs;

	/**
	 * constructeur par défaut
	 * @param nom nom de la table
	 */
	public Table(String nom){
		this.nom = nom;
		this.champs = new LinkedHashMap<String, Champ>();
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * permet d'accéder à la map des champs
	 * @return map des champs 
	 */
	public Map<String, Champ> getChamps() {
		return champs;
	}
		
	/**
	 * ajoute un champ a la table
	 * @param nomChamp nom du champ
	 * @param type (VARCHAR2, NUMBER, ...)
	 */
	public void addChamp(String nomChamp, String type){
		Champ champ = new Champ();
		champ.setNom(nomChamp);
		champ.setType(type);
		this.champs.put(nomChamp, champ);
	}
	
	/**
	 * fonction de récupération de la liste des champs d'une table
	 * @return liste des champs sous la forme d'une chaine sépérés par une virgule
	 */
	public String getListeChampsChaine(){
		StringBuffer sb = new StringBuffer();
		
		Set<String> cle = this.champs.keySet();
		Iterator<String> i=cle.iterator(); // on crée un Iterator pour parcourir notre HashSet
		while(i.hasNext()) // tant qu'on a un suivant
		{
			if(sb.length()!=0){
				sb.append(", ");
			}
			sb.append(i.next());
		}
		return sb.toString();
	}
}

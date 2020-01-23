package com.genere.orders.tools;

import java.util.Map;

public class AbstractMapGestion {

	protected Map<String, String> mapReplace;

	public boolean addCouple(String chaine) {
		int index = chaine.indexOf("=");
		if (index <= 0) {
			System.out.println("chaine "+chaine+ " non prise en compte");
			return false;
		}
		this.mapReplace.put(chaine.substring(0, index).toUpperCase(),
				chaine.substring(index + 1));
		return true;
	}
	
	/**
	 * supprime une clé dans la map en majuscule
	 * @param cle
	 * @return
	 */
	public boolean suppCouple(String cle){
		this.mapReplace.remove(cle.toUpperCase());
		return true;
	}

	/**
	 * ajoute une cle de remplacement de type cle=valeur
	 * @param chaine
	 * @param upper
	 * @return 
	 */
	public void addCouple(String chaine, boolean upper) {
		int index = chaine.indexOf("=");
		if (index <= 0) {
			System.out.println("chaine "+chaine+ " non prise en compte");
		} else {
			if (upper) {
				this.mapReplace.put(chaine.substring(0, index).toUpperCase(),
						chaine.substring(index + 1));
			} else {
				this.mapReplace.put(chaine.substring(0, index).toLowerCase(),
						chaine.substring(index + 1));
			}
		}
	}
	
	/**
	 * supprime une clé dans la map avec prise en compte de la casse
	 * @param cle
	 * @param upper
	 */
	public void suppCouple(String cle, boolean upper) {
		if (upper) {
			this.mapReplace.remove(cle.toUpperCase());
		} else {
			this.mapReplace.remove(cle.toLowerCase());
		}
	}
	
	/**
	 * Supprime une clé
	 * @param param
	 */
	public void removeCouple(String param){
		this.mapReplace.remove(param);
	}
	
	/**
	 * vide la map
	 */
	public void videListe(){
		this.mapReplace.clear();
	}
}

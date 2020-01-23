package com.genere.orders.tools;

import java.util.HashMap;

/**
 * Regroupe toutes les directives RVA 
 * @author oljean
 *
 */
public class VariableRequeteRemplacement extends AbstractMapGestion {

	public static final VariableRequeteRemplacement INSTANCE = new VariableRequeteRemplacement();
	
	private VariableRequeteRemplacement() {
		this.mapReplace = new HashMap<String, String>();
	}

	/**
	 * retourne la valeur de remplacement
	 * @param nomChamp
	 * @return
	 */
	public String getRemplacement(String nomChamp) {
		return this.mapReplace.get(nomChamp);
	}
}

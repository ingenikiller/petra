package com.genere.orders.tools;

import java.util.HashMap;

public class ParamMoteur extends AbstractMapGestion {

	public static final ParamMoteur INSTANCE = new ParamMoteur(); 
	
	public static final String REQUETE_VALORISEE="REQUETE_VALORISEE";
	public static final String SCHEMA_BASE="SCHEMA_BASE";
	
	
	private ParamMoteur(){
		this.mapReplace = new HashMap<String, String>();
	}
	
	private boolean getBooleanParam(String param){
		String valeur = this.mapReplace.get(param);
		return "O".equals(valeur)?true:false;
	}
	
	/**
	 * Lecture du paramètre REQUETE_VALORISEE
	 * @return valeur booleenne du paramètre
	 */
	public boolean getValorisationRequete(){
		return getBooleanParam(REQUETE_VALORISEE);
	}
	
	/**
	 * Lecture du paramètre SCEMA_BASE
	 * @return
	 */
	public boolean getSchemaBase(){
		return getBooleanParam(SCHEMA_BASE);
	}
	
}

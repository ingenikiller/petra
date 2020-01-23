package com.genere.orders.objets;

public class Champ  implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String TYPE_NUMBER="NUMBER";
	public static final String TYPE_VARCHAR2="VARCHAR2";
	
	private String nom;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private String type;
	
	/**
	 * constructeur par défaut
	 */
	public Champ(){
		
	}
	
	/**
	 * Permet de créer et d'initialiser un champ
	 * @param nom
	 * @param type
	 */
	public Champ(String nom, String type){
		this.nom=nom;
		this.type=type;
	}
	
}

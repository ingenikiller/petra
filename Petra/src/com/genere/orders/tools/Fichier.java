package com.genere.orders.tools;


public abstract class Fichier {
	
	protected String cheminFichier;
	
	public Fichier(String cheminFichier){
		this.cheminFichier=cheminFichier;

	}
	
	public String getCheminFichier(){
		return this.cheminFichier;
	}
	
}

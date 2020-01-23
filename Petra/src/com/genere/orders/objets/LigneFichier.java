package com.genere.orders.objets;

public class LigneFichier {
	public String entete;
	public String contenu;
	
	public LigneFichier(String ligne){
		if(ligne!=null && ligne.length()>=4){
			this.entete = ligne.substring(0, 3);
			this.contenu = ligne.substring(4);
		}
	}
	
	
}

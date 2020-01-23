package com.genere.orders.parser.v2.directives;

import com.genere.orders.tools.FonctionTools;

public class DirectiveFonctionnalite extends AbstractDirective {

	private String nomFonctionnalite;
	private String mode;
	private String nomPev;
	private String commentaire;
	private String langue;
	private String traduction;
	
	/**
	 * constructeur par défaut
	 * @param nomFonctionnalite
	 * @param mode
	 * @param nomPev
	 * @param commentaire
	 */
	public DirectiveFonctionnalite(String nomFonctionnalite, String mode, String nomPev, String commentaire, String langue, String traduction) {
		this.nomFonctionnalite = nomFonctionnalite;
		this.mode = mode;
		this.nomPev = nomPev;
		this.commentaire = commentaire;
		this.langue=langue;
		this.traduction = traduction;
	}
	
	
	@Override
	public boolean execute() throws Exception {
		FonctionTools fonction = new FonctionTools(this.nomFonctionnalite, this.mode, this.nomPev, this.commentaire, this.langue, this.traduction);
		fonction.execute();
		return true;
	}

}
